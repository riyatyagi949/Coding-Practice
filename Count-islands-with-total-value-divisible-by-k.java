/*
 * ==========================================
 * PROBLEM STATEMENT: Count Islands With Total Value Divisible by K
 * ==========================================
 * You are given an m x n matrix `grid` and a positive integer `k`. 
 * An island is a group of positive integers (representing land) that are 4-directionally connected (horizontally or vertically). 
 * The total value of an island is the sum of the values of all cells in the island. 
 * Return the number of islands with a total value divisible by `k`.
 * 
 * Constraints:
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * 0 <= grid[i][j] <= 10^6
 * 1 <= k <= 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Depth-First Search / Flood Fill)
 * ==========================================
 * - Approach:
 *   1. Treat the 2D grid as a graph where cells with positive values represent land and cells with 0 represent water.
 *   2. Iterate through each cell of the grid. When an unvisited land cell (`grid[i][j] > 0`) is encountered, we have discovered a new island.
 *   3. Run a Depth-First Search (DFS) from this cell to traverse the entire connected component, summing up all cell values 
 *      and marking visited cells by setting them to `0` to prevent redundant visits.
 *   4. Check if the computed total sum of the island is divisible by `k` (`sum % k == 0`). If it is, increment our valid island count.
 *   5. Repeat this process for all cells in the grid and return the final count.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n), since every cell in the grid is visited and processed at most once.
 *   - Space Complexity: O(m * n) in the worst-case scenario due to the recursion stack depth (when the entire grid forms a single island).
 */

class Solution {
    private int m, n;
    private final int[] dr = {-1, 0, 1, 0};
    private final int[] dc = {0, 1, 0, -1};

    private long dfs(int r, int c, int[][] grid) {
        long sum = grid[r][c];
        grid[r][c] = 0; // Mark current cell as visited

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                sum += dfs(nr, nc, grid);
            }
        }
        return sum;
    }

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long totalValue = dfs(i, j, grid);
                    if (totalValue % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
