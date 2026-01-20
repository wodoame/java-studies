package leetcode.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {
    public static void main(String[] args) {
        char[][] board = {{'A','B'}};
        System.out.println(exist(board, "BA"));
    }
    public static boolean exist(char[][] board, String word) {
        Set<List<Integer>> visited = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == word.charAt(0) && backtrack(0, i, j, m, n, word, board, visited))return true;
            }
        }
        return false;
    }

    public static boolean backtrack(int i, int r, int c, int m, int n, String word, char[][] board, Set<List<Integer>> visited) {
        if(i == word.length())return true;
        if(r < 0 || c < 0 || r == m || c == n || word.charAt(i) != board[r][c] || visited.contains(List.of(r, c)))return false;
        visited.add(List.of(r, c));
        if(
                backtrack(i + 1, r + 1, c, m, n, word, board, visited) ||
                backtrack(i + 1, r - 1, c, m, n, word, board, visited) ||
                backtrack(i + 1, r, c + 1, m, n, word, board, visited) ||
                backtrack(i + 1, r, c - 1, m, n, word, board, visited)
        )return true;
       visited.remove(List.of(r, c));
       return false;
    }
}


/*
Optimizations:
* 1. Frequency Pruning
This optimization ensures the board actually has the ingredients necessary to make the word.
Logic: Before starting any pathfinding, the code counts every character on the board. It then iterates through the target word, subtracting counts.
Benefit: If the word requires three 'A's but the board only contains two, the count will drop below zero. The function immediately returns false, avoiding the expensive DFS traversal completely.

2. Reverse Word Heuristic
This optimization attempts to minimize the number of DFS branches by starting with the "rarest" character.
Logic: It compares the frequency of the first letter and the last letter of the word on the board. If the first letter appears more often than the last, it reverses the search string.
Benefit: DFS works by trying every instance of the starting letter. If the word starts with a common letter (e.g., 'A' appears 20 times) and ends with a rare letter (e.g., 'Z' appears 1 time), searching for "A...Z" triggers 20 search trees. Reversing it to "Z...A" triggers only 1 search tree, significantly speeding up the process.
* */
class Solution {
    private int m, n;
    private final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        // ---- Optimization 1: frequency pruning ----
        int[] freq = new int[128];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                freq[board[i][j]]++;

        for (char c : word.toCharArray()) {
            if (--freq[c] < 0) return false;
        }

        // ---- Optimization 2: reverse word if needed ----
        if (countChar(board, word.charAt(0)) >
            countChar(board, word.charAt(word.length() - 1))) {
            word = new StringBuilder(word).reverse().toString();
        }

        // ---- DFS search ----
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0)) return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) return true;

        if (i < 0 || i >= m || j < 0 || j >= n ||
            board[i][j] != word.charAt(idx)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#'; // mark visited

        for (int[] d : dirs) {
            if (dfs(board, word, i + d[0], j + d[1], idx + 1)) {
                board[i][j] = temp;
                return true;
            }
        }

        board[i][j] = temp; // backtrack
        return false;
    }

    private int countChar(char[][] board, char c) {
        int count = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] == c) count++;
        return count;
    }
}
