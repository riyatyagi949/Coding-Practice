/**
 * PROBLEM STATEMENT: 3643. Flip Square Submatrix Vertically
 * --------------------------------------------------------------------------------
 * You are given an m x n integer matrix 'grid', and three integers x, y, and k.
 * - (x, y) represents the row and column indices of the top-left corner of a 
 * square submatrix.
 * - k represents the side length of that square submatrix.
 * * Your task is to flip the square submatrix vertically by reversing the order 
 * of its rows.
 * * Return the updated matrix.
 * * Example:
 * Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], x=1, y=0, k=3
 * Output: [[1,2,3,4],[13,14,15,8],[9,10,11,12],[5,6,7,16]]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: IN-PLACE ROW SWAPPING
 * --------------------------------------------------------------------------------
 * 1. Define the Range:
 * The submatrix rows range from 'x' to 'x + k - 1'.
 * The submatrix columns range from 'y' to 'y + k - 1'.
 * * 2. Vertical Flip Logic:
 * To flip vertically, we swap the first row of the submatrix with the last row,
 * the second row with the second-to-last, and so on, until we reach the middle.
 * * 3. Implementation:
 * - Use two pointers, 'top' starting at x and 'bottom' starting at x + k - 1.
 * - While top < bottom:
 * - Iterate through each column 'col' from y to y + k - 1.
 * - Swap grid[top][col] and grid[bottom][col].
 * - Increment top, decrement bottom.
 * * 4. Constraint Handling:
 * The constraints (m, n <= 50) are small, so this O(k^2) approach is highly 
 * efficient.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(k^2)
 * - We iterate through half the rows of the submatrix (k/2).
 * - For each row pair, we iterate through k columns.
 * - Total swaps: (k/2) * k = O(k^2).
 * * Space Complexity: O(1)
 * - The transformation is performed in-place on the original grid.
 * - Only a few integer variables are used for pointers and swapping.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int top = x;
        int bottom = x + k - 1;

        while (top < bottom) {
            for (int col = y; col < y + k; col++) {
                int temp = grid[top][col];
                grid[top][col] = grid[bottom][col];
                grid[bottom][col] = temp;
            }
            top++;
            bottom--;
        }

        return grid;
    }
}

