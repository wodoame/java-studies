package leetcode;

import java.util.Arrays;
// leetcode 1480
public class RunningSum {
    static void main() {
     int[] nums = {3, 1, 2, 10, 1};
        System.out.println(Arrays.toString(runningSum(nums)));
    }

    static int[] runningSum(int[] nums){
        for (int i = 1; i < nums.length; i++) nums[i] += nums[i - 1];
        return nums;
    }
}
