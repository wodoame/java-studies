package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllSubsets {
    static void main() {
        int[] nums = {1, 2, 3};
    }

    static void generate(int[] nums, List<Integer> res){
        for (int i = 0; i < nums.length; i++) {
            if(!res.contains(nums[i]))res.add(nums[i]);
            generate(nums, res);
            res.remove(nums[i]);
            generate(nums, res);
        }
    }
}
