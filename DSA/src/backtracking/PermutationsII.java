package backtracking;

import java.util.*;

public class PermutationsII {
    static void main() {
        System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(permuteII(new int[]{1, 2, 3}));
    }

    // recursive solution
    static List<List<Integer>> permute(int[] arr){
        List<List<Integer>> res = new ArrayList<>();
        if(arr.length == 0){
            res.add(new ArrayList<>());
            return res;
        }

    List<List<Integer>> perms = permute(Arrays.copyOfRange(arr, 1, arr.length));
        for(var perm: perms){
            for (int i = 0; i < perm.size() + 1; i++) {
                var permCopy = new ArrayList<>(perm);
                permCopy.add(i, arr[0]);
                res.add(permCopy);
            }
        }

        return res;
    }

    // iterative solution
    static List<List<Integer>> permuteII(int[] arr){
        List<List<Integer>> perms = new ArrayList<>();
        perms.add(new ArrayList<>());
        for(int n: arr){
            List<List<Integer>> newPerms = new ArrayList<>();
            for(var perm: perms){
                for (int i = 0; i < perm.size() + 1; i++) {
                    var permCopy = new ArrayList<>(perm);
                    permCopy.add(i, n);
                    newPerms.add(permCopy);
                }
            perms = newPerms;
            }
        }
        return perms;
    }

}
