/**
 * PROBLEM STATEMENT: 799. Champagne Tower
 * --------------------------------------------------------------------------------
 * We stack glasses in a pyramid, where the first row has 1 glass, the second row 
 * has 2 glasses, and so on until the 100th row. Each glass holds exactly 1 cup 
 * of champagne.
 * * When a glass is full, any excess liquid poured into it falls equally to the 
 * two glasses immediately below it (left and right). If those glasses become full, 
 * their excess falls to the next row, and so on.
 * * Given the amount of champagne poured at the top (row 0, glass 0), return 
 * how full the j-th glass in the i-th row is.
 * * Example:
 * Input: poured = 2, query_row = 1, query_glass = 1
 * Output: 0.50000
 * Explanation: 1 cup fills the top glass. The 1 cup excess splits into 0.5 for 
 * (1,0) and 0.5 for (1,1).
 * * Constraints:
 * 0 <= poured <= 10^9
 * 0 <= query_glass <= query_row < 100
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Dynamic Programming (Simulation)
 * --------------------------------------------------------------------------------
 * 1. Data Structure:
 * We use a 2D array `dp[101][101]` where `dp[r][c]` represents the total amount 
 * of champagne that *passed through* or *stayed in* the glass at row r, column c.
 * * 2. Simulation Logic:
 * - Start by pouring all `poured` champagne into `dp[0][0]`.
 * - Iterate through each row from 0 up to 99.
 * - For each glass `(r, c)`, if the amount is greater than 1:
 * - Calculate the overflow: `excess = (dp[r][c] - 1)`.
 * - Split the excess: `dp[r+1][c] += excess / 2.0` and `dp[r+1][c+1] += excess / 2.0`.
 * - (Optional for the query) We can cap `dp[r][c]` at 1.0, but it's cleaner 
 * to just do it at the end.
 * * 3. Edge Cases:
 * - The query might be for a glass that is still empty.
 * - The query might be for a glass that received more than 1 cup (it stayed full).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(R^2), where R is the number of rows (fixed at 100).
 * - We iterate through a nested loop of 100 * 100.
 * Space Complexity: O(R^2)
 * - We store the tower in a 101x101 matrix. 
 * - (Note: This can be optimized to O(R) space using a single array/row-by-row update).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in java - 
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];
        dp[0][0] = poured;
        
        for(int r = 0; r < 100; r++) {
            for(int c = 0; c <= r; c++) {
                if(dp[r][c] > 1)
                 {
                  double overflow = (dp[r][c] - 1) / 2.0;
                  dp[r + 1][c] += overflow;
                  dp[r + 1][c + 1] += overflow;
                  dp[r][c] = 1;
                }
            }
        }
      return Math.min(1, dp[query_row][query_glass]);
    }
}


