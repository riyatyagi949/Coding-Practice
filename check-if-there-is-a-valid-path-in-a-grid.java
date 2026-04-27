/**
 * PROBLEM STATEMENT: 1391. Check if There is a Valid Path in a Grid
 * --------------------------------------------------------------------------------
 * You are given an m x n grid where each cell represents a street type (1-6).
 * Each street type connects two specific directions:
 * 1: Left <-> Right
 * 2: Upper <-> Lower
 * 3: Left <-> Lower
 * 4: Right <-> Lower
 * 5: Left <-> Upper
 * 6: Right <-> Upper
 * * You start at (0, 0) and must reach (m-1, n-1).
 * A path is valid only if the current street connects to the next street AND 
 * the next street connects back to the current street (bidirectional connection).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Depth-First Search (DFS) or BFS
 * --------------------------------------------------------------------------------
 * 1. Representation: Use a map or 3D array to store the two allowed directions 
 * for each street type.
 * 2. Connectivity Check: When moving from (r, c) to (nr, nc), we must verify:
 * - (nr, nc) is within bounds.
 * - (nr, nc) has not been visited.
 * - One of the directions of street at (nr, nc) points back to (r, c).
 * 3. Search: Perform DFS starting from (0, 0). If we reach (m-1, n-1), return true.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - Each cell is visited at most once. For each cell, we check 2 directions.
 * Space Complexity: O(M * N)
 * - visited array takes O(M * N).
 * - Recursion stack in the worst case (a single snake-like path) takes O(M * N).
 * --------------------------------------------------------------------------------
 */
// Code ---
import java.util.HashMap;
import java.util.Map;

class Solution {
    int m, n;
    Map<Integer, int[][]> directions = new HashMap<>();

    public Solution() {
        directions.put(1, new int[][]{{0, -1}, {0, 1}});
        directions.put(2, new int[][]{{-1, 0}, {1, 0}});
        directions.put(3, new int[][]{{0, -1}, {1, 0}});
        directions.put(4, new int[][]{{0, 1}, {1, 0}});
        directions.put(5, new int[][]{{0, -1}, {-1, 0}});
        directions.put(6, new int[][]{{-1, 0}, {0, 1}});
    }

    public boolean dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i == m - 1 && j == n - 1)
            return true;

        visited[i][j] = true;

        for (int[] dir : directions.get(grid[i][j])) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];

            if (new_i < 0 || new_i >= m || new_j < 0 || new_j >= n || visited[new_i][new_j])
                continue;

            for (int[] backDir : directions.get(grid[new_i][new_j])) {
                if (new_i + backDir[0] == i && new_j + backDir[1] == j) {
                    if (dfs(grid, new_i, new_j, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean hasValidPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        return dfs(grid, 0, 0, visited);
    }
}

