package parvocure;

import java.time.LocalDate;
import java.util.List;

public record CheckoutRequest(
        String ownerId,
        String ownerLocation,
        String deliveryLocation,
        String dogBreed,
        double dogAgeYears,
        double dogWeightKg,
        List<String> coupons,
        LocalDate purchaseDate) {

    public CheckoutRequest {
        coupons = List.copyOf(coupons);
    }
}
