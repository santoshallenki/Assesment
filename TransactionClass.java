public class TransactionClass {
}
// Import necessary libraries
import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

// Define the Transaction class
class Transaction {
    private int customerId;
    private double amount;

    public Transaction(int customerId, double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }
}

// Define the RewardsCalculator class
class RewardsCalculator {

    private static final double REWARD_RATE_ABOVE_100 = 2.0;
    private static final double REWARD_RATE_BETWEEN_50_AND_100 = 1.0;

    public Map&lt;Integer, List&lt;Double&gt;&gt; calculateRewards(List&lt;Transaction&gt; transactions) {
        Map&lt;Integer, List&lt;Double&gt;&gt; rewardsMap = new HashMap&lt;&gt;();

        for (Transaction transaction : transactions) {
            int customerId = transaction.getCustomerId();
            double amount = transaction.getAmount();
            double rewardPoints = 0.0;

            if (amount &gt; 100) {
                rewardPoints += (amount - 100) * REWARD_RATE_ABOVE_100;
                amount = 100;
            }

            if (amount &gt; 50) {
                rewardPoints += (amount - 50) * REWARD_RATE_BETWEEN_50_AND_100;
            }

            if (!rewardsMap.containsKey(customerId)) {
                rewardsMap.put(customerId, new ArrayList&lt;&gt;());
            }
            rewardsMap.get(customerId).add(rewardPoints);
        }

        return rewardsMap;
    }
}

// Define the RewardsController class
@RestController
public class RewardsController {
    private RewardsCalculator rewardsCalculator;

    public RewardsController(RewardsCalculator rewardsCalculator) {
        this.rewardsCalculator = rewardsCalculator;
    }

    @PostMapping(&quot;/rewards&quot;)
    public Map&lt;Integer, List&lt;Double&gt;&gt; calculateRewards(@RequestBody List&lt;Transaction&gt; transactions) {
        return rewardsCalculator.calculateRewards(transactions);
    }
}