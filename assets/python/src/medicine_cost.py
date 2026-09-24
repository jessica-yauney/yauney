import math


class MedicineCostCalculator:
    PRICE_PER_ML = 0.033814057

    def calculate(self, dosage_ml: float) -> float:
        if not math.isfinite(dosage_ml) or dosage_ml < 0:
            raise ValueError("dosage must be a finite, nonnegative number")
        return dosage_ml * self.PRICE_PER_ML
