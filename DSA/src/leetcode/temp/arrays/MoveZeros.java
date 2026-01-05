package leetcode.temp.arrays;

import java.util.Arrays;

// leetcode 283
public class MoveZeros {
    static void main() {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
    public static void moveZeroes(int[] nums) {
        int j = 0; // keeps track of the position to insert a non-zero element
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i] != 0)swap(nums, i, j++);
        }
    }

    static void swap(int[] nums, int i, int j){
        if(i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
