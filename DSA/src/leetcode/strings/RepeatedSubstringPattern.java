package leetcode.temp.strings;

public class RepeatedSubstringPattern {
    static void main() {
        System.out.println("Original solution: " + repeatedSubstringPattern("abcabcabcabc"));
        System.out.println("Alternate solution: " + repeatedSubstringPatternOptimized("abcabcabcabc"));
        System.out.println("Original solution: " + repeatedSubstringPattern("aba"));
        System.out.println("Alternate solution: " + repeatedSubstringPatternOptimized("aba"));
        System.out.println("Original solution: " + repeatedSubstringPattern("abab"));
        System.out.println("Alternate solution: " + repeatedSubstringPatternOptimized("abab"));
    }

    // Original Solution - O(n²) time complexity
    public static boolean repeatedSubstringPattern(String s) {
        int i = s.length() / 2;
        for(int j=i; j > 0; j--){
            String sub = s.substring(0, j);
            StringBuilder res = new StringBuilder(sub);
            while(res.length() < s.length())res.append(sub);
            if(res.length() == s.length() && res.toString().equals(s))return true;
        }
        return false;
    }

    /**
     * OPTIMIZED SOLUTION - O(n) time complexity
     *
     * Key Insight: If a string s is made of repeated substrings, then:
     * - Concatenating s with itself creates: s + s
     * - Removing the first and last character from (s + s) still contains s as a substring
     *
     * Example: s = "abcabc"
     * - s + s = "abcabcabcabc"
     * - Remove first and last: "bcabcabcab"
     * - Does it contain "abcabc"? YES! (at index 3)
     *
     * Why this works:
     * - If s is made of repeating pattern, the pattern appears multiple times in s+s
     * - By removing first and last char, we remove the "obvious" match at position 0
     * - If s still appears, it means the pattern truly repeats
     *
     * Counterexample: s = "abc" (no repeating pattern)
     * - s + s = "abcabc"
     * - Remove first and last: "bcab"
     * - Does it contain "abc"? NO!
     */
    public static boolean repeatedSubstringPatternOptimized(String s) {
        // Create doubled string and remove first and last characters
        String doubled = s + s;
        String trimmed = doubled.substring(1, doubled.length() - 1);

        // If original string appears in the trimmed doubled string,
        // it means s is made of repeating substrings
        return trimmed.contains(s);
    }
}
