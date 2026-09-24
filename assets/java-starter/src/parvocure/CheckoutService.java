package parvocure;

import java.nio.file.Path;

public class CheckoutService {
    private final OrderHistory history;
    private final DogAgeCalculator age;
    private final DosageCalculator dosage;
    private final MedicineCostCalculator medicineCost;
    private final DiscountCalculator discount;
    private final ShippingCalculator shipping;
    private final TaxCalculator tax;
    private final TotalCostCalculator total;

    public CheckoutService() {
        this(DataLoader.load(Path.of("data")), new OrderHistory());
    }

    public CheckoutService(DataTables data) {
        this(data, new OrderHistory());
    }

    public CheckoutService(DataTables data, OrderHistory history) {
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
        // TODO:
        // 1. Validate the request.
        // 2. Enforce order-history rules.
        // 3. Call the component calculators.
        // 4. Decide how dosage should be displayed.
        // 5. Record the purchase.
        // 6. Return an OrderQuote.
        throw new UnsupportedOperationException("TODO");
    }
}
