package leetcode.greedy;

import java.util.Arrays;

public class LargestNumber {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{10, 2}));
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public static String largestNumber(int[] nums) {
        // Convert int array to string array
        String[] sNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sNums[i] = String.valueOf(nums[i]);
        }

        // Custom comparator: compare (b+a) with (a+b)
        // If "b" + "a" is larger than "a" + "b", then "b" should come first.
        Arrays.sort(sNums, (a, b) -> (b + a).compareTo(a + b));

        // Edge case: multiple zeros (e.g., [0, 0]) answer should be "0", not "00"
        if (sNums[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : sNums) {
            sb.append(s);
        }
        return sb.toString();
    }
}