from __future__ import annotations

import math
from collections.abc import Mapping


class DosageCalculator:
    def __init__(self, breed_base: Mapping[str, float]):
        self._breed_base = dict(breed_base)

    def calculate_ml(self, breed: str, human_age_years: float, weight_kg: float) -> float:
        if breed not in self._breed_base:
            raise ValueError(f"unknown breed: {breed}")
        if not math.isfinite(human_age_years) or human_age_years < 0:
            raise ValueError("dog age must be a finite, nonnegative number")
        if not math.isfinite(weight_kg) or weight_kg <= 0:
            raise ValueError("dog weight must be a finite, positive number")

        base = self._breed_base[breed]
        dosage_ml = base * ((weight_kg + 5.0) - human_age_years / 3.0)
        if dosage_ml < 0:
            raise ValueError("the dosage formula produced a negative dosage")
        return dosage_ml
