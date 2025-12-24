package leetcode.strings;

// leetcode 242
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
       int[] hash = new int[26];
       if(s.length() != t.length())return false;
       for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
       }
        for(int n: hash){
            if(n != 0)return false;
        }
        return true;
    }
}
