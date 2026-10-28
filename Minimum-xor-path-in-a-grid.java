/*
 * ==========================================
 * PROBLEM STATEMENT: 3882. Minimum XOR Path in a Grid
 * ==========================================
 * You are given a 2D integer array `grid` of size `m * n`. You start at the top-left cell `(0, 0)` 
 * and want to reach the bottom-right cell `(m - 1, n - 1)`. At each step, you may move either right or down.
 * The cost of a path is defined as the bitwise XOR of all the values in the cells along that path, 
 * including the start and end cells. Return the minimum possible XOR value among all valid paths.
 * 
 * Constraints:
 * 1 <= m == grid.length <= 1000
 * 1 <= n == grid[i].length <= 1000
 * m * n <= 1000
 * 0 <= grid[i][j] <= 1023
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (BFS / Dynamic Programming with XOR States)
 * ==========================================
 * - Approach:
 *   1. Since grid cell values are bounded between `0` and `1023` (less than $2^{10}$), the accumulated 
 *      XOR sum along any path will also be within that range ($0$ to $1023$).
 *   2. We can use a 2D boolean array or bitset for each cell `(r, c)` to keep track of all possible 
 *      XOR values reachable at that cell.
 *   3. Alternatively, a Breadth-First Search (BFS) or DP approach using a visited state table `dp[r][c][xor_val]` 
 *      can efficiently propagate possible XOR outcomes from top-left to bottom-right.
 *   4. At the destination cell `(m - 1, n - 1)`, we find the minimum XOR value that was successfully reached.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n * MAX_XOR), where MAX_XOR = 1024, which easily fits within limits given `m * n <= 1000`.
 *   - Space Complexity: O(m * n * MAX_XOR) to store reachable XOR states for each grid coordinate.
 */

class Solution {
    public int minXorPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Maximum possible XOR value based on constraints (grid[i][j] <= 1023)
        int MAX_XOR = 1024;
        
        boolean[][] visited = new boolean[MAX_XOR, MAX_XOR]; // conceptual or dynamic tracking per cell
        
        // Using a 2D array of boolean arrays to track reachable XORs at each cell
        boolean[][] dp = new boolean[m * n][MAX_XOR];
        
        // A simpler alternative is BFS/DFS with memoization or layer-by-layer bitsets.
        // Let's implement using a queue or BFS propagation for state tracking:
        boolean[][][] reachable = new boolean[m][n][MAX_XOR];
        
        reachable[0]0[0]] = true;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                for (int xorVal = 0; xorVal < MAX_XOR; xorVal++) {
                    if (!reachable[r][c][xorVal]) continue;
                    
                    // Move Right
                    if (c + 1 < n) {
                        int nextXor = xorVal ^ grid[r][c + 1];
                        reachable[r][c + 1][nextXor] = true;
                    }
                    
                    // Move Down
                    if (r + 1 < m) {
                        int nextXor = xorVal ^ grid[r + 1][c];
                        reachable[r + 1][c][nextXor] = true;
                    }
                }
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell
        for (int xorVal = 0; xorVal < MAX_XOR; xorVal++) {
            if (reachable[m - 1][n - 1][xorVal]) {
                return xorVal;
            }
        }
        
        return 0;
    }
}
