package leetcode.arrays;

import java.util.Arrays;

// leetcode 27
public class RemoveElement {
    static void main() {
    int[] nums = {3, 2, 2, 3};
    int k = removeElement(nums, 3);
    System.out.println(Arrays.toString(nums));
    }
 public static int removeElement(int[] nums, int val) {
     int n = nums.length;
     int k = 0;
     int positionToInsert = n - 1;
     int i=0;
     while (i <= positionToInsert) {
         int current = nums[i];
         if(current == val){
             while(positionToInsert > -1 && nums[positionToInsert] == val)positionToInsert--;
             if(i < positionToInsert){
                 k++;
                 swap(nums, i, positionToInsert);
                 positionToInsert--;
             }
         }
         else k++;
         i++;
     }
     return k;
 }

 public static void swap(int[] nums, int x, int y){
     int temp = nums[x];
     nums[x] = nums[y];
     nums[y] = temp;
 }


public static int removeElementSimplerSolution(int[] nums, int val) {
    int k = 0;
    for (int i = 0; i < nums.length; i++) {
        if(nums[i] != val){
            nums[k] = nums[i];
            k++;
        }
    }
    return k;
}
}
