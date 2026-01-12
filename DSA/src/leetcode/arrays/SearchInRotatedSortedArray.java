package leetcode.arrays;

// leetcode 33
public class SearchInRotatedSortedArray {
    static void main() {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length -1;
        while(low <= high){
            int m = low + (high - low) / 2;
            if(target == nums[m])return m;
            else if(nums[low] <= nums[m]){
                if(nums[low] <= target && target < nums[m])high = m -1;
                else low = m + 1;
            }
            else{
                if(nums[m] < target && target <= nums[high])low = m + 1;
                else high = m -1;
            }
        }
        return -1;
    }
}
