package parvocure;

import java.util.Map;

public class DosageCalculator {
    private final Map<String, Double> breedBase;

    public DosageCalculator(Map<String, Double> breedBase) {
        this.breedBase = Map.copyOf(breedBase);
    }

    public double calculateMl(
            String breed,
            double humanAgeYears,
            double weightKg) {
        // TODO: validate inputs and implement the clarified dosage rule.
        throw new UnsupportedOperationException("TODO");
    }
}
