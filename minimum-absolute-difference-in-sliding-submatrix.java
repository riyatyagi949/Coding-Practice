/**
 * PROBLEM STATEMENT: 3567. Minimum Absolute Difference in Sliding Submatrix
 * --------------------------------------------------------------------------------
 * You are given an m x n integer matrix 'grid' and an integer k.
 * For every contiguous k x k submatrix of grid, compute the minimum absolute 
 * difference between any two distinct values within that submatrix.
 * * Return a 2D array 'ans' of size (m - k + 1) x (n - k + 1), where ans[i][j] 
 * is the minimum absolute difference in the submatrix whose top-left corner is (i, j).
 * * Note: 
 * - If all elements in the submatrix have the same value, the answer is 0.
 * - The absolute difference is |a - b| for any two distinct elements in the submatrix.
 * * Constraints:
 * - 1 <= m, n <= 30 (Small constraints suggest we can afford a higher time complexity)
 * - -10^5 <= grid[i][j] <= 10^5
 * - 1 <= k <= min(m, n)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW WITH TREESET
 * --------------------------------------------------------------------------------
 * 1. Brute Force with Sorting:
 * For each possible top-left corner (i, j) of a k x k submatrix:
 * - Collect all k^2 elements.
 * - Sort the unique elements.
 * - The minimum absolute difference must occur between two adjacent elements 
 * in the sorted list.
 * * 2. Why this works for these constraints:
 * Given M, N <= 30, the number of submatrices is roughly 30 * 30 = 900.
 * For each submatrix, we process k*k elements (max 900).
 * Total operations: ~900 * 900 * log(900) ≈ 10^7, which fits well within 1 second.
 * * 3. TreeSet Usage:
 * - Using a TreeSet automatically handles uniqueness and sorting.
 * - If the size of the set is less than the total elements but greater than 1,
 * it means duplicates existed. If all were duplicates, size is 1.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N * K^2 * log(K^2))
 * - Outer loops run (M-K+1) * (N-K+1) times.
 * - Inner loops collect K^2 elements.
 * - TreeSet insertion takes O(log(K^2)).
 * - Final linear scan of the set takes O(K^2).
 * * Space Complexity: O(M * N + K^2)
 * - Result array takes O(M * N).
 * - TreeSet takes O(K^2) per submatrix.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -----------------------------------

import java.util.TreeSet;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] result = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

             TreeSet<Integer> vals = new TreeSet<>();

            for (int r = i; r <= i + k - 1; r++) {
                for (int c = j; c <= j + k - 1; c++) {
                        vals.add(grid[r][c]);
                    }
                }
                if (vals.size() == 1) {
                    continue;
                }
                 int minAbsDiff = Integer.MAX_VALUE;

                Integer prev = null;
                for (int val : vals) {
                    if (prev != null) {
                        minAbsDiff = Math.min(minAbsDiff, val - prev);
                    }
                    prev = val;
                }
                 result[i][j] = minAbsDiff;
            }
        }
        return result;
    }
}

