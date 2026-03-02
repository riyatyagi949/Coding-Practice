/**
 * PROBLEM STATEMENT: 1536. Minimum Swaps to Arrange a Binary Grid
 * --------------------------------------------------------------------------------
 * Given an n x n binary grid, in one step you can choose two adjacent rows 
 * and swap them.
 * * A grid is said to be valid if all the cells above the main diagonal are zeros.
 * Return the minimum number of steps (swaps) needed to make the grid valid, 
 * or -1 if the grid cannot be valid.
 * * The main diagonal starts at (0, 0) and ends at (n-1, n-1).
 * For a row 'i', to be valid, it must have at least (n - 1 - i) trailing zeros.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY APPROACH
 * --------------------------------------------------------------------------------
 * 1. Pre-processing:
 * For each row, count the number of trailing zeros. Store these counts in 
 * an array 'trailingZeros'.
 * * 2. Requirements:
 * - Row 0 needs at least (n - 1) trailing zeros.
 * - Row 1 needs at least (n - 2) trailing zeros.
 * - Row i needs at least (n - 1 - i) trailing zeros.
 * * 3. Greedy Selection:
 * Iterate through each row position 'i' from 0 to n-1:
 * a. Find the first row 'j' (where j >= i) in our current trailingZeros 
 * array that satisfies the requirement: trailingZeros[j] >= (n - 1 - i).
 * b. If no such row exists, return -1 (impossible).
 * c. If such a row is found at index 'j', it must be moved to index 'i'. 
 * The number of swaps required is (j - i).
 * d. Simulate the swap by shifting all elements from index i to j-1 down by one.
 * * 4. Sum up all swaps.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - Counting trailing zeros: O(N^2).
 * - Greedy selection loop (N) with inner search and shift (N): O(N^2).
 * - Given N <= 200, N^2 = 40,000, which is well within performance limits.
 * * Space Complexity: O(N)
 * - To store the trailing zeros count for each of the N rows.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int minSwaps(int[][] grid) {
         int n = grid.length; 

        int[] endZeros = new int[n];

        for (int i = 0; i < n; i++) {
            int j = n - 1; 

            int count = 0;
            while (j >= 0 && grid[i][j] == 0) {
                count++;
                j--;
            }
           endZeros[i] = count;
        }
        int steps = 0;

        for (int i = 0; i < n; i++) {
             int need = n - i - 1;
             int j = i;

            while (j < n && endZeros[j] < need) {
                j++;
            }

            if (j == n) {
                return -1;
            }
            steps += (j - i);

            while (j > i) {
                int temp = endZeros[j];
                endZeros[j] = endZeros[j - 1];
                endZeros[j - 1] = temp;
                j--;
            }
        }
        return steps;
    }
}

