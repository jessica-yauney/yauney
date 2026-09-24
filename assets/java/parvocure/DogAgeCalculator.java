package parvocure;

public class DogAgeCalculator {
    public double calculate(double humanAgeYears) {
        if (!Double.isFinite(humanAgeYears) || humanAgeYears < 0) {
            throw new IllegalArgumentException("dog age must be finite and nonnegative");
        }
        return humanAgeYears * 7.0;
    }
}
