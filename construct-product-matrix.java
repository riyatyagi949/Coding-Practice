/**
 * PROBLEM STATEMENT: 2906. Construct Product Matrix
 * --------------------------------------------------------------------------------
 * Given a 0-indexed 2D integer matrix 'grid' of size n * m, construct a 2D matrix 'p'
 * of the same size where:
 * - Each element p[i][j] is the product of ALL elements in 'grid' EXCEPT grid[i][j].
 * - Every calculation must be taken modulo 12345.
 * * Return the resulting product matrix.
 * * Constraints:
 * - 1 <= n, m <= 10^5
 * - 2 <= n * m <= 10^5
 * - 1 <= grid[i][j] <= 10^9
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: PREFIX AND SUFFIX PRODUCTS
 * --------------------------------------------------------------------------------
 * 1. The Division Problem:
 * Normally, the product of all elements except one is (Total Product / Element).
 * However, since we need the result modulo 12345, division is only possible if 
 * the element has a modular multiplicative inverse. 12345 is not prime, and many 
 * elements in the grid might share factors with 12345, making division impossible.
 * * 2. Prefix/Suffix Strategy:
 * We can represent the product of all elements except grid[i][j] as:
 * (Product of all elements before grid[i][j]) * (Product of all elements after grid[i][j])
 * * 3. Two-Pass Algorithm:
 * - Pass 1 (Suffix): Traverse the matrix backwards (bottom-right to top-left).
 * Store the cumulative product of all elements seen so far (the "suffix") 
 * into the result matrix p[i][j].
 * - Pass 2 (Prefix): Traverse the matrix forwards (top-left to bottom-right).
 * Maintain a "prefix" product. Multiply the existing suffix value in p[i][j]
 * by the current prefix, then update the prefix with grid[i][j].
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * M)
 * - We iterate through the entire matrix exactly twice.
 * Space Complexity: O(1)
 * - Excluding the output matrix, we only use a few long variables for products.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java ----------------------------------

class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int MOD = 12345;
        int n = grid.length;
        int m = grid[0].length;

        int[][] p = new int[n][m]; 

        long suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int) suffix;
                suffix = (suffix * grid[i][j]) % MOD;
            }
        }

        long prefix = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) ((prefix * p[i][j]) % MOD);
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }

        return p;
    }
}

