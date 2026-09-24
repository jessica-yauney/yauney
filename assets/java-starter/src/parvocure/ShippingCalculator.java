package parvocure;

import java.util.Map;

public class ShippingCalculator {
    private final Map<String, Double> shippingFees;

    public ShippingCalculator(Map<String, Double> shippingFees) {
        this.shippingFees = Map.copyOf(shippingFees);
    }

    public double calculate(String deliveryLocation) {
        // TODO: look up the shipping cost.
        // Give a clear error for an unsupported location.
        throw new UnsupportedOperationException("TODO");
    }
}
