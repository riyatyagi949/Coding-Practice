/**
 * PROBLEM STATEMENT: 3212. Count Submatrices With Equal Frequency of X and Y
 * --------------------------------------------------------------------------------
 * Given a 2D character matrix 'grid', where grid[i][j] is either 'X', 'Y', or '.', 
 * return the number of submatrices that satisfy three conditions:
 * 1. They must contain the top-left element grid[0][0].
 * 2. They must have an equal frequency of 'X' and 'Y'.
 * 3. They must contain at least one 'X'.
 * * Constraints:
 * - 1 <= grid.length, grid[i].length <= 1000
 * - grid[i][j] is either 'X', 'Y', or '.'.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: 2D PREFIX SUM (SATELLITE MATRICES)
 * --------------------------------------------------------------------------------
 * 1. Requirement:
 * A submatrix starting at (0,0) and ending at (i,j) is a rectangle. To check the 
 * frequency of 'X' and 'Y' in constant time, we use the 2D Prefix Sum technique.
 * * 2. Prefix Sum Logic:
 * We maintain two 2D arrays (or update them iteratively):
 * - countX[i][j]: Number of 'X's in the rectangle from (0,0) to (i,j).
 * - countY[i][j]: Number of 'Y's in the rectangle from (0,0) to (i,j).
 * * The recurrence for any point (i, j) is:
 * Total(i, j) = current_cell_value + Total(i-1, j) + Total(i, j-1) - Total(i-1, j-1)
 * * 3. Algorithm:
 * - Iterate through each cell (i, j) of the grid.
 * - Calculate the running count of 'X' and 'Y' for the submatrix ending at (i, j).
 * - If countX == countY and countX > 0, increment the result counter.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - We traverse the grid once. Each cell's prefix sum is calculated in O(1) time.
 * * Space Complexity: O(M * N)
 * - We store two prefix sum matrices. We can optimize this to O(N) using row-based 
 * prefix sums if space is a major constraint, but O(MN) is standard for these limits.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java ----------------------------

class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] cumSumX = new int[m][n];
        int[][] cumSumY = new int[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                cumSumX[i][j] = (grid[i][j] == 'X') ? 1 : 0;
                cumSumY[i][j] = (grid[i][j] == 'Y') ? 1 : 0;

                if (i - 1 >= 0) {
                    cumSumX[i][j] += cumSumX[i - 1][j];
                    cumSumY[i][j] += cumSumY[i - 1][j];
                }

                if (j - 1 >= 0) {
                    cumSumX[i][j] += cumSumX[i][j - 1];
                    cumSumY[i][j] += cumSumY[i][j - 1];
                }

                if (i - 1 >= 0 && j - 1 >= 0) {
                    cumSumX[i][j] -= cumSumX[i - 1][j - 1];
                    cumSumY[i][j] -= cumSumY[i - 1][j - 1];
                }

                if (cumSumX[i][j] == cumSumY[i][j] && cumSumX[i][j] > 0) {
                    count++;
                }
            }
        }

        return count;
    }
}

