/**
 * PROBLEM STATEMENT: 2946. Matrix Similarity After Cyclic Shifts
 * --------------------------------------------------------------------------------
 * You are given an m x n integer matrix 'mat' and an integer 'k'.
 * * The following process happens k times:
 * 1. Even-indexed rows (0, 2, 4, ...) are cyclically shifted to the left.
 * 2. Odd-indexed rows (1, 3, 5, ...) are cyclically shifted to the right.
 * * Return true if the final modified matrix after k steps is identical to the 
 * original matrix, and false otherwise.
 * * Constraints:
 * - 1 <= m, n <= 25
 * - 1 <= mat[i][j] <= 25
 * - 1 <= k <= 50
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: MODULAR PERIODICITY CHECK
 * --------------------------------------------------------------------------------
 * A matrix is "similar" after cyclic shifts if every row remains unchanged by 
 * the shift. For a row to remain unchanged after a cyclic shift of 'k' positions, 
 * the element at any index 'j' must be equal to the element at the index it 
 * "moved from" (or "moved to").
 * * 1. Effective Shift:
 * A shift of 'k' positions is equivalent to a shift of $k \pmod n$ positions, 
 * where 'n' is the number of columns.
 * * 2. The Symmetry of Cyclic Shifts:
 * Whether we shift a row left or right by 'k', the condition for the row 
 * remaining identical is the same:
 * - For a left shift: $mat[i][j]$ must equal $mat[i][(j + k) \pmod n]$.
 * - For a right shift: $mat[i][j]$ must equal $mat[i][(j - k + n) \pmod n]$.
 * * Mathematically, if $mat[i][j] = mat[i][(j + k) \pmod n]$ for all $j$, then 
 * the row is periodic and will also satisfy the right-shift condition. Therefore, 
 * we can simplify the logic by checking the same condition for all rows.
 * * 3. Algorithm:
 * - Normalize $k = k \pmod n$.
 * - Iterate through every element $mat[i][j]$.
 * - Check if $mat[i][j] == mat[i][(j + k) \pmod n]$.
 * - If any element fails this check, return false.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: $O(m \times n)$
 * - We traverse each element of the $m \times n$ matrix exactly once.
 * * Space Complexity: $O(1)$
 * - We perform the check in-place without using any additional data structures.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Reduce k to the effective number of shifts within one row length
        k = k % n;
        
        // If k is 0 or a multiple of n, the matrix doesn't change
        if (k == 0) return true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // For similarity, the element at current index must match
                // the element k positions away (cyclically).
                // This covers both left and right shifts because similarity
                // implies the row is periodic with respect to k.
                if (mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
