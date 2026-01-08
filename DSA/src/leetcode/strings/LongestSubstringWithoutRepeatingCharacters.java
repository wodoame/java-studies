package leetcode.strings;

import java.util.HashSet;
import java.util.Set;

// leetcode 3
public class LongestSubstringWithoutRepeatingCharacters {
   public int lengthOfLongestSubstring(String s) {
       int res = 0;
       int left = 0;
       Set<Character> seen = new HashSet<>();
       for (int i = 0; i < s.length(); i++) {
           char c = s.charAt(i);

           while(seen.contains(c)){
               seen.remove(s.charAt(left));
               left++;
           }
           seen.add(c);
           res = Math.max(res, i - left + 1);
       }
       return res;
   }
}
