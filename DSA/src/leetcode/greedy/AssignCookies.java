package leetcode.greedy;

import java.util.Arrays;

// leetcode 451
public class AssignCookies {
    static void main() {
        System.out.println(findContentChildren(new int[]{10,9,8,7}, new int[]{5,6,7,8}));
    }
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int j = 0;
        int i =0;
        while(i < g.length && j < s.length){
            if (s[j] >= g[i]) {
                count++;
                i++;
            }
            j++;
        }
        return count;
    }
}
