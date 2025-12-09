import java.util.ArrayList;

public class Recursion {
    public static void main(String[] args) {
        ArrayList<String> parentheses = new ArrayList<>();
        parentheses = generateParentheses(3);
        System.out.println(parentheses);
    }

    public static int factorial(int n){
        if(n == 1)return 1;
        return n * factorial(n - 1);
    }

    public static ArrayList<String> generateParentheses(int n){
        ArrayList<String> parentheses = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(sb, 0, 0, n, parentheses);
        return parentheses;
    }

    public static void backtrack(StringBuilder currentString, int openCount, int closeCount, int n, ArrayList<String> res){
        if(currentString.length() == 2 * n){
            res.add(currentString.toString());
            return;
        }
        if(openCount < n){
            currentString.append("(");
            backtrack(currentString, openCount + 1, closeCount, n, res);
            currentString.setLength(currentString.length() - 1);
        }

        // ensuring the well-formed contraint
        if(closeCount < openCount){
            currentString.append(")");
            backtrack(currentString, openCount, closeCount + 1, n, res);
            currentString.setLength(currentString.length() - 1);
        }

    }
}
