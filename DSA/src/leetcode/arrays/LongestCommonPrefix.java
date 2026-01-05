package leetcode.temp.arrays;

public class LongestCommonPrefix {
    static void main() {
        System.out.println("Hello".substring(0, 2));
    }
     public String longestCommonPrefix(String[] strs) {
       String prefix = strs[0];
         for (int i = 1; i < strs.length; i++) {
             String current = strs[i];
             int j = 0;
             while(j < Math.min(current.length(), prefix.length()) && current.charAt(j) == prefix.charAt(j))j++;
             prefix = prefix.substring(0, j);
             if(prefix.isEmpty())return prefix;
         }
         return prefix;
    }
}
