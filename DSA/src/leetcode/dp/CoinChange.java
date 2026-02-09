package leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    static void main() {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[0] ... dp[amount]
        Arrays.fill(dp, amount + 1); // amount + 1 is an arbitrary large value
        dp[0] = 0;
        for(int a = 1; a < amount + 1; a++){
            for(int coin: coins){
                 if(a - coin >= 0) dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
            }
        }
        return dp[amount] > 0? dp[amount]: -1;
    }
}
