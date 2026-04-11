/**
 * PROBLEM STATEMENT: Toeplitz Matrix
 * --------------------------------------------------------------------------------
 * A Toeplitz matrix is a matrix where every descending diagonal from left to right 
 * contains the same element.
 * * Given a rectangular matrix 'mat', determine if it is a Toeplitz matrix.
 * * Example 1:
 * Input: mat[][] = [[6, 7, 8],
 * [4, 6, 7],
 * [1, 4, 6]]
 * Output: true
 * Explanation: 
 * - Main diagonal: 6 -> 6 -> 6 (Constant)
 * - Upper diagonals: [7, 7], [8] (Constant)
 * - Lower diagonals: [4, 4], [1] (Constant)
 * * Example 2:
 * Input: mat[][] = [[6, 3, 8],
 * [4, 9, 7]]
 * Output: false
 * Explanation: The primary diagonal elements are [6, 9], which are not the same.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single-Pass Neighbor Comparison
 * --------------------------------------------------------------------------------
 * 1. Observation:
 * In a Toeplitz matrix, for any element at mat[i][j], it must be equal to 
 * the element at mat[i-1][j-1] (if that neighbor exists).
 * * 2. Algorithm:
 * - Iterate through the matrix starting from the second row (i = 1) and 
 * the second column (j = 1).
 * - For each element mat[i][j], compare it with its top-left neighbor mat[i-1][j-1].
 * - If any element does not match its top-left neighbor, return false immediately.
 * - If the loops complete, return true.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * M)
 * - We visit each element of the matrix exactly once.
 * Space Complexity: O(1)
 * - No extra data structures are used; we perform an in-place comparison.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java ------------------------------

class Solution {
    public boolean isToeplitz(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] != mat[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

