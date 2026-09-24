import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "src"))

from parvocure.data_loader import load_reference_data


DATA = load_reference_data(ROOT / "data")


class ParvoCureUnitTests(unittest.TestCase):
    def test_example(self):
        # Replace this example with tests for individual components.
        self.assertIn("Labrador Retriever", DATA.breed_base)

    # TODO: add unit tests for:
    # - dog age
    # - dosage
    # - medicine cost
    # - discounts
    # - shipping
    # - tax
    # - total cost
    # - order history


if __name__ == "__main__":
    unittest.main()
