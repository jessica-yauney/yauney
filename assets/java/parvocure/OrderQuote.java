package parvocure;

public record OrderQuote(
        double dogAgeDisplay,
        double dosageMl,
        double dosageDisplayValue,
        String dosageDisplayUnit,
        double medicineCost,
        double discountAmount,
        double netCost,
        double shippingCost,
        double taxAmount,
        double totalCost) {}
