from __future__ import annotations

from collections.abc import Mapping


class ShippingCalculator:
    def __init__(self, shipping_fees: Mapping[str, float]):
        self._shipping_fees = dict(shipping_fees)

    def calculate(self, delivery_location: str) -> float:
        try:
            return self._shipping_fees[delivery_location]
        except KeyError as exc:
            raise ValueError(f"unsupported delivery location: {delivery_location}") from exc
