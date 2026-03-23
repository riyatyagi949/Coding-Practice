/**
 * PROBLEM STATEMENT: 1594. Maximum Non Negative Product in a Matrix
 * --------------------------------------------------------------------------------
 * You are given an m x n matrix 'grid'. You start at (0, 0) and can only move 
 * right or down to reach (m-1, n-1).
 * * Find the path with the maximum non-negative product of all integers in the 
 * path cells.
 * * Return the maximum non-negative product modulo 10^9 + 7. 
 * If the maximum product is negative, return -1.
 * * Constraints:
 * - m, n <= 15
 * - -4 <= grid[i][j] <= 4
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DYNAMIC PROGRAMMING (Min-Max Tracking)
 * --------------------------------------------------------------------------------
 * 1. Why track both Min and Max?
 * Since the grid contains negative numbers, a very small negative product 
 * encountered earlier could become a very large positive product if multiplied 
 * by another negative number later. Thus, at each cell, we must store both 
 * the maximum and minimum possible products possible to reach that cell.
 * * 2. State Definition:
 * dpMax[i][j]: Maximum product to reach cell (i, j)
 * dpMin[i][j]: Minimum product to reach cell (i, j)
 * * 3. Transitions:
 * To reach (i, j), we come from (i-1, j) or (i, j-1).
 * If grid[i][j] is positive:
 * newMax = max(dpMax[up], dpMax[left]) * grid[i][j]
 * newMin = min(dpMin[up], dpMin[left]) * grid[i][j]
 * If grid[i][j] is negative:
 * newMax = min(dpMin[up], dpMin[left]) * grid[i][j]
 * newMin = max(dpMax[up], dpMax[left]) * grid[i][j]
 * * 4. Modulo Note:
 * The problem states "modulo is performed after getting the maximum product". 
 * With m,n <= 15 and values <= 4, the max product is roughly 4^29, which fits 
 * into a 'long' data type in Java.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - We traverse the grid once, performing constant time operations at each cell.
 * Space Complexity: O(M * N)
 * - We use two 2D arrays (dpMax and dpMin) to store the products. 
 * - Given M, N <= 15, this is very small.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    final int MOD = 1000000007;
     public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Pair<Long, Long>[][] t = new Pair[m][n];

        t[0][0] = new Pair<>((long) grid[0][0], (long) grid[0][0]);

        for (int j = 1; j < n; j++) {
            t[0][j] = new Pair<>(t[0][j - 1].getKey() * grid[0][j], t[0][j - 1].getValue() * grid[0][j]);
        }

        for (int i = 1; i < m; i++) {
            t[i][0] = new Pair<>(t[i - 1][0].getKey() * grid[i][0], t[i - 1][0].getValue() * grid[i][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long upMax = t[i - 1][j].getKey();
                long upMin = t[i - 1][j].getValue();

                long leftMax = t[i][j - 1].getKey();
                long leftMin = t[i][j - 1].getValue();

                t[i][j] = new Pair<>(
                    Math.max(Math.max(upMax * grid[i][j], upMin * grid[i][j]), Math.max(leftMax * grid[i][j], leftMin * grid[i][j])),
                    Math.min(Math.min(upMax * grid[i][j], upMin * grid[i][j]), Math.min(leftMax * grid[i][j], leftMin * grid[i][j]))
                );
            }
        }
        long maxProd = t[m - 1][n - 1].getKey();

        return maxProd < 0 ? -1 : (int) (maxProd % MOD);
    }
}

