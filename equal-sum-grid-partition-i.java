/**
 * PROBLEM STATEMENT: 3546. Equal Sum Grid Partition I
 * --------------------------------------------------------------------------------
 * You are given an m x n matrix 'grid' of positive integers. Your task is to 
 * determine if it is possible to make either one horizontal or one vertical 
 * cut on the grid such that:
 * 1. Each of the two resulting sections formed by the cut is non-empty.
 * 2. The sum of the elements in both sections is equal.
 *
 * Return true if such a partition exists; otherwise return false.
 *
 * Constraints:
 * - 1 <= m, n <= 10^5
 * - 2 <= m * n <= 10^5 (The total number of elements is manageable)
 * - 1 <= grid[i][j] <= 10^5
 * --------------------------------------------------------------------------------
 */
/**
     * OPTIMAL SOLUTION: Single-Pass Prefix Accumulation
     * ----------------------------------------------------------------------------
     * 1. Total Sum: First, calculate the total sum of all elements. Since 
     * m * n can be 10^5 and elements can be 10^5, the total can reach 10^10,
     * so we must use a 'long'.
     * 2. Parity Check: If the total sum is odd, an equal integer split is impossible.
     * 3. Target: Our goal is to find a prefix sum (either row-wise or column-wise)
     * that equals totalSum / 2.
     * 4. Horizontal Cuts: We iterate through the rows, adding one row at a time.
     * We stop at m-1 because the sections must be non-empty.
     * 5. Vertical Cuts: We iterate through the columns, adding one column at a time.
     * We stop at n-1 to satisfy the non-empty constraint.
     * ----------------------------------------------------------------------------
     */
/**
 * TIME COMPLEXITY ANALYSIS:
 * -------------------------
 * O(M * N)
 * - Calculating the total sum visits every cell once: O(M * N).
 * - Checking horizontal cuts visits every cell once (across all row iterations): O(M * N).
 * - Checking vertical cuts visits every cell once (across all column iterations): O(M * N).
 * - Total time is 3 * O(M * N), which simplifies to O(M * N). Given M*N <= 10^5, this is optimal.
 *
 * SPACE COMPLEXITY ANALYSIS:
 * --------------------------
 * O(1)
 * - We only store a few long and int variables (total, target, prefix sums).
 * - No additional data structures (like prefix sum matrices) are required.
 */
// Code ---------------------------------------

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }
        if (total % 2 != 0)
         return false;

        long target = total / 2;

        long sum = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
            }
            if (sum == target) 
            return true;
        }
         sum = 0;

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                sum += grid[i][j];
            }
            if (sum == target) 
            return true;
        }

        return false;
    }
}
