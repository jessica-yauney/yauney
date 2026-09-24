import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "src"))

from parvocure.data_loader import load_reference_data


DATA = load_reference_data(ROOT / "data")


class ParvoCureIntegrationTests(unittest.TestCase):
    def test_data_loads(self):
        # Starter integration smoke test.
        self.assertGreater(len(DATA.breed_base), 0)
        self.assertGreater(len(DATA.shipping_fee), 0)

    # TODO: add end-to-end checkout tests that exercise
    # multiple components together.


if __name__ == "__main__":
    unittest.main()
