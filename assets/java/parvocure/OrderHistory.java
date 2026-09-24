package parvocure;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private final List<HistoryRecord> records = new ArrayList<>();
    public OrderHistory(){}

    public boolean hasOrderWithin30Days(String ownerId, LocalDate purchaseDate) {
        for (HistoryRecord record : records) {
            if (!record.ownerId().equals(ownerId)) continue;
            long days = ChronoUnit.DAYS.between(record.purchaseDate(), purchaseDate);
            if (days >= 0 && days < 30) return true;
        }
        return false;
    }

    public boolean couponUsedInCalendarMonth(
            String ownerId, String coupon, LocalDate purchaseDate) {
        for (HistoryRecord record : records) {
            if (record.ownerId().equals(ownerId)
                    && record.coupons().contains(coupon)
                    && record.purchaseDate().getYear() == purchaseDate.getYear()
                    && record.purchaseDate().getMonthValue() == purchaseDate.getMonthValue()) {
                return true;
            }
        }
        return false;
    }

    public void record(
            String ownerId,
            double netCost,
            double taxAmount,
            double shippingCost,
            LocalDate purchaseDate,
            List<String> coupons) {
        records.add(new HistoryRecord(
                ownerId, netCost, taxAmount, shippingCost, purchaseDate, coupons));
    }

    public List<HistoryRecord> allRecords() {
        return List.copyOf(records);
    }
}
