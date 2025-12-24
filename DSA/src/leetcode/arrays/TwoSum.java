package leetcode.arrays;

import java.util.HashMap;

public class TwoSum {
    static void main() {

    }

    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if(seen.get(current) == null){
            int complement = target - current;
            seen.put(complement, i);
            }
            else{
                return new int[] {seen.get(current), i};
            }
        }
        return new int[] {};
    }
}
