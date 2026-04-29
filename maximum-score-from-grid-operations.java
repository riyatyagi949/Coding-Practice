/**
 * PROBLEM STATEMENT: 3225. Maximum Score From Grid Operations
 * --------------------------------------------------------------------------------
 * You have an n x n grid. You can color column j black from row 0 down to row i.
 * Score = Sum of grid[i][j] where cell (i, j) is white AND has a horizontal black neighbor.
 * Goal: Maximize this score.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Dynamic Programming (Column-wise)
 * --------------------------------------------------------------------------------
 * Since score depends on adjacent columns, we use DP state: dp[col][height][state]
 * - col: Current column (0 to n-1)
 * - height: Height of black cells in current column (0 to n)
 * - state: 
 * 0 -> Decreasing (h_col < h_{col-1})
 * 1 -> Increasing (h_col > h_{col-1})
 * 2 -> Special state for valleys (h_{col-2} > h_{col-1} < h_{col})
 * * We use prefix sums to calculate the sum of grid values in a column range in O(1).
 * --------------------------------------------------------------------------------
 * COMPLEXITY:
 * Time: O(N^3) - N columns, N heights, and N transitions per height.
 * Space: O(N^2) - To store the DP table and prefix sums.
 */
// Code --

import java.util.Arrays;

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;

        if (n == 1) 
            return 0;

        long[] dp0 = new long[n + 1];
        long[] dp1 = new long[n + 1];

        for (int j = 1; j < n; j++) {
            long[] new_dp0 = new long[n + 1];
            long[] new_dp1 = new long[n + 1];

            for (int i = 0; i <= n; i++) {
                long prev = 0, curr = 0;

                for (int x = 0; x < i; x++)
                    curr += grid[x][j];

                for (int y = 0; y <= n; y++) {
                    if (y > 0 && y <= i)
                        curr -= grid[y - 1][j];

                    if (y > i)
                        prev += grid[y - 1][j - 1];
                        
                    new_dp0[y] = Math.max(new_dp0[y], Math.max(prev + dp0[i], dp1[i]));
                    new_dp1[y] = Math.max(new_dp1[y], Math.max(curr + dp1[i], curr + prev + dp0[i]));
                }
            }
            dp0 = new_dp0;
            dp1 = new_dp1;
        }
        long res = 0;
        for (long v : dp1) 
            res = Math.max(res, v);
        return res;
    }
}

