package leetcode.temp.arrays;

// leetcode 127
public class TwoSumII {
    static void main() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int target = 7;
    }
    public int[] twoSum(int[] numbers, int target) {
    // Initialize one pointer at the start and one at the end
    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {
        int currentSum = numbers[left] + numbers[right];

        if (currentSum == target) {
            // The problem usually asks for 1-based indices
            return new int[]{left + 1, right + 1};
        } else if (currentSum < target) {
            // Sum is too small, move the left pointer to increase the sum
            left++;
        } else {
            // Sum is too large, move the right pointer to decrease the sum
            right--;
        }
    }

    // Default return if no solution is found
    return new int[]{-1, -1};
}
}
