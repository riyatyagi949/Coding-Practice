/*
 * ==========================================
 * PROBLEM STATEMENT: Check if There Is a Valid Parentheses String Path
 * ==========================================
 * Given an `m x n` matrix of parentheses `grid`. A valid parentheses string path starts from (0, 0), 
 * ends at (m - 1, n - 1), only moves down or right, and forms a valid parentheses string. 
 * Return true if there exists a valid parentheses string path, otherwise false.
 * 
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] is either '(' or ')'
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming / Memoized 3D Boolean Table)
 * ==========================================
 * - Approach:
 *   1. Length Parity Check: The total length of any path from (0,0) to (m-1,n-1) is `m + n - 1`. 
 *      If this length is odd, a valid parentheses string cannot be formed (must have an even length).
 *   2. Boundary Condition: The path must start with `'('` at `(0, 0)` and end with `')'` at `(m - 1, n - 1)`.
 *   3. State Definition: We use a 3D table `t[i][j][openCount]`, representing whether it's possible to reach 
 *      the bottom-right from cell `(i, j)` with a given net balance of unmatched open parentheses (`openCount`).
 *   4. Bottom-Up DP: We iterate backward from the bottom-right corner to the top-left, building up valid 
 *      configurations based on valid transitions from `(i+1, j)` and `(i, j+1)`.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n * (m + n)), since we visit every grid cell and iterate over possible valid `openCount` states up to `m + n`.
 *   - Space Complexity: O(m * n * (m + n)) to store the 3D DP memoization/tabular array.
 */

class Solution {
    int m, n;
    boolean[][][] t;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Step 1: A valid parentheses string must have an even length
        if ((m + n - 1) % 2 == 1)
            return false;

        // Step 2: Start must be '(' and end must be ')'
        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
            return false;
        }
        
        // Maximum possible open count is bounded by the maximum path length from (0,0), which is m + n
        t = new boolean[m][n][201];

        // Step 3: Bottom-up DP filling table from (m-1, n-1) back to (0, 0)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int openCount = 0; openCount <= i + j + 1; openCount++) {
                    if (i == m - 1 && j == n - 1) {
                        t[i][j][openCount] = (openCount == 0);
                        continue;
                    }
                    t[i][j][openCount] = false;

                    // Transition from cell below (i + 1, j)
                    if (i + 1 < m) {
                        int newOpCount = (grid[i + 1][j] == '(') ? openCount + 1 : openCount - 1;
                        if (newOpCount >= 0 && t[i + 1][j][newOpCount]) {
                            t[i][j][openCount] = true;
                        }
                    }
                    // Transition from cell to the right (i, j + 1)
                    if (j + 1 < n) {
                        int newOpCount = (grid[i][j + 1] == '(') ? openCount + 1 : openCount - 1;
                        if (newOpCount >= 0 && t[i][j + 1][newOpCount]) {
                            t[i][j][openCount] = true;
                        }
                    }
                }
            }
        }
        
        // Return result starting at (0,0) with initial open parenthesis count = 1
        return t[0][0][1];
    }
}
