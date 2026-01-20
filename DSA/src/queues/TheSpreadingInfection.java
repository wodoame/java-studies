package queues;

import java.util.LinkedList;
import java.util.Queue;

public class TheSpreadingInfection {

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // Step 1 & 2: Initialize queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // Return 0 if there are no fresh oranges initially
        if (freshOranges == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Step 3: Multi-Source BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infectedThisRound = false;

            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int r = point[0];
                int c = point[1];

                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];

                    // Check boundaries and if the adjacent cell is a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        // Infect the orange
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        queue.offer(new int[]{newRow, newCol});
                        infectedThisRound = true;
                    }
                }
            }

            if (infectedThisRound) {
                minutes++;
            }
        }

        // Step 4: Check if any fresh oranges remain
        return freshOranges == 0 ? minutes : -1;
    }
}

