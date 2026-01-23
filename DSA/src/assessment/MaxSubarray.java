package assessment;

import java.util.Arrays;

public class MaxSubarray {
     static void main() {
       int[] nums = {2, 3, -8, 7, -1, 2, 3};
        System.out.println(Arrays.toString(maxSubarry(nums)));

    }
    public static int[] maxSubarry(int[] nums){
        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0;
        int end = 0;
        int pstart = 0;
        for (int j = 1; j < nums.length; j++) {
           currentSum = Math.max(currentSum + nums[j], nums[j]);
           if(currentSum == nums[j])pstart=j;
           if(currentSum > maxSum){
               maxSum = currentSum;
               end = j;
               start = pstart;
           }
        }
        return new int[]{start, end, maxSum};
    }
}
