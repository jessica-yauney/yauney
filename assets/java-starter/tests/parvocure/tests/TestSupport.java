package parvocure.tests;

import java.util.Objects;

public final class TestSupport {
    private int passed = 0;
    private int failed = 0;

    public void near(
            double actual,
            double expected,
            double epsilon,
            String name) {
        if (Math.abs(actual - expected) > epsilon) {
            failed++;
            System.out.println("FAIL " + name);
        } else {
            passed++;
            System.out.println("PASS " + name);
        }
    }

    public void equal(Object actual, Object expected, String name) {
        if (!Objects.equals(actual, expected)) {
            failed++;
            System.out.println("FAIL " + name);
        } else {
            passed++;
            System.out.println("PASS " + name);
        }
    }

    public void ok(boolean condition, String name) {
        if (!condition) {
            failed++;
            System.out.println("FAIL " + name);
        } else {
            passed++;
            System.out.println("PASS " + name);
        }
    }

    public void finish() {
        System.out.printf(
                "Passed: %d Failed: %d%n",
                passed,
                failed);

        if (failed > 0) {
            System.exit(1);
        }
    }
}
