/**
 * PROBLEM STATEMENT: 1559. Detect Cycles in 2D Grid
 * --------------------------------------------------------------------------------
 * Given a 2D array of characters 'grid' of size m x n, determine if there exists 
 * any cycle consisting of the same value.
 * * Rules for a valid cycle:
 * 1. Path length must be 4 or more.
 * 2. Starts and ends at the same cell.
 * 3. Movements are restricted to 4 directions (up, down, left, right).
 * 4. All cells in the cycle must have the same character value.
 * 5. You cannot move to the cell that was visited in your immediate last move 
 * (prevents trivial back-and-forth "cycles").
 * * Return true if any cycle exists, otherwise return false.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Depth-First Search (DFS) with Parent Tracking
 * --------------------------------------------------------------------------------
 * 1. Concept:
 * - We treat the grid as an undirected graph where an edge exists between two 
 * adjacent cells if they share the same character value.
 * - A cycle in an undirected graph is detected if we encounter a 'visited' node 
 * that is NOT the parent (the cell we just came from).
 * * 2. Algorithm:
 * - Maintain a 'visited' 2D boolean array to keep track of processed cells.
 * - Iterate through every cell in the grid. If it hasn't been visited, start a DFS.
 * - During DFS:
 * a. Mark the current cell as visited.
 * b. Explore all 4 neighbors.
 * c. If a neighbor is within bounds and has the same character:
 * - If the neighbor is the 'parent' (px, py), skip it.
 * - If the neighbor is already 'visited' (and not the parent), a cycle is found!
 * - Otherwise, recursively call DFS for that neighbor.
 * * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - We visit each cell in the grid at most once across all DFS calls.
 * Space Complexity: O(M * N)
 * - The 'visited' array takes O(M * N) space.
 * - The recursion stack can go up to O(M * N) in the worst-case (a long path).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    int m, n;
    boolean[][] visited;
    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && dfs(grid, i, j, -1, -1, grid[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] grid, int x, int y, int px, int py, char target) {
        visited[x][y] = true;

        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) 
            continue;

            if (grid[nx][ny] != target) 
            continue;

            if (nx == px && ny == py)
             continue;

            if (visited[nx][ny])
             return true;

            if (dfs(grid, nx, ny, x, y, target))
             return true;
        }

        return false;
    }
}

