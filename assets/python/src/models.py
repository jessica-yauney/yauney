from __future__ import annotations

from dataclasses import dataclass
from datetime import date


@dataclass(frozen=True)
class CheckoutRequest:
    owner_id: str
    owner_location: str
    delivery_location: str
    dog_breed: str
    dog_age_years: float
    dog_weight_kg: float
    coupons: tuple[str, ...]
    purchase_date: date


@dataclass(frozen=True)
class OrderQuote:
    dog_age_display: float
    dosage_ml: float
    dosage_display_value: float
    dosage_display_unit: str
    medicine_cost: float
    discount_amount: float
    net_cost: float
    shipping_cost: float
    tax_amount: float
    total_cost: float
