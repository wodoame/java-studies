package assessment;

import java.util.Arrays;

public class Assignment1 {

    public static void main(String[] args) {
        // TEST PROBLEM 1: Maximum Subarray Sum
        System.out.println("PROBLEM 1: Maximum Subarray Sum (Kadane's Algorithm)");
        System.out.println("-------------------------------------------------------");

        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.print("Input: ");
        printArray(nums1);

        int maxSum = maxSubarraySum(nums1);
        System.out.println("Maximum Subarray Sum: " + maxSum);

        // TEST PROBLEM 2: Rotate Array
        System.out.println("\nPROBLEM 2: Rotate Array");
        System.out.println("-------------------------");

        int[] nums2a = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.print("Input: ");
        printArray(nums2a);
        System.out.println("k = " + k);

        int[] nums2aCopy = nums2a.clone();
        rotateArray(nums2aCopy, k);
        System.out.print("Output: ");
        printArray(nums2aCopy);

        // TEST PROBLEM 3: Move Zeroes
        System.out.println("\nPROBLEM 3: Move Zeroes");
        System.out.println("-----------------------");

        int[] nums3 = {0, 1, 0, 3, 12};
        System.out.print("Input: ");
        printArray(nums3);

        moveZeroes(nums3);
        System.out.print("Output: ");
        printArray(nums3);

    }

    // Question 1
    public static int maxSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Question 2
    public static void rotateArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;

        if (k == 0) {
            return;
        }

        // Create temporary array
        int[] temp = new int[n];

        // Copy elements to their new positions
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }

        // Copy back to original array
        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }

    // Question 3
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int nonZeroPos = 0; // Position to place next non-zero element

        // Move all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap only if positions are different (optimization)
                if (i != nonZeroPos) {
                    int temp = nums[nonZeroPos];
                    nums[nonZeroPos] = nums[i];
                    nums[i] = temp;
                }
                nonZeroPos++;
            }
        }
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
