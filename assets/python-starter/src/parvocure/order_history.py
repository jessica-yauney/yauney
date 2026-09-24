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

    def has_order_within_30_days(
        self,
        owner_id: str,
        purchase_date: date,
    ) -> bool:
        # TODO: implement the ordering-frequency rule.
        raise NotImplementedError

    def coupon_used_in_calendar_month(
        self,
        owner_id: str,
        coupon: str,
        purchase_date: date,
    ) -> bool:
        # TODO: implement the coupon-reuse rule.
        raise NotImplementedError

    def record(
        self,
        owner_id: str,
        net_cost: float,
        tax_amount: float,
        shipping_cost: float,
        purchase_date: date,
        coupons: tuple[str, ...],
    ) -> None:
        # TODO: save a HistoryRecord.
        raise NotImplementedError

    def all_records(self) -> tuple[HistoryRecord, ...]:
        return tuple(self._records)
