package assessment;

public class AssignmentCopy {

    public static void main(String[] args) {
        System.out.println("==================== DSA LIVE SESSION PROBLEMS ====================\n");

        // ========== TEST PROBLEM 1: Maximum Subarray Sum ==========
        System.out.println("PROBLEM 1: Maximum Subarray Sum (Kadane's Algorithm)");
        System.out.println("-------------------------------------------------------");

        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.print("Input: ");
        printArray(nums1);

        int maxSum = maxSubarraySum(nums1);
        System.out.println("Maximum Subarray Sum: " + maxSum);

        int[] result = maxSubarraySumWithIndices(nums1);
        System.out.println("Subarray: [" + result[1] + ", " + result[2] + "] (indices)");
        System.out.print("Subarray Elements: [");
        for (int i = result[1]; i <= result[2]; i++) {
            System.out.print(nums1[i]);
            if (i < result[2]) System.out.print(", ");
        }
        System.out.println("]\n");

        // ========== TEST PROBLEM 2: Rotate Array ==========
        System.out.println("PROBLEM 2: Rotate Array");
        System.out.println("-------------------------");

        int[] nums2a = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.print("Input: ");
        printArray(nums2a);
        System.out.println("k = " + k);

        System.out.println("\nMethod 1 (Using Temporary Array):");
        int[] nums2aCopy = nums2a.clone();
        rotateArrayMethod1(nums2aCopy, k);
        System.out.print("Output: ");
        printArray(nums2aCopy);

        System.out.println("\nMethod 2 (In-Place Reversal):");
        int[] nums2bCopy = nums2a.clone();
        rotateArrayMethod2(nums2bCopy, k);
        System.out.print("Output: ");
        printArray(nums2bCopy);
        System.out.println();

        // ========== TEST PROBLEM 3: Move Zeroes ==========
        System.out.println("PROBLEM 3: Move Zeroes");
        System.out.println("-----------------------");

        int[] nums3 = {0, 1, 0, 3, 12};
        System.out.print("Input: ");
        printArray(nums3);

        moveZeroes(nums3);
        System.out.print("Output: ");
        printArray(nums3);

        System.out.println("\nAlternative Method:");
        int[] nums3b = {0, 1, 0, 3, 12};
        System.out.print("Input: ");
        printArray(nums3b);

        moveZeroesAlternative(nums3b);
        System.out.print("Output: ");
        printArray(nums3b);

        System.out.println("\n==================== ALL TESTS COMPLETED ====================");
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

    public static int[] maxSubarraySumWithIndices(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxEndingHere + nums[i]) {
                maxEndingHere = nums[i];
                tempStart = i; // Potential new start
            } else {
                maxEndingHere = maxEndingHere + nums[i];
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        return new int[]{maxSoFar, start, end};
    }

    // Question 2

    /**
     * METHOD 1: Rotate array using a temporary array.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums The input integer array
     * @param k    Number of steps to rotate right
     */
    public static void rotateArrayMethod1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;
        k = k % n; // Handle k > n

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

    /**
     * METHOD 2: Rotate array in-place using the reversal algorithm.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * <p>
     * Algorithm:
     * 1. Reverse the entire array
     * 2. Reverse the first k elements
     * 3. Reverse the remaining n-k elements
     * <p>
     * Example: [1,2,3,4,5,6,7], k=3
     * Step 1: [7,6,5,4,3,2,1]
     * Step 2: [5,6,7,4,3,2,1]
     * Step 3: [5,6,7,1,2,3,4]
     *
     * @param nums The input integer array
     * @param k    Number of steps to rotate right
     */
    public static void rotateArrayMethod2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;
        k = k % n; // Handle k > n

        if (k == 0) {
            return;
        }

        // Reverse entire array
        reverse(nums, 0, n - 1);
        // Reverse first k elements
        reverse(nums, 0, k - 1);
        // Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    /**
     * Helper method to reverse a portion of an array in-place.
     *
     * @param nums  The array to reverse
     * @param start Starting index (inclusive)
     * @param end   Ending index (inclusive)
     */
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // Question 3

    /**
     * Algorithm:
     * - Use a pointer to track the position where the next non-zero should be placed
     * - Iterate through the array and swap non-zero elements to this position
     * - This naturally pushes zeros to the end while maintaining order
     *
     * @param nums The input integer array (modified in-place)
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
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

    /**
     * Alternative implementation: Two-pass approach (less optimal due to more writes).
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums The input integer array (modified in-place)
     */
    public static void moveZeroesAlternative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int nonZeroPos = 0;

        // First pass: Copy all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroPos++] = nums[i];
            }
        }

        // Second pass: Fill remaining positions with zeros
        while (nonZeroPos < nums.length) {
            nums[nonZeroPos++] = 0;
        }
    }

    // UTILITY METHODS FOR TESTING

    /**
     * Utility method to print an array.
     *
     * @param arr The array to print
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
