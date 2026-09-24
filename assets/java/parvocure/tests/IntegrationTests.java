package parvocure.tests;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import parvocure.*;

public class IntegrationTests {
    public static void main(String[] args) {
        TestSupport t = new TestSupport();
        DataTables data = DataLoader.load(Path.of("/Users/jessica/yauney/assets/java/parvocure/tests"));

        CheckoutService usService = new CheckoutService(data);
        OrderQuote us = usService.checkout(new CheckoutRequest(
                "owner-1", "California", "California", "Labrador Retriever",
                2, 20, List.of("10% Off"), LocalDate.of(2026, 3, 10)));
        t.near(us.dosageMl(), 37.96, 1e-12, "integration US dosage");
        t.equal(us.dosageDisplayUnit(), "fl oz", "integration US display unit");
        t.near(us.netCost(), 1.1552234433480002, 1e-12, "integration US net");
        t.near(us.totalCost(), 6.811912969633067, 1e-12, "integration US total");

        CheckoutService internationalService = new CheckoutService(data);
        OrderQuote international = internationalService.checkout(new CheckoutRequest(
                "owner-2", "Canada", "Canada", "Beagle",
                4, 10, List.of(), LocalDate.of(2026, 4, 1)));
        t.equal(international.dosageDisplayUnit(), "mL", "integration non-US unit");
        t.near(international.dosageDisplayValue(), international.dosageMl(), 1e-12,
                "integration non-US display value");
        t.near(international.shippingCost(), 15.5901966, 1e-12,
                "integration uses delivery location");
        t.ok(international.taxAmount() > 0, "integration non-US tax");

        OrderHistory history = new OrderHistory();
        CheckoutService historyService = new CheckoutService(data, history);
        historyService.checkout(new CheckoutRequest(
                "owner-3", "California", "Utah", "Poodle",
                3, 12, List.of("10% Off"), LocalDate.of(2026, 5, 1)));
        t.throwsIA(() -> historyService.checkout(new CheckoutRequest(
                        "owner-3", "California", "Utah", "Poodle",
                        3, 12, List.of(), LocalDate.of(2026, 5, 20))),
                "integration rolling order limit");
        OrderQuote boundary = historyService.checkout(new CheckoutRequest(
                "owner-3", "California", "Utah", "Poodle",
                3, 12, List.of("$10 Off"), LocalDate.of(2026, 5, 31)));
        t.near(boundary.taxAmount(), 0, 1e-12, "integration zero tax at boundary");

        t.finish();
    }
}
