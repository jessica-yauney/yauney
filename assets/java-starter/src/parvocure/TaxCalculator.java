package parvocure;

import java.util.Map;

public class TaxCalculator {
    private final Map<String, Double> taxRatePercent;

    public TaxCalculator(Map<String, Double> taxRatePercent) {
        this.taxRatePercent = Map.copyOf(taxRatePercent);
    }

    public double calculate(double netCost, String deliveryLocation) {
        // TODO: look up the tax value and calculate the tax amount.
        throw new UnsupportedOperationException("TODO");
    }
}
