package parvocure;

import java.util.Map;

public record DataTables(
        Map<String, Double> breedBase,
        Map<String, Double> taxRatePercent,
        Map<String, Double> shippingFee) {

    public DataTables {
        breedBase = Map.copyOf(breedBase);
        taxRatePercent = Map.copyOf(taxRatePercent);
        shippingFee = Map.copyOf(shippingFee);
    }
}
