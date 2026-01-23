void main() {
    ArrayList<String> parentheses;
    parentheses = generateParentheses(3);
    IO.println(parentheses);
}

public static ArrayList<String> generateParentheses(int n) {
    ArrayList<String> parentheses = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    backtrack(sb, 0, 0, n, parentheses);
    return parentheses;
}

public static void backtrack(StringBuilder currentString, int openCount, int closeCount, int n, ArrayList<String> res) {
    if (currentString.length() == 2 * n) {
        res.add(currentString.toString());
        return;
    }
    if (openCount < n) {
        currentString.append("(");
        backtrack(currentString, openCount + 1, closeCount, n, res);
        currentString.setLength(currentString.length() - 1);
    }

    // ensuring the well-formed contraint
    if (closeCount < openCount) {
        currentString.append(")");
        backtrack(currentString, openCount, closeCount + 1, n, res);
        currentString.setLength(currentString.length() - 1);
    }
}
