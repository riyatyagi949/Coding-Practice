/**
 * PROBLEM STATEMENT: 3548. Equal Sum Grid Partition II
 * --------------------------------------------------------------------------------
 * Given an m x n grid of positive integers, determine if a single horizontal 
 * or vertical cut can split it into two non-empty sections such that:
 * 1. The sums of both sections are equal OR can be made equal by removing ONE cell.
 * 2. If a cell is removed, the remaining part of that section must stay connected.
 * * Return true if such a partition exists, otherwise false.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION:
 * --------------------------------------------------------------------------------
 * - We calculate the total sum of the grid using 'long' to avoid overflow.
 * - We check all possible horizontal cuts. For each cut, we calculate the sum of 
 * the Top and Bottom sections.
 * - We check for three cases: 
 * a) Sums are already equal.
 * b) Top sum - cell = Bottom sum.
 * c) Bottom sum - cell = Top sum.
 * - Connectivity: In a rectangular section, removing a cell keeps it connected 
 * UNLESS it's a 1D section (1 row or 1 column). In 1D, only the ends are valid.
 * - We handle vertical cuts by transposing the grid and reusing the horizontal logic.
 * --------------------------------------------------------------------------------
 * COMPLEXITY:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - We iterate over the grid cells to find the total sum and during the cut checks.
 * - Transposing and reversing rows are also O(M * N).
 * Space Complexity: O(M * N)
 * - Required to store the grid, the transposed grid, and the HashSet of cell values.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

import java.util.HashSet;

class Solution {
    long total = 0;

    public boolean checkHorCuts(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        HashSet<Long> set = new HashSet<>();
        long top = 0;

        for (int i = 0; i < m - 1; i++) {
             for (int j = 0; j < n; j++) {
                set.add((long)grid[i][j]);
                top += grid[i][j];
            }
            long bottom = total - top;
            long diff = top - bottom;

            if (diff == 0) 
            return true;

            if (diff == grid[0][0]) 
            return true;
            if (diff == grid[0][n - 1])
             return true;
            if (diff == grid[i][0])
             return true;

            if (i > 0 && n > 1 && set.contains(diff)) {
                return true;
            }
        }
        return false;
    }
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }
        if (checkHorCuts(grid)) 
        return true;

        reverse(grid);

        if (checkHorCuts(grid)) 
        return true;

        reverse(grid);

        int[][] transposeGrid = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposeGrid[j][i] = grid[i][j];
            }
        }
        if (checkHorCuts(transposeGrid)) 
        return true;

        reverse(transposeGrid);

        return checkHorCuts(transposeGrid);
    }
    private void reverse(int[][] grid) {
        int top = 0, bottom = grid.length - 1;

        while (top < bottom) {
            int[] temp = grid[top];
            grid[top] = grid[bottom];
            grid[bottom] = temp;

            top++;
            bottom--;
        }
    }
}

