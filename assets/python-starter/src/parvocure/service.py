from .data_loader import ReferenceData, load_reference_data
from .discount import DiscountCalculator
from .dog_age import DogAgeCalculator
from .dosage import DosageCalculator
from .medicine_cost import MedicineCostCalculator
from .models import CheckoutRequest, OrderQuote
from .order_history import OrderHistory
from .shipping import ShippingCalculator
from .tax import TaxCalculator
from .total import TotalCostCalculator


class CheckoutService:
    def __init__(
        self,
        data: ReferenceData | None = None,
        history: OrderHistory | None = None,
    ) -> None:
        data = data or load_reference_data()

        self.history = history or OrderHistory()
        self.age = DogAgeCalculator()
        self.dosage = DosageCalculator(data.breed_base)
        self.medicine_cost = MedicineCostCalculator()
        self.discount = DiscountCalculator()
        self.shipping = ShippingCalculator(data.shipping_fee)
        self.tax = TaxCalculator(data.tax_rate_percent)
        self.total = TotalCostCalculator()

    def checkout(self, request: CheckoutRequest) -> OrderQuote:
        # TODO:
        # 1. Validate the request.
        # 2. Enforce order-history rules.
        # 3. Call the component calculators.
        # 4. Decide how dosage should be displayed.
        # 5. Record the purchase.
        # 6. Return an OrderQuote.
        raise NotImplementedError
