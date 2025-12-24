package leetcode.arrays;

import java.util.HashSet;

// leetcode: 217
public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums){
        HashSet<Integer> seen = new HashSet<>();
        for(int num: nums){
            if(seen.contains(num)){
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
