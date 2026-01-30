package leetcode.twopointers;

// leetcode 392
public class IsSubsequence {
    static void main() {
        System.out.println(isSubsequence("", "ahbgdc"));
    }
    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while(i < s.length() && j < t.length()){
            if(t.charAt(j) == s.charAt(i))i++;
            j++;
        }
        return i == s.length();
    }
}
