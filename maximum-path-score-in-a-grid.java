/**
 * PROBLEM STATEMENT: 3742. Maximum Path Score in a Grid
 * --------------------------------------------------------------------------------
 * Given an m x n grid with values 0, 1, or 2 and an integer k.
 * - Start at (0, 0), move only Right or Down to reach (m-1, n-1).
 * - Each cell value determines its score and cost:
 *   - 0: Score +0, Cost 0
 *   - 1: Score +1, Cost 1
 *   - 2: Score +2, Cost 1
 * - Find the maximum total score such that total cost <= k.
 * - Return -1 if no valid path exists.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: 3D DYNAMIC PROGRAMMING
 * --------------------------------------------------------------------------------
 * We use a 3D array dp[i][j][cost] to represent the maximum score achievable
 * reaching cell (i, j) with exactly 'cost'.
 * 
 * Score and Cost Mapping:
 * | Value | Score | Cost |
 * |-------|-------|------|
 * |   0   |   0   |  0   |
 * |   1   |   1   |  1   |
 * |   2   |   2   |  1   |
 * 
 * 1. Initialize dp[m][n][k+1] with -1.
 * 2. Set dp[0][0][0] = 0 (since grid[0][0] is always 0).
 * 3. Iterate through every cell (i, j) and every possible cost 'c' up to k.
 * 4. For each cell, transition to the right (i, j+1) and down (i+1, j).
 * 5. After filling the table, the answer is the max value in dp[m-1][n-1][0...k].
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N * K)
 * - We iterate through each cell (M*N) and for each cell, we iterate through k costs.
 * Space Complexity: O(M * N * K)
 * - The 3D DP table stores M * N * (K+1) integers.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        int[][][] dp = new int[m][n][k + 1];
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int c = 0; c <= k; c++)
                    dp[i][j][c] = -1;

        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {

                    if (dp[i][j][c] == -1) 
                    continue;

                    if (i + 1 < m) {
                        int val = grid[i + 1][j];
                        int cost = (val == 0) ? 0 : 1;
                        int nc = c + cost;

                        if (nc <= k) {
                            dp[i + 1][j][nc] = Math.max(
                                dp[i + 1][j][nc],
                                dp[i][j][c] + val
                            );
                        }
                    }

                    if (j + 1 < n) {
                        int val = grid[i][j + 1];
                        int cost = (val == 0) ? 0 : 1;
                        int nc = c + cost;
                        
                        if (nc <= k) {
                            dp[i][j + 1][nc] = Math.max(
                                dp[i][j + 1][nc],
                                dp[i][j][c] + val
                            );
                        }
                    }
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}
