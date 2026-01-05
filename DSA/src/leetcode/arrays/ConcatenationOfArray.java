package leetcode.temp.arrays;

import java.util.Arrays;

// leetcode 1929
public class ConcatenationOfArray {
    static void main() {
        System.out.println(Arrays.toString(getConcatenation(new int[]{1, 2})));
    }
    public static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        System.arraycopy(nums, 0, ans, 0, nums.length);
        System.arraycopy(nums, 0, ans, n, nums.length);
        return ans;
    }
}
