import math


class TotalCostCalculator:
    def calculate(self, net_cost: float, shipping_cost: float, tax_amount: float) -> float:
        values = (net_cost, shipping_cost, tax_amount)
        if any(not math.isfinite(value) or value < 0 for value in values):
            raise ValueError("total inputs must be finite and nonnegative")
        return net_cost + shipping_cost + tax_amount
