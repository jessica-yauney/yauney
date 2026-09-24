package parvocure.tests;

import java.nio.file.Path;
import parvocure.*;

public class IntegrationTests {
    public static void main(String[] args) {
        TestSupport t = new TestSupport();
        DataTables data = DataLoader.load(Path.of("data"));

        // Starter integration smoke test.
        t.ok(
                !data.shippingFee().isEmpty(),
                "location data loads");

        // TODO: add end-to-end checkout tests that exercise
        // multiple components together.

        t.finish();
    }
}
