package parvocure;

import java.util.Map;

public class DosageCalculator {
    private final Map<String, Double> breedBase;

    public DosageCalculator(Map<String, Double> breedBase) {
        this.breedBase = Map.copyOf(breedBase);
    }

    public double calculateMl(String breed, double humanAgeYears, double weightKg) {
        Double base = breedBase.get(breed);
        if (base == null) {
            throw new IllegalArgumentException("unknown breed: " + breed);
        }
        if (!Double.isFinite(humanAgeYears) || humanAgeYears < 0) {
            throw new IllegalArgumentException("dog age must be finite and nonnegative");
        }
        if (!Double.isFinite(weightKg) || weightKg <= 0) {
            throw new IllegalArgumentException("dog weight must be finite and positive");
        }

        double dosageMl = base * ((weightKg + 5.0) - humanAgeYears / 3.0);
        if (dosageMl < 0) {
            throw new IllegalArgumentException("the dosage formula produced a negative dosage");
        }
        return dosageMl;
    }
}
