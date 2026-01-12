package leetcode.arrays;

// leetcode 81
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        System.out.println(search(nums, 2)); // true

        int[] nums2 = {2,5,6,0,0,1,2};
        System.out.println(search(nums2, 0)); // true
        System.out.println(search(nums2, 3)); // false
    }

   public static boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // Handle duplicates: when we can't determine which half is sorted
            // we need to skip duplicates from both ends
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            }
            // Left half is sorted
            else if (nums[low] <= nums[mid]) {
                // Check if target is in the sorted left half
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else {
                // Check if target is in the sorted right half
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;
    }
}
