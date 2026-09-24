class TaxCalculator:
    def __init__(self, tax_rate_percent):
        self._tax_rate_percent = dict(tax_rate_percent)

    def calculate(self, net_cost: float, delivery_location: str) -> float:
        # TODO: look up the tax value and calculate the tax amount.
        raise NotImplementedError
