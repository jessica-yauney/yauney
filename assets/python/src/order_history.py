from __future__ import annotations

from dataclasses import dataclass
from datetime import date


@dataclass(frozen=True)
class HistoryRecord:
    owner_id: str
    net_cost: float
    tax_amount: float
    shipping_cost: float
    purchase_date: date
    coupons: tuple[str, ...]


class OrderHistory:
    def __init__(self) -> None:
        self._records: list[HistoryRecord] = []

    def has_order_within_30_days(self, owner_id: str, purchase_date: date) -> bool:
        for record in self._records:
            if record.owner_id != owner_id:
                continue
            days = (purchase_date - record.purchase_date).days
            if 0 <= days < 30:
                return True
        return False

    def coupon_used_in_calendar_month(
        self, owner_id: str, coupon: str, purchase_date: date
    ) -> bool:
        return any(
            record.owner_id == owner_id
            and coupon in record.coupons
            and record.purchase_date.year == purchase_date.year
            and record.purchase_date.month == purchase_date.month
            for record in self._records
        )

    def record(
        self,
        owner_id: str,
        net_cost: float,
        tax_amount: float,
        shipping_cost: float,
        purchase_date: date,
        coupons: tuple[str, ...],
    ) -> None:
        self._records.append(
            HistoryRecord(
                owner_id=owner_id,
                net_cost=net_cost,
                tax_amount=tax_amount,
                shipping_cost=shipping_cost,
                purchase_date=purchase_date,
                coupons=tuple(coupons),
            )
        )

    def all_records(self) -> tuple[HistoryRecord, ...]:
        return tuple(self._records)
