package leetcode.stacks;

import java.util.Stack;

public class RemovingStarsFromString {
    public String removeStars(String s) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '*')res.append(s.charAt(i));
            else res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}
