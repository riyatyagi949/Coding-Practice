/**
 * PROBLEM STATEMENT: 1886. Determine Whether Matrix Can Be Obtained By Rotation
 * --------------------------------------------------------------------------------
 * Given two n x n binary matrices 'mat' and 'target', return true if it is 
 * possible to make 'mat' equal to 'target' by rotating 'mat' in 90-degree 
 * increments (0, 90, 180, or 270 degrees).
 * * Constraints:
 * - n == mat.length == target.length
 * - 1 <= n <= 10
 * - mat[i][j] and target[i][j] are either 0 or 1.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SIMULATED ROTATION
 * --------------------------------------------------------------------------------
 * 1. Rotation Limit:
 * A square matrix returns to its original state after four 90-degree rotations 
 * (360 degrees total). Therefore, we only need to check the equality of 'mat' 
 * and 'target' at 0, 1, 2, and 3 rotations.
 * * 2. Rotation Formula:
 * For a 90-degree clockwise rotation, an element at position (row, col) moves to:
 * - New Row = col
 * - New Col = (n - 1) - row
 * * 3. Algorithm:
 * - Loop 4 times (for 0, 90, 180, and 270 degrees):
 * - Check if current 'mat' is identical to 'target'. If yes, return true.
 * - If not, rotate 'mat' 90 degrees clockwise.
 * - If the loop finishes without a match, return false.
 * * 4. Optimization:
 * Since n is very small (max 10), either creating a new matrix for rotation 
 * or performing an in-place transpose + reverse is extremely fast.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(4 * n^2) => O(n^2)
 * - We check for equality up to 4 times. Each check takes O(n^2).
 * - We rotate the matrix up to 3 times. Each rotation takes O(n^2).
 * - Total operations are proportional to the number of elements in the matrix.
 * * Space Complexity: O(n^2)
 * - We create a temporary 2D array to store the rotated version of the matrix.
 * - Given n <= 10, this uses negligible memory.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java ---------------------------------

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int k = 0; k < 4; k++) {
            if (isEqual(mat, target))
             return true;

            mat = rotate(mat);
        }
        return false;
    }
     private boolean isEqual(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j])
                 return false;
            }
        }
        return true;
    }
    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - i - 1] = mat[i][j];
            }
        }
        
        return rotated;
    }
}

