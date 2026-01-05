package leetcode.temp.strings;

// leetcode 125
public class ValidPalindrome {
    static void main() {
        System.out.println(isPalindrome("a"));
    }
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
               sb.append(c);
            }
        }

        String cleaned = sb.toString().toLowerCase();
        return isPal(cleaned);
    }

    static boolean isPal(String s){
        int n = s.length();
        int left;
        int right;

        if(n % 2 == 1) left = right = n / 2;
        else{
            right = n / 2;
            left = right - 1;
        }
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1 == s.length();
    }
}
