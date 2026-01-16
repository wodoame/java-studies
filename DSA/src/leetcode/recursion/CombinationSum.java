package leetcode.recursion;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        HashSet<List<Integer>> seen = new HashSet<>();
        findCombinations(0, res, subset, candidates, target, seen);
        return res;
    }

    public void findCombinations(int i, List<List<Integer>> res, List<Integer> subset,  int[] candidates, int target, HashSet<List<Integer>> seen) {
        if(seen.contains(subset))return;
        if(target < 0)return;
        if(target == 0){
            res.add(new ArrayList<>(subset));
            seen.add(subset);
        }
        if(i == candidates.length) return;
        subset.add(candidates[i]);
        findCombinations(i, res, subset, candidates, target - candidates[i], seen);
        subset.removeLast();
        findCombinations(i + 1, res, subset, candidates, target, seen);
    }
}
