package leetcode.temp.stacks;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ValidParenthesis {
    static void main() {
    }

    public boolean isValid(String s) {
        Set<Character> openBrackets = Set.of('(', '[', '{');
        Map<Character, Character> closingBrackets = Map.of(
                ')', '(',
                ']', '[',
                '}', '{'
                );

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if(openBrackets.contains(bracket)) stack.push(bracket);
            else if(!stack.isEmpty() && closingBrackets.get(bracket) == stack.peek())stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }
}
