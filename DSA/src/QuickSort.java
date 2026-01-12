import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        // Original QuickSort demonstration
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(arr));

        System.out.println("\n--- Minimum Subarray Sum Difference ---");
        // Test case 1
        int[] nums1 = {8, 3, 1, 5, 2};
        int k1 = 3;
        int result1 = minSubarraySumDifference(nums1, k1);
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + result1);

        // Test case 2
        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        int result2 = minSubarraySumDifference(nums2, k2);
        System.out.println("\nInput: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + result2);

        // Test case 3
        int[] nums3 = {10, 20, 30};
        int k3 = 2;
        int result3 = minSubarraySumDifference(nums3, k3);
        System.out.println("\nInput: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + result3);
    }

    // trees.Main Quicksort function
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is the partitioning index
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // This function takes the last element as pivot and places
    // it at its correct position in the sorted array
    // [8, 3, 5, 1, 2]
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot element with the element at i+1
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Solves the Minimum Subarray Sum Difference problem using QuickSelect
     * @param nums - array of positive integers
     * @param k - find k-th smallest element (1-indexed)
     * @return minimum absolute difference between sum of subarrays
     */
    static int minSubarraySumDifference(int[] nums, int k) {
        // Create a copy to avoid modifying original array
        int[] arr = Arrays.copyOf(nums, nums.length);

        // Find the k-th smallest element using QuickSelect
        int pivot = quickSelect(arr, 0, arr.length - 1, k - 1); // Convert to 0-indexed

        // Partition elements: A < pivot, B > pivot (pivot not included yet)
        int sumLessThanPivot = 0;
        int sumGreaterThanPivot = 0;
        int pivotCount = 0;

        for (int num : nums) {
            if (num < pivot) {
                sumLessThanPivot += num;
            } else if (num > pivot) {
                sumGreaterThanPivot += num;
            } else {
                pivotCount++;
            }
        }

        // Assign pivot to the larger sum (or to A if equal)
        // A contains elements < pivot, B contains elements > pivot
        int sumA, sumB;

        if (sumGreaterThanPivot > sumLessThanPivot) {
            // Pivot goes to B (the larger sum)
            sumA = sumLessThanPivot;
            sumB = sumGreaterThanPivot + pivot * pivotCount;
        } else {
            // Pivot goes to A (larger or equal sum)
            sumA = sumLessThanPivot + pivot * pivotCount;
            sumB = sumGreaterThanPivot;
        }

        return Math.abs(sumA - sumB);
    }

    /**
     * QuickSelect algorithm to find k-th smallest element
     * @param arr - array to search
     * @param low - starting index
     * @param high - ending index
     * @param k - k-th smallest element (0-indexed)
     * @return the k-th smallest element
     */
    static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) {
            return arr[low];
        }

        // Partition the array using the same partition method as QuickSort
        int pi = partition(arr, low, high);

        // If partition index is the k-th position
        if (pi == k) {
            return arr[pi];
        }
        // If k is less than partition index, search left subarray
        else if (k < pi) {
            return quickSelect(arr, low, pi - 1, k);
        }
        // If k is greater than partition index, search right subarray
        else {
            return quickSelect(arr, pi + 1, high, k);
        }
    }
}