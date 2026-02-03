package leetcode.graphs;

// leetcode 200
public class NumberOfIslands {
    static void main() {
        System.out.println(numIslands(new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        }));
    }
    public static int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m * n; i++) {
            if(explore(grid, i / n, i % n))count++;
        }
        return count;
    }

    static boolean explore(char[][] grid, int r, int c){
        if(r == grid.length || r < 0 || c < 0 || c == grid[0].length || grid[r][c] == '0')return false;
        grid[r][c] = '0';
        explore(grid, r + 1, c);
        explore(grid, r - 1, c);
        explore(grid, r, c +1);
        explore(grid, r, c  - 1);
        return true;
    }
}
