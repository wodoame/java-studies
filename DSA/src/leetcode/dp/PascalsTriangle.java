package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    static void main() {
        System.out.println(generate(2));
    }
    public static List<List<Integer>> generate(int numRows) {     List<List<Integer>> dp = new ArrayList<>();
        dp.add(List.of(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> base = new ArrayList<>(List.of(1));
            List<Integer> prev = dp.get(i - 1);
            for(int j = 1; j < i ; j ++){
                base.add(prev.get(j) + prev.get(j - 1));
            }
            base.add(1);
            dp.add(base);
        }
        return dp;}
}
