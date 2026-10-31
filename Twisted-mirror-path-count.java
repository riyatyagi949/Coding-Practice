/*
 * ==========================================
 * PROBLEM STATEMENT: 3665. Twisted Mirror Path Count
 * ==========================================
 * Given an m x n binary grid where 0 represents an empty cell and 1 represents a mirror.
 * A robot starts at (0, 0) and wants to reach (m - 1, n - 1), moving only right or down.
 * If the robot attempts to move into a mirror cell, it is reflected:
 * - Moving right into a mirror turns it down (into the cell directly below the mirror).
 * - Moving down into a mirror turns it right (into the cell directly to the right of the mirror).
 * Return the number of different valid paths modulo 10^9 + 7.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming with State Tracking)
 * ==========================================
 * - Approach:
 *   1. Define a 3D DP table `t[row][col][cameFrom]`, where `cameFrom` tracks the direction 
 *      from which the current cell is entered (`LEFT` or `UP`).
 *   2. Base case: At the target cell `(m - 1, n - 1)`, the path count is 1.
 *   3. If a cell contains a mirror (`grid[row][col] == 1`):
 *      - If coming from `LEFT` (trying to move right into the mirror), it gets reflected `DOWN`, 
 *        so its value depends on `t[row + 1][col][UP]`.
 *      - If coming from `UP` (trying to move down into the mirror), it gets reflected `RIGHT`, 
 *        so its value depends on `t[row][col + 1][LEFT]`.
 *   4. If it's an empty cell (`grid[row][col] == 0`), it can receive paths from both directions: 
 *      summing paths from `t[row][col + 1][LEFT]` and `t[row + 1][col][UP]` modulo 10^9 + 7.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n) since we iterate through each cell and constant directions.
 *   - Space Complexity: O(m * n) for the 3D DP tracking table.
 */

class Solution {
    int LEFT = 0; 
    int UP = 1;   
    int MOD = 1_000_000_007;
    int m, n;

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int[][][] t = new int[m + 1][n + 1][2];

        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                for (int cameFrom = 0; cameFrom < 2; cameFrom++) {

                    if (row == m - 1 && col == n - 1) {
                        t[row][col][cameFrom] = 1;
                        continue;
                    }
                    
                    if (grid[row][col] == 1) {
                        if (cameFrom == LEFT)
                            t[row][col][cameFrom] = t[row + 1][col][UP];  
                        else
                            t[row][col][cameFrom] = t[row][col + 1][LEFT]; 
                        continue;
                    }
                    
                    long total = 0;
                    total += t[row][col + 1][LEFT]; 
                    total += t[row + 1][col][UP];   

                    t[row][col][cameFrom] = (int) (total % MOD);
                }
            }
        }
        
        return t[0][0][LEFT]; 
    }
}
