package leetcode.arrays;

public class RemoveDuplicate {
    public static int removeDuplicate(int[] nums){
        int k = 1;
        int previous = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if(current != previous){
                nums[k++] = current;
                previous = current;
            }
        }
        return k;
    }
}
