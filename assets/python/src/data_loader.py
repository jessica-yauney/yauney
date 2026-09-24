from __future__ import annotations

import csv
from dataclasses import dataclass
from pathlib import Path


@dataclass(frozen=True)
class ReferenceData:
    breed_base: dict[str, float]
    tax_rate_percent: dict[str, float]
    shipping_fee: dict[str, float]


def load_breed_base(path: str | Path) -> dict[str, float]:
    breed_base = {}

    with open(path, newline="", encoding="utf-8") as file:
        reader = csv.DictReader(file)

        for row in reader:
            breed = row["breed"]
            dosage = float(row["base_dosage"])
            breed_base[breed] = dosage

    return breed_base


def load_location_rates(
    path: str | Path,
) -> tuple[dict[str, float], dict[str, float]]:
    tax_rates = {}
    shipping_fees = {}

    with open(path, newline="", encoding="utf-8") as file:
        reader = csv.DictReader(file)

        for row in reader:
            location = row["location"]
            tax_rate = float(row["tax_rate"])
            shipping_fee = float(row["shipping_fee"])

            # Fix the malformed row in the provided data.
            if location == "PennsylvaniaRhode Island":
                tax_rates["Pennsylvania"] = tax_rate
                tax_rates["Rhode Island"] = tax_rate

                shipping_fees["Pennsylvania"] = shipping_fee
                shipping_fees["Rhode Island"] = shipping_fee
            else:
                tax_rates[location] = tax_rate
                shipping_fees[location] = shipping_fee

    return tax_rates, shipping_fees


def load_reference_data(
    data_dir: str | Path = "data",
) -> ReferenceData:
    data_dir = Path(data_dir)

    breed_base = load_breed_base(
        data_dir / "breed_base.csv"
    )

    tax_rates, shipping_fees = load_location_rates(
        data_dir / "location_rates.csv"
    )

    return ReferenceData(
        breed_base,
        tax_rates,
        shipping_fees,
    )