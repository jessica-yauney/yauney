package parvocure;

import java.util.Map;

public class TaxCalculator {
    private final Map<String, Double> taxRatePercent;

    public TaxCalculator(Map<String, Double> taxRatePercent) {
        this.taxRatePercent = Map.copyOf(taxRatePercent);
    }

    public double calculate(double netCost, String deliveryLocation) {
        if (!Double.isFinite(netCost) || netCost < 0) {
            throw new IllegalArgumentException("net cost must be finite and nonnegative");
        }
        Double rate = taxRatePercent.get(deliveryLocation);
        if (rate == null) {
            throw new IllegalArgumentException(
                    "unsupported delivery location: " + deliveryLocation);
        }
        return netCost * rate / 100.0;
    }
}
