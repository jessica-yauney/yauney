package parvocure;

import java.time.LocalDate;
import java.util.List;

public record HistoryRecord(
        String ownerId,
        double netCost,
        double taxAmount,
        double shippingCost,
        LocalDate purchaseDate,
        List<String> coupons) {

    public HistoryRecord {
        coupons = List.copyOf(coupons);
    }
}
