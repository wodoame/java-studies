package leetcode.temp.strings;

public class LengthOfLastWord {
    static void main() {
        System.out.println(lengthOfLastWord("My name"));
    }
    public static int lengthOfLastWord(String s) {
        int n = s.length();
        if(n == 0)return 0;
        int right = n - 1;
        while(right >=0 && !Character.isLetter(s.charAt(right)))right--;
        int left = right;
        while(left >= 0 && !Character.isSpaceChar(s.charAt(left)))left--;
        return s.substring(left + 1, right + 1).length();
    }
}
