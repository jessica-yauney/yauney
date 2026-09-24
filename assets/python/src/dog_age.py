import math


class DogAgeCalculator:
    """For this fictional release, dog years = human years * 7."""

    def calculate(self, human_age_years: float) -> float:
        if not math.isfinite(human_age_years) or human_age_years < 0:
            raise ValueError("dog age must be a finite, nonnegative number")
        return human_age_years * 7.0
