package parvocure;

public class MedicineCostCalculator {
    public static final double PRICE_PER_ML = 0.033814057;

    public double calculate(double dosageMl) {
        if (!Double.isFinite(dosageMl) || dosageMl < 0) {
            throw new IllegalArgumentException("dosage must be finite and nonnegative");
        }
        return dosageMl * PRICE_PER_ML;
    }
}
