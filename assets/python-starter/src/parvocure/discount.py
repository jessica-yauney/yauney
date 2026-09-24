from datetime import date


class DiscountCalculator:
    def calculate(
        self,
        medicine_cost: float,
        coupons,
        owner_id: str,
        purchase_date: date,
        history,
    ) -> float:
        # TODO: validate coupons and implement the clarified discount rules.
        raise NotImplementedError
