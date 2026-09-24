from __future__ import annotations

import sys
from datetime import date
from pathlib import Path
import unittest

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "src"))

from parvocure.data_loader import load_reference_data
from parvocure.discount import DiscountCalculator
from parvocure.dog_age import DogAgeCalculator
from parvocure.dosage import DosageCalculator
from parvocure.medicine_cost import MedicineCostCalculator
from parvocure.order_history import OrderHistory
from parvocure.shipping import ShippingCalculator
from parvocure.tax import TaxCalculator
from parvocure.total import TotalCostCalculator


DATA = load_reference_data(ROOT / "data")


class DogAgeTests(unittest.TestCase):
    def test_fractional_age(self):
        self.assertAlmostEqual(DogAgeCalculator().calculate(2.5), 17.5)

    def test_negative_age_rejected(self):
        with self.assertRaises(ValueError):
            DogAgeCalculator().calculate(-0.1)


class DosageTests(unittest.TestCase):
    def setUp(self):
        self.calc = DosageCalculator(DATA.breed_base)

    def test_formula_uses_human_age(self):
        self.assertAlmostEqual(
            self.calc.calculate_ml("Labrador Retriever", 2, 20), 37.96
        )
        self.assertNotEqual(
            self.calc.calculate_ml("Labrador Retriever", 2, 20),
            self.calc.calculate_ml("Labrador Retriever", 5, 20),
        )

    def test_unknown_breed_rejected(self):
        with self.assertRaises(ValueError):
            self.calc.calculate_ml("Definitely Not A Breed", 2, 20)


class MedicineCostTests(unittest.TestCase):
    def test_price_per_ml(self):
        self.assertAlmostEqual(MedicineCostCalculator().calculate(100), 3.3814057)

    def test_negative_dosage_rejected(self):
        with self.assertRaises(ValueError):
            MedicineCostCalculator().calculate(-1)


class DiscountTests(unittest.TestCase):
    def setUp(self):
        self.calc = DiscountCalculator()
        self.history = OrderHistory()

    def test_coupons_are_sequential(self):
        first_ten_then_percent = self.calc.calculate(
            100, ("$10 Off", "10% Off"), "A", date(2026, 3, 2), self.history
        )
        percent_then_ten = self.calc.calculate(
            100, ("10% Off", "$10 Off"), "B", date(2026, 3, 2), self.history
        )
        self.assertAlmostEqual(first_ten_then_percent, 81)
        self.assertAlmostEqual(percent_then_ten, 80)

    def test_coupon_reuse_is_calendar_month(self):
        self.history.record("A", 90, 0, 5, date(2026, 1, 2), ("10% Off",))
        with self.assertRaises(ValueError):
            self.calc.calculate(
                100, ("10% Off",), "A", date(2026, 1, 31), self.history
            )
        self.assertAlmostEqual(
            self.calc.calculate(
                100, ("10% Off",), "A", date(2026, 2, 1), self.history
            ),
            90,
        )

    def test_bogo_and_duplicate_coupon_are_rejected(self):
        with self.assertRaises(ValueError):
            self.calc.calculate(100, ("BOGO",), "A", date(2026, 2, 1), self.history)
        with self.assertRaises(ValueError):
            self.calc.calculate(
                100,
                ("10% Off", "10% Off"),
                "A",
                date(2026, 2, 1),
                self.history,
            )


class ShippingTests(unittest.TestCase):
    def setUp(self):
        self.calc = ShippingCalculator(DATA.shipping_fee)

    def test_shipping_is_fee_only(self):
        self.assertAlmostEqual(self.calc.calculate("Utah"), 5.44925658)

    def test_combined_row_is_split(self):
        self.assertAlmostEqual(
            self.calc.calculate("Pennsylvania"), self.calc.calculate("Rhode Island")
        )


class TaxTests(unittest.TestCase):
    def setUp(self):
        self.calc = TaxCalculator(DATA.tax_rate_percent)

    def test_table_value_is_percent(self):
        self.assertAlmostEqual(self.calc.calculate(100, "California"), 5.59208841)

    def test_zero_tax_is_valid(self):
        self.assertEqual(self.calc.calculate(12.34, "Utah"), 0.0)


class TotalTests(unittest.TestCase):
    def test_total_includes_shipping(self):
        self.assertAlmostEqual(TotalCostCalculator().calculate(10, 5, 1), 16)

    def test_no_internal_rounding(self):
        self.assertAlmostEqual(
            TotalCostCalculator().calculate(1.111, 2.222, 3.333), 6.666
        )


class OrderHistoryTests(unittest.TestCase):
    def test_rolling_30_day_boundary(self):
        history = OrderHistory()
        history.record("A", 10, 1, 5, date(2026, 1, 15), ())
        self.assertTrue(history.has_order_within_30_days("A", date(2026, 2, 10)))
        self.assertFalse(history.has_order_within_30_days("A", date(2026, 2, 14)))

    def test_persists_required_cost_fields(self):
        history = OrderHistory()
        history.record("A", 10.5, 1.2, 5.3, date(2026, 1, 15), ("10% Off",))
        record = history.all_records()[0]
        self.assertEqual(record.owner_id, "A")
        self.assertEqual(record.net_cost, 10.5)
        self.assertEqual(record.tax_amount, 1.2)
        self.assertEqual(record.shipping_cost, 5.3)
        self.assertEqual(record.purchase_date, date(2026, 1, 15))


if __name__ == "__main__":
    unittest.main()
