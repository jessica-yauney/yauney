package parvocure.tests;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import parvocure.*;

public class UnitTests {
    public static void main(String[] args) {
        TestSupport t = new TestSupport();
        DataTables data = DataLoader.load(Path.of("/Users/jessica/yauney/assets/java/parvocure/tests"));

        DogAgeCalculator age = new DogAgeCalculator();
        t.near(age.calculate(2.5), 17.5, 1e-12, "dog age fractional");
        t.throwsIA(() -> age.calculate(-0.1), "dog age rejects negative");

        DosageCalculator dosage = new DosageCalculator(data.breedBase());
        t.near(dosage.calculateMl("Labrador Retriever", 2, 20), 37.96, 1e-12,
                "dosage formula");
        t.ok(dosage.calculateMl("Labrador Retriever", 2, 20)
                        != dosage.calculateMl("Labrador Retriever", 5, 20),
                "dosage uses age");
        t.throwsIA(() -> dosage.calculateMl("No Such Breed", 2, 20),
                "dosage rejects unknown breed");

        MedicineCostCalculator medicine = new MedicineCostCalculator();
        t.near(medicine.calculate(100), 3.3814057, 1e-12, "medicine price per ml");
        t.throwsIA(() -> medicine.calculate(-1), "medicine rejects negative dosage");

        OrderHistory history = new OrderHistory();
        DiscountCalculator discount = new DiscountCalculator();
        t.near(discount.calculate(100, List.of("$10 Off", "10% Off"), "A",
                        LocalDate.of(2026, 3, 2), history),
                81, 1e-12, "discount sequential order 1");
        t.near(discount.calculate(100, List.of("10% Off", "$10 Off"), "B",
                        LocalDate.of(2026, 3, 2), history),
                80, 1e-12, "discount sequential order 2");
        t.throwsIA(() -> discount.calculate(10, List.of("BOGO"), "A",
                        LocalDate.of(2026, 3, 2), history),
                "discount rejects BOGO");
        t.throwsIA(() -> discount.calculate(100, List.of("10% Off", "10% Off"), "A",
                        LocalDate.of(2026, 3, 2), history),
                "discount rejects duplicate type");

        history.record("A", 90, 0, 5, LocalDate.of(2026, 1, 2), List.of("10% Off"));
        t.throwsIA(() -> discount.calculate(100, List.of("10% Off"), "A",
                        LocalDate.of(2026, 1, 31), history),
                "coupon reuse same calendar month");
        t.near(discount.calculate(100, List.of("10% Off"), "A",
                        LocalDate.of(2026, 2, 1), history),
                90, 1e-12, "coupon reusable next month");

        ShippingCalculator shipping = new ShippingCalculator(data.shippingFee());
        t.near(shipping.calculate("Utah"), 5.44925658, 1e-12, "shipping fee only");
        t.near(shipping.calculate("Pennsylvania"), shipping.calculate("Rhode Island"),
                1e-12, "combined PA/RI row split");

        TaxCalculator tax = new TaxCalculator(data.taxRatePercent());
        t.near(tax.calculate(100, "California"), 5.59208841, 1e-12,
                "tax rate interpreted as percent");
        t.near(tax.calculate(12.34, "Utah"), 0, 1e-12, "zero tax valid");

        TotalCostCalculator total = new TotalCostCalculator();
        t.near(total.calculate(10, 5, 1), 16, 1e-12, "total includes shipping");
        t.near(total.calculate(1.111, 2.222, 3.333), 6.666, 1e-12,
                "total keeps precision");

        OrderHistory rolling = new OrderHistory();
        rolling.record("A", 10, 1, 5, LocalDate.of(2026, 1, 15), List.of());
        t.ok(rolling.hasOrderWithin30Days("A", LocalDate.of(2026, 2, 10)),
                "rolling 30 day limit inside window");
        t.ok(!rolling.hasOrderWithin30Days("A", LocalDate.of(2026, 2, 14)),
                "rolling 30 day exact boundary allowed");
        HistoryRecord record = rolling.allRecords().get(0);
        t.near(record.netCost(), 10, 1e-12, "history persists net cost");
        t.near(record.taxAmount(), 1, 1e-12, "history persists tax");
        t.near(record.shippingCost(), 5, 1e-12, "history persists shipping");

        t.finish();
    }
}
