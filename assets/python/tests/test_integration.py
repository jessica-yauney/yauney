from __future__ import annotations

import sys
from datetime import date
from pathlib import Path
import unittest

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "src"))

from parvocure.data_loader import load_reference_data
from parvocure.models import CheckoutRequest
from parvocure.order_history import OrderHistory
from parvocure.service import CheckoutService


DATA = load_reference_data(ROOT / "data")


class CheckoutIntegrationTests(unittest.TestCase):
    def test_us_end_to_end(self):
        service = CheckoutService(DATA)
        quote = service.checkout(
            CheckoutRequest(
                owner_id="owner-1",
                owner_location="Canada",
                delivery_location="California",
                dog_breed="Labrador Retriever",
                dog_age_years=2,
                dog_weight_kg=20,
                coupons=("10% Off",),
                purchase_date=date(2026, 3, 10),
            )
        )
        self.assertAlmostEqual(quote.dosage_ml, 37.96)
        self.assertEqual(quote.dosage_display_unit, "fl oz")
        self.assertAlmostEqual(quote.net_cost, 1.1552234433480002)
        self.assertAlmostEqual(quote.total_cost, 6.811912969633067)

    def test_non_us_uses_ml_and_delivery_location(self):
        service = CheckoutService(DATA)
        quote = service.checkout(
            CheckoutRequest(
                owner_id="owner-2",
                owner_location="California",
                delivery_location="Canada",
                dog_breed="Beagle",
                dog_age_years=4,
                dog_weight_kg=10,
                coupons=(),
                purchase_date=date(2026, 4, 1),
            )
        )
        self.assertEqual(quote.dosage_display_unit, "mL")
        self.assertAlmostEqual(quote.dosage_display_value, quote.dosage_ml)
        self.assertAlmostEqual(quote.shipping_cost, 15.5901966)
        self.assertGreater(quote.tax_amount, 0)

    def test_order_limit_and_coupon_history_cross_components(self):
        history = OrderHistory()
        service = CheckoutService(DATA, history)
        first = CheckoutRequest(
            "owner-3",
            "California",
            "Utah",
            "Poodle",
            3,
            12,
            ("10% Off",),
            date(2026, 5, 1),
        )
        service.checkout(first)

        with self.assertRaises(ValueError):
            service.checkout(
                CheckoutRequest(
                    "owner-3",
                    "California",
                    "Utah",
                    "Poodle",
                    3,
                    12,
                    (),
                    date(2026, 5, 20),
                )
            )

        # Exactly 30 days later is allowed by the dose rule, but the same coupon
        # is still in the same calendar month here only if the month matches.
        quote = service.checkout(
            CheckoutRequest(
                "owner-3",
                "California",
                "Utah",
                "Poodle",
                3,
                12,
                ("$10 Off",),
                date(2026, 5, 31),
            )
        )
        self.assertEqual(quote.tax_amount, 0.0)


if __name__ == "__main__":
    unittest.main()
