package parvocure;

import java.nio.file.Path;
import java.util.List;

public class CheckoutService {
    private static final double ML_PER_FL_OZ = 29.5735295625;

    private final OrderHistory history;
    private final DogAgeCalculator age;
    private final DosageCalculator dosage;
    private final MedicineCostCalculator medicineCost;
    private final DiscountCalculator discount;
    private final ShippingCalculator shipping;
    private final TaxCalculator tax;
    private final TotalCostCalculator total;
    private final DataTables data;

    public List<String> usStates = List.of(
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming"
    );

    public CheckoutService() {
        this(DataLoader.load(Path.of("data")), new OrderHistory());
    }

    public CheckoutService(DataTables data) {
        this(data, new OrderHistory());
    }

    public CheckoutService(DataTables data, OrderHistory history) {
        this.data = data;
        this.history = history;
        this.age = new DogAgeCalculator();
        this.dosage = new DosageCalculator(data.breedBase());
        this.medicineCost = new MedicineCostCalculator();
        this.discount = new DiscountCalculator();
        this.shipping = new ShippingCalculator(data.shippingFee());
        this.tax = new TaxCalculator(data.taxRatePercent());
        this.total = new TotalCostCalculator();
    }

    public OrderQuote checkout(CheckoutRequest request) {
        validateRequest(request);
        if (history.hasOrderWithin30Days(request.ownerId(), request.purchaseDate())) {
            throw new IllegalArgumentException(
                    "owner already ordered a dose within the previous 30 days");
        }

        double dogAgeDisplay = age.calculate(request.dogAgeYears());
        double dosageMl = dosage.calculateMl(
                request.dogBreed(), request.dogAgeYears(), request.dogWeightKg());
        double medicine = medicineCost.calculate(dosageMl);
        double net = discount.calculate(
                medicine,
                request.coupons(),
                request.ownerId(),
                request.purchaseDate(),
                history);
        double discountAmount = medicine - net;
        double shippingCost = shipping.calculate(request.deliveryLocation());
        double taxAmount = tax.calculate(net, request.deliveryLocation());
        double totalCost = total.calculate(net, shippingCost, taxAmount);

        boolean us = usStates.contains(request.ownerLocation());
        double dosageDisplayValue = us ? dosageMl / ML_PER_FL_OZ : dosageMl;
        String dosageDisplayUnit = us ? "fl oz" : "mL";

        history.record(
                request.ownerId(),
                net,
                taxAmount,
                shippingCost,
                request.purchaseDate(),
                request.coupons());

        return new OrderQuote(
                dogAgeDisplay,
                dosageMl,
                dosageDisplayValue,
                dosageDisplayUnit,
                medicine,
                discountAmount,
                net,
                shippingCost,
                taxAmount,
                totalCost);
    }

    private static void validateRequest(CheckoutRequest request) {
        if (request == null) throw new IllegalArgumentException("request is required");
        if (request.ownerId() == null || request.ownerId().isBlank()) {
            throw new IllegalArgumentException("ownerId is required");
        }
        if (request.ownerLocation() == null || request.ownerLocation().isBlank()) {
            throw new IllegalArgumentException("ownerLocation is required");
        }
        if (request.deliveryLocation() == null || request.deliveryLocation().isBlank()) {
            throw new IllegalArgumentException("deliveryLocation is required");
        }
        if (request.dogBreed() == null || request.dogBreed().isBlank()) {
            throw new IllegalArgumentException("dogBreed is required");
        }
        if (request.coupons() == null) {
            throw new IllegalArgumentException("coupons are required");
        }
        if (request.purchaseDate() == null) {
            throw new IllegalArgumentException("purchaseDate is required");
        }
    }
}
