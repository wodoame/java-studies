package leetcode.arrays;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    static void main() {
        System.out.println(Arrays.toString(productExceptSelf( new int[]{1,2,3,4})));
    }
    public static int[] productExceptSelf(int[] nums) {
        int left = 1;
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
           res[i] = left;
           left *= nums[i];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
