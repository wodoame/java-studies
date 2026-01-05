package leetcode.arrays;

// leetcode 26
public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums){
        int[] result = new int[nums.length];
        int previous = nums[0];
        int k=1;
        result[0] = nums[0];
        int j = 1;
        for(int i=1; i< nums.length; i++) {
            int current = nums[i];
            if (current != previous) {
                k += 1;
                result[j++] = current;
                previous = current;
            }
        }

        if (k >= 0) System.arraycopy(result, 0, nums, 0, k);
        return k;

    }

    // in-place solution
    public static int removeDuplicatesInPlace(int[] nums){
       int positionToInsert = 1;
       for(int i=1; i< nums.length; i++){
           int current = nums[i];
           if(nums[i] != nums[i - 1]){
               nums[positionToInsert++] = current;
           }
       }

       return positionToInsert;
    }
}
