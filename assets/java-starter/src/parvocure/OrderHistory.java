package parvocure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private final List<HistoryRecord> records = new ArrayList<>();

    public boolean hasOrderWithin30Days(
            String ownerId,
            LocalDate purchaseDate) {
        // TODO: implement the ordering-frequency rule.
        throw new UnsupportedOperationException("TODO");
    }

    public boolean couponUsedInCalendarMonth(
            String ownerId,
            String coupon,
            LocalDate purchaseDate) {
        // TODO: implement the coupon-reuse rule.
        throw new UnsupportedOperationException("TODO");
    }

    public void record(
            String ownerId,
            double netCost,
            double taxAmount,
            double shippingCost,
            LocalDate purchaseDate,
            List<String> coupons) {
        // TODO: save a HistoryRecord.
        throw new UnsupportedOperationException("TODO");
    }

    public List<HistoryRecord> allRecords() {
        return List.copyOf(records);
    }
}
