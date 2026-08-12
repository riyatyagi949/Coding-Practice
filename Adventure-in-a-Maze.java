/*
 * ==========================================
 * PROBLEM STATEMENT: Adventure in a Maze
 * ==========================================
 * Given an n x n grid where each cell contains a value (1, 2, or 3) indicating allowed movements:
 * - 1: Move Right only.
 * - 2: Move Down only.
 * - 3: Move Right or Down.
 * 
 * Starting from the top-left cell (0, 0) and reaching the bottom-right cell (n-1, n-1):
 * - Find the total number of distinct valid paths (modulo 10^9 + 7).
 * - Find the maximum possible "Adventure" (sum of values of all visited cells along a path).
 * Return the result as [totalPaths, maxAdventure]. If no path exists, return [0, 0].
 * 
 * Constraints:
 * 1 <= n <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming)
 * ==========================================
 * - Approach: We use two 2D tables of size n x n:
 *   1. `ways[i][j]` tracks the number of distinct valid paths to reach cell (i, j) (modulo 10^9 + 7).
 *   2. `adventure[i][j]` tracks the maximum path sum (Adventure) to reach cell (i, j).
 * - State Transitions:
 *   - We iterate row by row and column by column from (0, 0).
 *   - From cell (i, j), if reachable (`ways[i][j] > 0`), we look at `grid[i][j]`:
 *     - If it allows moving Right (1 or 3) and `j + 1 < n`:
 *       - Update `ways[i][j + 1] = (ways[i][j + 1] + ways[i][j]) % MOD`
 *       - Update `adventure[i][j + 1] = max(adventure[i][j + 1], adventure[i][j] + grid[i][j + 1])`
 *     - If it allows moving Down (2 or 3) and `i + 1 < n`:
 *       - Update `ways[i + 1][j] = (ways[i + 1][j] + ways[i][j]) % MOD`
 *       - Update `adventure[i + 1][j] = max(adventure[i + 1][j], adventure[i][j] + grid[i + 1][j])`
 * - Complexity:
 *   - Time Complexity: O(n^2), since we visit each cell in the n x n grid once.
 *   - Space Complexity: O(n^2), for storing the `ways` and `adventure` tracking tables.
 */

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> findWays(int[][] grid) {
        int n = grid.length;
        int MOD = 1000000007;

        long[][] ways = new long[n][n];
        int[][] adventure = new int[n][n];

        ways[0][0] = 1;
        adventure[0][0] = grid[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ways[i][j] == 0) {
                    continue;
                }
                
                if ((grid[i][j] == 1 || grid[i][j] == 3) && j + 1 < n) {
                    ways[i][j + 1] = (ways[i][j + 1] + ways[i][j]) % MOD;
                    adventure[i][j + 1] = Math.max(adventure[i][j + 1], adventure[i][j] + grid[i][j + 1]);
                }
                
                if ((grid[i][j] == 2 || grid[i][j] == 3) && i + 1 < n) {
                    ways[i + 1][j] = (ways[i + 1][j] + ways[i][j]) % MOD;
                    adventure[i + 1][j] = Math.max(adventure[i + 1][j], adventure[i][j] + grid[i + 1][j]);
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        if (ways[n - 1][n - 1] == 0) {
            ans.add(0);
            ans.add(0);
        } 
        else {
            ans.add((int) ways[n - 1][n - 1]);
            ans.add(adventure[n - 1][n - 1]);
        }
       return ans;
    }
}
