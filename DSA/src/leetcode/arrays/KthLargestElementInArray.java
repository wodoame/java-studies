package leetcode.arrays;

public class KthLargestElementInArray {
    static void main() {
       int[] nums = {1, 2, 3, 3};
        System.out.println(findKthLargest(nums, 2));
    }
    // kth largest = (arr.length - k + 1)th smallest
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    public static int partition(int[] nums, int low, int high){
        int pivot = nums[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if(nums[j] <= pivot)swap(nums, i++, j);
        }
        swap(nums, i, high);
        return i;
    }

    public static int quickSelect(int[] nums, int low, int high, int k){
        int pi = partition(nums, low, high);
        int targetIndex = nums.length - k;
        if(pi == targetIndex){
            return nums[pi];
        }
        else if(pi < targetIndex){
            return quickSelect(nums, pi + 1, high, k);
        }
        return quickSelect(nums, low, pi - 1, k);
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
