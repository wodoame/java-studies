/**
 * Implements Binary Search to find the index of a target value in a sorted array.
 * Binary Search works by repeatedly dividing the search interval in half.
 */
/**
 * Finds the target in the sorted array.
 *
 * @param arr The sorted integer array to search within.
 * @param target The value to find.
 * @return The index of the target if found; otherwise, returns -1.
 */
public static int findTargetIndex(int[] arr, int target) {
    // 1. Initialize the search boundaries
    int low = 0; // The start index of the search space
    int high = arr.length - 1; // The end index of the search space

    // 2. Loop while the search space is valid
    // The search space is valid as long as 'low' does not pass 'high'
    while (low <= high) {
        // 3. Calculate the middle index (mid)
        // Using (high + low) / 2 is mathematically correct, but this version
        // prevents potential integer overflow when high and low are very large.
        int mid = low + (high - low) / 2;

        // 4. Check the middle element

        // Case 1: Target is found!
        if (arr[mid] == target) {
            return mid; // Return the index
        }

        // Case 2: The middle element is LESS than the target.
        // This means the target must be in the RIGHT half (if it exists).
        // We can discard the left half (arr[low] through arr[mid]).
        else if (arr[mid] < target) {
            // Move the 'low' boundary to mid + 1
            low = mid + 1;
        }

        // Case 3: The middle element is GREATER than the target.
        // This means the target must be in the LEFT half (if it exists).
        // We can discard the right half (arr[mid] through arr[high]).
        else { // arr[mid] > target
            // Move the 'high' boundary to mid - 1
            high = mid - 1;
        }
    }

    // 5. If the loop finishes, it means 'low' > 'high',
    // and the target was not found in the array.
    return -1;
}

// target = 1
// 7l, 8, 9, 10m, 1t, 2, 3, 4, 5, 6r
// 7, 8, 9, 10, 1l, 2, 3m, 4, 5, 6r
// 7, 8, 9, 10, 1lm, 2r, 3, 4, 5, 6

// Rotated binary search
// Is the target at the middle position?
// If no then we check if the left side is sorted (nums[low] <= nums[mid])
    // If yes then we check if the target lies within the left side (target >= nums[low] && target < nums[mid])
        // If yes then the target must be in the left sorted half
        // If no then the target must be in the right unsorted / rotated half

    // If no then the target must be in the right unsorted / rotated half
        // If the target doesn't lie within the right side then it must be on the left side

void main() {
    int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
    int target1 = 23;
    int target2 = 42;

    int index1 = findTargetIndex(sortedArray, target1);
    int index2 = findTargetIndex(sortedArray, target2);

    IO.println("Array: " + Arrays.toString(sortedArray));
    IO.println("Target " + target1 + " found at index: " + index1); // Expected: 5
    IO.println("Target " + target2 + " found at index: " + index2); // Expected: -1
}