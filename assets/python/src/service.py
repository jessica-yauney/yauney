from __future__ import annotations

from datetime import date

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

ML_PER_FL_OZ = 29.5735295625


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
        self._us_destinations = data.us_destinations

    def checkout(self, request: CheckoutRequest) -> OrderQuote:
        self._validate_request(request)

        if self.history.has_order_within_30_days(request.owner_id, request.purchase_date):
            raise ValueError("owner already ordered a dose within the previous 30 days")

        dog_age_display = self.age.calculate(request.dog_age_years)
        dosage_ml = self.dosage.calculate_ml(
            request.dog_breed, request.dog_age_years, request.dog_weight_kg
        )
        medicine_cost = self.medicine_cost.calculate(dosage_ml)
        net_cost = self.discount.calculate(
            medicine_cost,
            request.coupons,
            request.owner_id,
            request.purchase_date,
            self.history,
        )
        discount_amount = medicine_cost - net_cost
        shipping_cost = self.shipping.calculate(request.delivery_location)
        tax_amount = self.tax.calculate(net_cost, request.delivery_location)
        total_cost = self.total.calculate(net_cost, shipping_cost, tax_amount)

        if request.delivery_location in self._us_destinations:
            dosage_display_value = dosage_ml / ML_PER_FL_OZ
            dosage_display_unit = "fl oz"
        else:
            dosage_display_value = dosage_ml
            dosage_display_unit = "mL"

        self.history.record(
            owner_id=request.owner_id,
            net_cost=net_cost,
            tax_amount=tax_amount,
            shipping_cost=shipping_cost,
            purchase_date=request.purchase_date,
            coupons=request.coupons,
        )

        return OrderQuote(
            dog_age_display=dog_age_display,
            dosage_ml=dosage_ml,
            dosage_display_value=dosage_display_value,
            dosage_display_unit=dosage_display_unit,
            medicine_cost=medicine_cost,
            discount_amount=discount_amount,
            net_cost=net_cost,
            shipping_cost=shipping_cost,
            tax_amount=tax_amount,
            total_cost=total_cost,
        )

    @staticmethod
    def _validate_request(request: CheckoutRequest) -> None:
        if not request.owner_id.strip():
            raise ValueError("owner_id is required")
        if not request.owner_location.strip():
            raise ValueError("owner_location is required")
        if not request.delivery_location.strip():
            raise ValueError("delivery_location is required")
        if not request.dog_breed.strip():
            raise ValueError("dog_breed is required")
        if not isinstance(request.purchase_date, date):
            raise ValueError("purchase_date must be a date")
