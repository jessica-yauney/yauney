class ShippingCalculator:
    def __init__(self, shipping_fees):
        self._shipping_fees = dict(shipping_fees)

    def calculate(self, delivery_location: str) -> float:
        # TODO: look up and return the shipping cost.
        # Give a clear error if the location is unsupported.
        raise NotImplementedError
