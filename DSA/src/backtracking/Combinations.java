package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    static void main() {
        int[] nums = {1, 2, 3, 4};
        System.out.println(generateCombinations(nums, 4));
    }

    static void generateCombinationsRec(int i, List<List<Integer>> res, List<Integer> subset, int[] arr, int k){
       if(k > arr.length)return;
       if(i == arr.length){
           if(subset.size() == k)res.add(new ArrayList<>(subset));
           return;
       }
       subset.add(arr[i]);
       generateCombinationsRec(i +  1, res, subset, arr, k);
       subset.removeLast();
       generateCombinationsRec(i +  1, res, subset, arr, k);
    }

    static List<List<Integer>> generateCombinations(int[] arr, int k){
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        generateCombinationsRec(0, res, subset, arr, k);
        return res;
    }
}
