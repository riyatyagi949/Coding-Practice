/*
 * PROBLEM STATEMENT:
 * Given a 2D grid of size m x n and an integer 'k', shift the grid 'k' times.
 * In one shift operation:
 * - Element at grid[i][j] moves to grid[i][j + 1].
 * - Element at grid[i][n - 1] moves to grid[i + 1][0].
 * - Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying the shift operation 'k' times.
 * 
 * OPTIMAL SOLUTION APPROACH:
 * 1. Flatten the 2D grid conceptually into a 1D array of size total = m * n.
 * 2. Each element at 2D coordinates (i, j) can be mapped to a 1D index using the formula:
 *    oldIndex = i * n + j.
 * 3. After shifting 'k' times, the new position in the 1D layout becomes:
 *    newIndex = (oldIndex + k) % total.
 * 4. We can map this new 1D index back to 2D coordinates (newRow, newCol) using:
 *    newRow = newIndex / n
 *    newCol = newIndex % n
 * 5. Place each element from the original grid into its calculated new position in a result grid, 
 *    then convert the 2D array into a List of Lists as required by the return type.
 * 
 * Time Complexity: O(m * n), since we visit every element in the grid exactly once.
 * Space Complexity: O(m * n) to store the shifted result grid and output lists.
 */

import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        // Reduce k to avoid redundant full rotations
        k %= total;

        int[][] shifted = new int[m][n];

        // Map every element from its old position to its new shifted position
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int oldIndex = i * n + j;
                int newIndex = (oldIndex + k) % total;

                int newRow = newIndex / n;
                int newCol = newIndex % n;

                shifted[newRow][newCol] = grid[i][j];
            }
        }

        // Convert the 2D array result into a List of Lists
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[i][j]);
            }
            ans.add(row);
        }

        return ans;
    }
}
