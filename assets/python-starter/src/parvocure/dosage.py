class DosageCalculator:
    def __init__(self, breed_base):
        self._breed_base = dict(breed_base)

    def calculate_ml(
        self,
        breed: str,
        human_age_years: float,
        weight_kg: float,
    ) -> float:
        # TODO: validate inputs and implement the clarified dosage rule.
        raise NotImplementedError
