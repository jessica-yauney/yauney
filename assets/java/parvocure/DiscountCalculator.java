package parvocure;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiscountCalculator {
    private static final Set<String> VALID = Set.of("10% Off", "$10 Off", "BOGO");

    public double calculate(
            double medicineCost,
            List<String> coupons,
            String ownerId,
            LocalDate purchaseDate,
            OrderHistory history) {
        if (!Double.isFinite(medicineCost) || medicineCost < 0) {
            throw new IllegalArgumentException("medicine cost must be finite and nonnegative");
        }
        if (coupons.size() > 2) {
            throw new IllegalArgumentException("maximum of two coupons per transaction");
        }
        if (new HashSet<>(coupons).size() != coupons.size()) {
            throw new IllegalArgumentException(
                    "a coupon type may only be used once per calendar month");
        }

        double net = medicineCost;
        for (String coupon : coupons) {
            if (!VALID.contains(coupon)) {
                throw new IllegalArgumentException("unknown coupon: " + coupon);
            }
            if (coupon.equals("BOGO")) {
                throw new IllegalArgumentException("BOGO is unavailable in this release");
            }
            if (history.couponUsedInCalendarMonth(ownerId, coupon, purchaseDate)) {
                throw new IllegalArgumentException("coupon already used this calendar month");
            }

            if (coupon.equals("10% Off")) {
                net *= 0.90;
            } else if (coupon.equals("$10 Off")) {
                net = Math.max(0.0, net - 10.0);
            }
        }
        return net;
    }
}
