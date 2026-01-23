package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    static void main() {
        System.out.println("10".compareTo("2"));
//        System.out.println(largestNumber(new int[]{10, 2}));
    }
    public static String largestNumber(int[] nums) {
        Comparator<Integer> largestStart = (a, b)->{
            char x = Integer.toString(a).charAt(0);
            char y = Integer.toString(b).charAt(0);
            return (x - '0') - (y - '0');
        };
        List<Integer> numsSorted = new ArrayList<>();
        for (int num : nums) {
            numsSorted.add(num);
        }
        numsSorted.sort(largestStart.reversed());
        System.out.println(numsSorted);
        StringBuilder result = new StringBuilder();
        for(int n: numsSorted){
           result.append(n);
        }
        return result.toString();
    }
}