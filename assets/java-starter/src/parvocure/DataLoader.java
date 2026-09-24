package parvocure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DataLoader {
    private DataLoader() {}

    public static DataTables load(Path dataDir) {
        try {
            Map<String, Double> breedBase =
                    loadBreedBase(dataDir.resolve("breed_base.csv"));
            LocationMaps locations =
                    loadLocations(dataDir.resolve("location_rates.csv"));

            return new DataTables(
                    breedBase,
                    locations.taxRates,
                    locations.shippingFees);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to load reference data", e);
        }
    }

    private static Map<String, Double> loadBreedBase(Path path)
            throws IOException {
        Map<String, Double> result = new HashMap<>();
        List<String> lines = Files.readAllLines(path);

        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                continue;
            }

            String[] parts = lines.get(i).split(",", -1);
            result.put(parts[0], Double.parseDouble(parts[1]));
        }

        return result;
    }

    private static LocationMaps loadLocations(Path path)
            throws IOException {
        Map<String, Double> taxRates = new HashMap<>();
        Map<String, Double> shippingFees = new HashMap<>();
        List<String> lines = Files.readAllLines(path);

        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                continue;
            }

            String[] parts = lines.get(i).split(",", -1);
            String location = parts[0];

            taxRates.put(location, Double.parseDouble(parts[1]));
            shippingFees.put(location, Double.parseDouble(parts[2]));
        }

        return new LocationMaps(taxRates, shippingFees);
    }

    private record LocationMaps(
            Map<String, Double> taxRates,
            Map<String, Double> shippingFees) {
    }
}
