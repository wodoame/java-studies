package sorting;

import java.util.Arrays;

public class InsertionSort {
    static void main() {
    int[] nums = {5, 3, 4, 2, 1};
    sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static void sort(int[] nums){
       // [1, 8, 3, 2, 6, 7]
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int j = i - 1;
            while(j >= 0 && nums[j] > current){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = current;
        }
    }
}
