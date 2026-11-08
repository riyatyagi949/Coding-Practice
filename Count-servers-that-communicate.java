/*
 * ==========================================
 * PROBLEM STATEMENT: Count Servers that Communicate
 * ==========================================
 * Given a `m * n` integer matrix `grid` where `1` represents a server and `0` represents no server.
 * Two servers communicate if they are on the same row or column.
 * Return the total number of servers that communicate with at least one other server.
 * 
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 250
 * grid[i][j] == 0 or 1
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Row & Column Frequency Counting)
 * ==========================================
 * - Approach:
 *   1. Use two tracking arrays: `indexRowCount` of size `m` and `indexColCount` of size `n` 
 *      to count the total number of servers present in each row and column respectively.
 *   2. Iterate through the grid during a first pass to populate these counts.
 *   3. Iterate through the grid a second time: a server at `(row, col)` can communicate with 
 *      others if either its row has more than one server (`indexRowCount[row] > 1`) or its column 
 *      has more than one server (`indexColCount[col] > 1`).
 *   4. Accumulate and return the count of communicating servers.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n) by making two linear passes over the grid.
 *   - Space Complexity: O(m + n) to store the row and column server count arrays.
 */

class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] indexColCount = new int[n];
        int[] indexRowCount = new int[m];

        // First pass: count servers in each row and column
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) { 
                    indexColCount[col]++;
                    indexRowCount[row]++;
                }
            }
        }
        
        int resultServers = 0;
        
        // Second pass: check which servers have sharing partners in their row or column
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1 && (indexColCount[col] > 1 || indexRowCount[row] > 1)) {
                    resultServers++;
                }
            }
        }
        
        return resultServers;
    }
}
