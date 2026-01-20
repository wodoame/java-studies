package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.println("All permutations:");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
        System.out.println("Total permutations: " + result.size());
    }

    /**
     * Generate all permutations of the given array
     * Time Complexity: O(n! * n) - n! permutations, each takes O(n) to build
     * Space Complexity: O(n) - recursion depth and current permutation
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, current, used, result);
        return result;
    }

    /**
     * Backtracking helper method
     * @param nums - the input array
     * @param current - current permutation being built
     * @param used - tracks which elements are already used
     * @param result - stores all permutations
     */
    private static void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // Base case: if current permutation has all elements
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try adding each unused element
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip if already used

            // Choose: add current element
            current.add(nums[i]);
            used[i] = true;

            // Explore: recurse with updated state
            backtrack(nums, current, used, result);

            // Unchoose: backtrack
            current.removeLast();
            used[i] = false;
        }
    }

    /**
     * Alternative approach using swapping (no extra space for 'used' array)
     * This modifies the array in place
     */
    public static List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSwap(nums, 0, result);
        return result;
    }

    private static void backtrackSwap(int[] nums, int start, List<List<Integer>> result) {
        // Base case: if we've fixed all positions
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        // Try each element at the current position
        for (int i = start; i < nums.length; i++) {
            // Swap current element to the start position
            swap(nums, start, i);

            // Recurse for the next position
            backtrackSwap(nums, start + 1, result);

            // Backtrack: restore the array
            swap(nums, start, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
