/*
 * ==========================================
 * PROBLEM STATEMENT: Create Grid With Exactly One Path
 * ==========================================
 * Given two integers `m` and `n`, representing the number of rows and columns of a grid, 
 * construct any m x n grid consisting only of the characters '.' (free cell) and '#' (obstacle) 
 * such that there is exactly one valid path from the top-left cell (0, 0) to the bottom-right cell (m - 1, n - 1).
 * 
 * Valid moves are only Right or Down.
 * 
 * Constraints:
 * 1 <= m, n <= 25
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Simple Construction)
 * ==========================================
 * - Approach:
 *   1. To guarantee a single unique path from the top-left to the bottom-right, we can block 
 *      the entire grid with obstacles ('#').
 *   2. Then, carve out a single pathway by setting the entire **first row** and the entire **last column** to '.'.
 *   3. This creates a unique shape: starting at (0,0), going all the way right across the top row to (0, n-1), 
 *      and then going straight down the last column to (m-1, n-1). Any other path would require going down earlier, 
 *      which hits walls ('#').
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n) to populate the grid cells.
 *   - Space Complexity: O(m * n) to build the result array of strings.
 */

import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        
        // Step 1: Initialize the entire grid with obstacles ('#')
        for (int i = 0; i < m; i++) {
            Arrays.fill(g[i], '#');
        }
        
        // Step 2: Open up the entire first row
        Arrays.fill(g[0], '.');
        
        // Step 3: Open up the entire last column
        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }
        
        // Convert the 2D character matrix into an array of Strings
        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(g[i]);
        }
        
        return ans;
    }
}
