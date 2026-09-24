import csv
from dataclasses import dataclass
from pathlib import Path


@dataclass(frozen=True)
class ReferenceData:
    breed_base: dict[str, float]
    tax_rate_percent: dict[str, float]
    shipping_fee: dict[str, float]


def load_breed_base(path: str | Path) -> dict[str, float]:
    result = {}

    with open(path, newline="", encoding="utf-8") as file:
        reader = csv.DictReader(file)
        for row in reader:
            result[row["breed"]] = float(row["base_dosage"])

    return result


def load_location_rates(
    path: str | Path,
) -> tuple[dict[str, float], dict[str, float]]:
    tax_rates = {}
    shipping_fees = {}

    with open(path, newline="", encoding="utf-8") as file:
        reader = csv.DictReader(file)
        for row in reader:
            location = row["location"]
            tax_rates[location] = float(row["tax_rate"])
            shipping_fees[location] = float(row["shipping_fee"])

    return tax_rates, shipping_fees


def load_reference_data(data_dir: str | Path = "data") -> ReferenceData:
    data_dir = Path(data_dir)

    breed_base = load_breed_base(data_dir / "breed_base.csv")
    tax_rates, shipping_fees = load_location_rates(
        data_dir / "location_rates.csv"
    )

    return ReferenceData(
        breed_base=breed_base,
        tax_rate_percent=tax_rates,
        shipping_fee=shipping_fees,
    )
