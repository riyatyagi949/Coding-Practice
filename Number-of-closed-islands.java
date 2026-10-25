/*
 * ==========================================
 * PROBLEM STATEMENT: Number of Closed Islands
 * ==========================================
 * Given a 2D grid consisting of 0s (land) and 1s (water), return the number of closed islands. 
 * An island is a maximal 4-directionally connected group of 0s, and a closed island is an island 
 * totally surrounded by 1s (i.e., none of its land cells touch the grid boundary).
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (DFS Boundary Flooding)
 * ==========================================
 * - Approach:
 *   1. Any land component (group of `0`s) that touches the outer boundary of the grid cannot be a closed island.
 *   2. First, perform a DFS/BFS starting from all land cells (`0`s) on the border of the grid to "sink" 
 *      them (convert them to `1`s), eliminating any islands connected to the boundary.
 *   3. After clearing the boundary-connected land, iterate through the remaining inner cells of the grid.
 *   4. Whenever an unvisited land cell (`0`) is found, it forms a completely enclosed island. Run a DFS 
 *      to flood-fill and mark the entire island as visited, then increment the island count.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n), where `m` and `n` are the dimensions of the grid. Each cell is visited at most a constant number of times.
 *   - Space Complexity: O(m * n) in the worst-case scenario for the recursion stack during DFS traversal.
 */

class Solution {
    private int m, n;
    private boolean dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n)
            return false;

        if (grid[r][c] == 1)
            return true;

        grid[r][c] = 1;

        boolean left  = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);
        boolean up    = dfs(grid, r - 1, c);
        boolean down  = dfs(grid, r + 1, c);

        return left && right && up && down;
    }
    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }
         return count;
    }
}
