/**
 * PROBLEM STATEMENT: 3070. Count Submatrices with Top-Left Element and Sum Less Than k
 * --------------------------------------------------------------------------------
 * You are given a 0-indexed integer matrix 'grid' and an integer 'k'.
 * * Task: Return the number of submatrices that contain the top-left element 
 * of the grid (grid[0][0]) and have a sum less than or equal to k.
 * * Example:
 * Input: grid = [[7,6,3],[6,6,1]], k = 18
 * Output: 4
 * Explanation: 
 * - Submatrix (0,0) to (0,0): sum = 7 (<= 18)
 * - Submatrix (0,0) to (0,1): sum = 7 + 6 = 13 (<= 18)
 * - Submatrix (0,0) to (1,0): sum = 7 + 6 = 13 (<= 18)
 * - Submatrix (0,0) to (1,1): sum = 7 + 6 + 6 + 6 = 25 (> 18, Stop)
 * Total valid submatrices = 4.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: 2D PREFIX SUM (IN-PLACE)
 * --------------------------------------------------------------------------------
 * 1. Concept:
 * A submatrix containing the top-left element (0,0) and ending at (i,j) is 
 * simply the prefix sum of the rectangle from (0,0) to (i,j).
 * * 2. Recurrence Relation:
 * To calculate the sum of the rectangle ending at (i, j) efficiently:
 * Sum(i, j) = grid[i][j] + Sum(i-1, j) + Sum(i, j-1) - Sum(i-1, j-1)
 * - Sum(i-1, j): Sum of the rectangle directly above.
 * - Sum(i, j-1): Sum of the rectangle to the left.
 * - Sum(i-1, j-1): The overlapping area added twice, so we subtract it once.
 * * 3. Optimization:
 * We can perform this calculation in-place by updating the 'grid' array itself
 * to save space, assuming the input can be modified.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - We iterate through every cell in the M x N grid exactly once to compute 
 * the prefix sum and increment the counter.
 * * Space Complexity: O(1)
 * - We update the grid in-place. If modifying the input is not allowed, 
 * the complexity would be O(M * N) to store the prefix sum matrix.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -------------------

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i > 0) grid[i][j] += grid[i - 1][j];
                if (j > 0) grid[i][j] += grid[i][j - 1];
                if (i > 0 && j > 0) grid[i][j] -= grid[i - 1][j - 1];

                if (grid[i][j] <= k) 
                count++;
            }
        }
        return count;
    }
}

