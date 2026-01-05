package leetcode.temp.strings;

import java.util.HashMap;
import java.util.HashSet;

public class LongestPalindrome {
    static void main() {
    }
    public static int longestPalindrome(String s) {
        HashSet<Character> seen = new HashSet<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if(seen.contains(current)){
                seen.remove(current);
                res += 2;
            }
            else seen.add(current);
        }
        return seen.isEmpty()? res: res + 1;
    }

    public static int longestPalindromeSolution2(String s) {
        HashMap<Character, Integer> freqs = new HashMap<>();
        int res = 0;
        boolean hasOddNumberFrequency = false;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            freqs.put(current, freqs.getOrDefault(current, 0) + 1);
        }

        for (Integer freq: freqs.values()) {
            if(freq % 2 == 0)res += freq;
            else hasOddNumberFrequency = true;
        }

        return hasOddNumberFrequency? res + 1: res;
    }
    
    // Saw this interesting solution using XOR operation 
    public static int longestPalindromeXORSolution(String s) {
        int len=s.length();
        if(len<2) return len;
        boolean[] arr=new boolean[58];
        int ans=0;
        for(char v:s.toCharArray()){
            ans+=(arr[v-'A']^=true)?0:2;
        }
        if(ans<len) return ans+1;
        return ans;
    }
}
