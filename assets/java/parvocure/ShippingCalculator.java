package parvocure;

import java.util.Map;

public class ShippingCalculator {
    private final Map<String, Double> shippingFees;

    public ShippingCalculator(Map<String, Double> shippingFees) {
        this.shippingFees = Map.copyOf(shippingFees);
    }

    public double calculate(String deliveryLocation) {
        Double fee = shippingFees.get(deliveryLocation);
        if (fee == null) {
            throw new IllegalArgumentException(
                    "unsupported delivery location: " + deliveryLocation);
        }
        return fee;
    }
}
