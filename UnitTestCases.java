public class UnitTestCases {
}

import unittest
        from unittest.mock import Mock
        from main import RewardsCalculator

class TestRewardsCalculator(unittest.TestCase):
        """
    This class contains unit tests for the `RewardsCalculator` class.
    """
        def setUp(self):
        """
        Set up a mock `Transaction` object and a `RewardsCalculator` object.
        """
        self.transaction = Mock()
        self.transaction.getCustomerId.return_value = 1
        self.transaction.getAmount.return_value = 150.0

        self.rewards_calculator = RewardsCalculator()

        def test_calculate_rewards_above_100(self):
        """
        Tests that the rewards are calculated correctly for a transaction above 100.
        """
        self.transaction.getAmount.return_value = 150.0
        rewards_map = self.rewards_calculator.calculateRewards([self.transaction])
        self.assertEqual(rewards_map[1][0], 100.0)

        def test_calculate_rewards_between_50_and_100(self):
        """
        Tests that the rewards are calculated correctly for a transaction between 50 and 100.
        """
        self.transaction.getAmount.return_value = 75.0
        rewards_map = self.rewards_calculator.calculateRewards([self.transaction])
        self.assertEqual(rewards_map[1][0], 25.0)

        def test_calculate_rewards_below_50(self):
        """
        Tests that the rewards are calculated correctly for a transaction below 50.
        """
        self.transaction.getAmount.return_value = 25.0
        rewards_map = self.rewards_calculator.calculateRewards([self.transaction])
        self.assertEqual(rewards_map[1][0], 0.0)

        def test_calculate_rewards_multiple_transactions(self):
        """
        Tests that the rewards are calculated correctly for multiple transactions.
        """
        transaction1 = Mock()
        transaction1.getCustomerId.return_value = 1
        transaction1.getAmount.return_value = 150.0

        transaction2 = Mock()
        transaction2.getCustomerId.return_value = 2
        transaction2.getAmount.return_value = 75.0

        rewards_map = self.rewards_calculator.calculateRewards([transaction1, transaction2])
        self.assertEqual(rewards_map[1][0], 100.0)
        self.assertEqual(rewards_map[2][0], 25.0)
