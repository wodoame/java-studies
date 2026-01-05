package leetcode.temp.arrays;

// leetcode 692
public class MinimumInSortedRotatedArray {
    static void main() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2, 3};
        System.out.println(findMin(nums));
    }
    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        return fMin(nums, left, right);
    }

    static int fMin(int[] nums, int left, int right){
        if(left == right)return nums[left];
        int m =  left + (right - left) / 2;
        if(nums[m] > nums[right])return fMin(nums, m + 1, right);
        return fMin(nums, left, m);
    }
}
