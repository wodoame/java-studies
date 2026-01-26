package leetcode.dp;
import java.util.Map;
import java.util.HashMap;

// leetcode 70
// This solution looks just like the Fibonnacci sequence
public class ClimbingStairs {
    public int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return climbStairsRec(n, memo);
    }

    public int climbStairsRec(int n, Map<Integer, Integer> memo){
        if(n == 0)return 1;
        if(n < 0)return 0;
        if(memo.containsKey(n))return memo.get(n);
        int res = climbStairsRec(n - 1, memo) + climbStairsRec(n - 2, memo);
        memo.put(n, res);
        return res;
    }
}
