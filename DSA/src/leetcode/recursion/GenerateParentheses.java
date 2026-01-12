package leetcode.recursion;
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
       List<String> ans = new ArrayList<>();
       create("", 0, 0, n, ans);
       return ans;
    }

    public static void create(String currentString, int opening, int closing, int n, List<String> ans) {
       if(opening == n && closing == n){ // equivalent conditions (opening + closing == 2 * n; currentString.length() == 2 * n)
           ans.add(currentString);
           return;
       }

       if(opening < n){
           create(currentString + "(", opening + 1, closing, n, ans);
       }
       if(closing < opening){
           create(currentString + ")", opening, closing + 1, n, ans);
       }
    }
}
