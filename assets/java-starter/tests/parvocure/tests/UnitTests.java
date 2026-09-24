package parvocure.tests;

import java.nio.file.Path;
import parvocure.*;

public class UnitTests {
    public static void main(String[] args) {
        TestSupport t = new TestSupport();
        DataTables data = DataLoader.load(Path.of("data"));

        // Starter smoke test.
        t.ok(
                data.breedBase().containsKey("Labrador Retriever"),
                "reference breed data loads");

        // TODO: add unit tests for each component.

        t.finish();
    }
}
