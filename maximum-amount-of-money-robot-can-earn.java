/**
 * PROBLEM STATEMENT: 3418. Maximum Amount of Money Robot Can Earn
 * --------------------------------------------------------------------------------
 * You are given an m x n grid. A robot starts at (0, 0) and wants to reach 
 * (m - 1, n - 1) by moving only Right or Down.
 * * - If coins[i][j] >= 0: Robot gains that many coins.
 * - If coins[i][j] < 0: Robot encounters a robber who steals |coins[i][j]|.
 * - Special Ability: The robot can neutralize robbers in at most 2 cells, 
 * preventing them from stealing anything.
 * * Goal: Return the maximum profit achievable.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: 3D DYNAMIC PROGRAMMING (TOP-DOWN)
 * --------------------------------------------------------------------------------
 * We use a 3D DP table `dp[m][n][3]` where:
 * - i, j: Current coordinates in the grid.
 * - k: Number of neutralization opportunities remaining (0, 1, or 2).
 * * Transitions:
 * 1. Standard Move: Add `coins[i][j]` to the best path from the next cell 
 * (Right or Down) using the same number of neutralizations 'k'.
 * 2. Neutralize (if coins[i][j] < 0 and k > 0): Add 0 to the best path from 
 * the next cell using 'k - 1' neutralizations.
 * * Base Case:
 * - If we go out of bounds, return a very small number (Integer.MIN_VALUE / 2) 
 * to represent an invalid path.
 * - At the bottom-right cell:
 * - If it's a robber and we have neutralizations left, we can choose the max 
 * of (0) or (coins[i][j]).
 * - Otherwise, we must take the value.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(m * n * 3) -> O(m * n)
 * - Each state (i, j, k) is computed exactly once due to memoization.
 * Space Complexity: O(m * n * 3) -> O(m * n)
 * - Required for the 3D DP table and the recursion stack.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

class Solution {
    int m, n;
    // Using Long to avoid integer overflow issues during intermediate steps,
    // though the constraints suggest Integer might suffice.
    Long[][][] dp;

    private long solve(int[][] coins, int i, int j, int k) {
        // Out of bounds
        if (i >= m || j >= n) {
            return Long.MIN_VALUE / 2; 
        }

        // Base case: Reached the destination
        if (i == m - 1 && j == n - 1) {
            long currentVal = coins[i][j];
            if (currentVal < 0 && k > 0) {
                // We can either neutralize the last robber or take the loss
                return Math.max(0L, currentVal);
            }
            return currentVal;
        }

        // Memoization check
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }

        // Option 1: Standard path (Don't neutralize current cell)
        long currentVal = coins[i][j];
        long moveDown = currentVal + solve(coins, i + 1, j, k);
        long moveRight = currentVal + solve(coins, i, j + 1, k);
        long best = Math.max(moveDown, moveRight);

        // Option 2: Neutralize current cell (Only if it's a robber and we have k > 0)
        if (currentVal < 0 && k > 0) {
            long skipDown = 0 + solve(coins, i + 1, j, k - 1);
            long skipRight = 0 + solve(coins, i, j + 1, k - 1);
            best = Math.max(best, Math.max(skipDown, skipRight));
        }

        return dp[i][j][k] = best;
    }

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        dp = new Long[m][n][3];

        return (int) solve(coins, 0, 0, 2);
    }
}
