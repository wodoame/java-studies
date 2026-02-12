package leetcode.dp;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = cost[n - 1];
        for (int i = n - 2; i > -1 ; i--) {
           dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }
        return Math.min(dp[0], dp[1]);
    }
    // NOTE: since we only need the next two values on every iteration we don't need to build the whole dp array
    // but rather we could just keep track of the next two values directly using two variables.
}
