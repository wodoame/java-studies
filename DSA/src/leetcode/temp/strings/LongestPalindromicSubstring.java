package leetcode.temp.strings;

// leetcode 5
public class LongestPalindromicSubstring {
    static void main() {
        System.out.println(longestPalindrome("aa"));
    }
    public static String longestPalindrome(String s) {
        int longest_l = 0;
        int longest_r = 0;
        int current_longest_length = 0;
        for (int i = 0; i < s.length(); i++) {

        // for odd case
        int[] indices = getLongestPalindrome(i, i, s);

        int x = indices[0];
        int y = indices[1];
        if(current_longest_length < y - x){
            longest_l = x;
            longest_r = y;
            current_longest_length = y - x;
        }

        // for even case
        indices = getLongestPalindrome(i, i + 1, s);

        x = indices[0];
        y = indices[1];
            if(current_longest_length < y - x){
                longest_l = x;
                longest_r = y;
                current_longest_length = y - x;
            }
        }
        return s.substring(longest_l, longest_r);
    }

    static int[] getLongestPalindrome(int left, int right, String s){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return new int[]{left + 1, right};

    }

}
