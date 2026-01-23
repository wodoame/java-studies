package leetcode.greedy;

import java.util.Arrays;

// leetcode 561
public class ArrayPartition {
    static void main() {
        int[] nums = {1, 4, 3, 2};
        System.out.println(arrayPairSum(nums));
    }
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int sum = 0;
        while(i < nums.length){
            sum += nums[i];
            i += 2;
        }
        return sum;
    }
}
