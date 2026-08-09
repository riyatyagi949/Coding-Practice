/*
 * ==========================================
 * PROBLEM STATEMENT: Largest Zigzag Sequence
 * ==========================================
 * Given a square matrix mat[][] of size n x n. 
 * A zigzag sequence starts from the top and ends at the bottom. 
 * Two consecutive elements of the sequence cannot belong to the same column.
 * Return the maximum sum of such a zigzag sequence.
 * 
 * Constraints:
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 1000
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming)
 * ==========================================
 * - Approach: We use a 1D DP array of size n to track the maximum zigzag path sum 
 *   ending at each column of the current row.
 * - Constraint Handling: Since consecutive elements cannot be in the same column, 
 *   for each row transition, we track the top two maximum values (max1 and max2) 
 *   from the previous row's DP array along with the index of the absolute maximum (maxIndex).
 *   - If the current column matches maxIndex, we pick max2.
 *   - Otherwise, we pick max1.
 * - Complexity:
 *   - Time Complexity: O(n^2), as we iterate through all elements of the n x n matrix.
 *   - Space Complexity: O(n), using a 1D array for DP instead of a 2D matrix.
 */

class Solution {
    public int zigzagSequence(int[][] mat) {
        int n = mat.length;

        // dp[j] stores the maximum zigzag sequence sum ending at column j of the current row
        int[] dp = new int[n];

        // Base case: initialize with the first row of the matrix
        for (int j = 0; j < n; j++) {
            dp[j] = mat[0][j];
        }
        
        // Iterate through rows from top to bottom
        for (int i = 1; i < n; i++) {
            int max1 = Integer.MIN_VALUE;
            int max2 = Integer.MIN_VALUE;
            int maxIndex = -1;

            // Find the largest (max1) and second largest (max2) values in the previous dp array
            for (int j = 0; j < n; j++) {
                if (dp[j] > max1) {
                    max2 = max1;
                    max1 = dp[j];
                    maxIndex = j;
                }
                else if (dp[j] > max2) {
                    max2 = dp[j];
                }
            }

            int[] newDp = new int[n];

            // Compute the new DP state for the current row
            for (int j = 0; j < n; j++) {
                int bestPrevious;

                // If the current column matches the column of the absolute max from the previous row,
                // we must pick the second-largest value to satisfy the zigzag condition (no same column).
                if (j == maxIndex) {
                    bestPrevious = max2;
                } 
                else {
                    bestPrevious = max1;
                }
               newDp[j] = mat[i][j] + bestPrevious;
            }
            // Move to the next row's state
            dp = newDp;
        }
      // Find the maximum sum present in the final row's DP array
        int answer = 0;
        for (int value : dp) {
            answer = Math.max(answer, value);
        }
        
        return answer;
    }
}
