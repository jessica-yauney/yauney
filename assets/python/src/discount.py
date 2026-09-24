from __future__ import annotations

import math
from datetime import date
from collections.abc import Sequence

from .order_history import OrderHistory


class DiscountCalculator:
    VALID_COUPONS = frozenset({"10% Off", "$10 Off", "BOGO"})

    def calculate(
        self,
        medicine_cost: float,
        coupons: Sequence[str],
        owner_id: str,
        purchase_date: date,
        history: OrderHistory,
    ) -> float:
        if not math.isfinite(medicine_cost) or medicine_cost < 0:
            raise ValueError("medicine cost must be a finite, nonnegative number")
        if len(coupons) > 2:
            raise ValueError("maximum of two coupons per transaction")
        if len(set(coupons)) != len(coupons):
            raise ValueError("a coupon type may only be used once per calendar month")

        net = medicine_cost
        for coupon in coupons:
            if coupon not in self.VALID_COUPONS:
                raise ValueError(f"unknown coupon: {coupon}")
            if coupon == "BOGO":
                raise ValueError("BOGO is unavailable in this release")
            if history.coupon_used_in_calendar_month(owner_id, coupon, purchase_date):
                raise ValueError("coupon already used this calendar month")

            # Product decision: coupons are applied sequentially in submitted order.
            if coupon == "10% Off":
                net *= 0.90
            elif coupon == "$10 Off":
                net = max(0.0, net - 10.0)

        return net
