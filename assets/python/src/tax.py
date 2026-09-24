from __future__ import annotations

import math
from collections.abc import Mapping


class TaxCalculator:
    def __init__(self, tax_rate_percent: Mapping[str, float]):
        self._tax_rate_percent = dict(tax_rate_percent)

    def calculate(self, net_cost: float, delivery_location: str) -> float:
        if not math.isfinite(net_cost) or net_cost < 0:
            raise ValueError("net cost must be a finite, nonnegative number")
        try:
            rate_percent = self._tax_rate_percent[delivery_location]
        except KeyError as exc:
            raise ValueError(f"unsupported delivery location: {delivery_location}") from exc
        return net_cost * rate_percent / 100.0
