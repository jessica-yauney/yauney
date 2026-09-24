package parvocure;

public class TotalCostCalculator {
    public double calculate(double netCost, double shippingCost, double taxAmount) {
        double[] values = {netCost, shippingCost, taxAmount};
        for (double value : values) {
            if (!Double.isFinite(value) || value < 0) {
                throw new IllegalArgumentException(
                        "total inputs must be finite and nonnegative");
            }
        }
        return netCost + shippingCost + taxAmount;
    }
}
