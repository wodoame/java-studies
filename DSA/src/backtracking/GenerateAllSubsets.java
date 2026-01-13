package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateAllSubsets {
    static void main() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        generate(0, nums, subset, res);
        System.out.println(res);
    }

    static void generate(int i, int[] nums, List<Integer> subset, List<List<Integer>> res){
        if(i == nums.length){
            List<Integer> subsetCopy = new ArrayList<>(subset);
            res.add(subsetCopy);
            return;
        }
       subset.add(nums[i]);
       generate(i + 1, nums, subset, res);
       subset.removeLast();
       generate(i + 1, nums, subset, res);
    }
}
