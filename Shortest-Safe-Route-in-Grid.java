/*
 * ==========================================
 * PROBLEM STATEMENT: Shortest Safe Route in Grid
 * ==========================================
 * Given a 2D matrix `mat[][]` of size `n x m`, where each cell is either 0 (landmine) or 1 (safe), 
 * find the minimum number of steps required to travel from any cell in the leftmost column 
 * to any cell in the rightmost column. You can move in four directions (up, down, left, right).
 * A cell is unsafe if it contains a landmine or is directly adjacent to a landmine.
 * Return -1 if no safe path exists.
 * 
 * Constraints:
 * 1 <= n, m <= 10^3
 * 0 <= mat[i][j] <= 1
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Breadth-First Search & Preprocessing)
 * ==========================================
 * - Approach:
 *   1. Preprocessing (Marking Unsafe Cells): 
 *      - Iterate through the entire grid to find all landmines (`0`).
 *      - For each landmine, mark its own cell and all 4 directly adjacent neighboring cells as `unsafe`.
 *   2. Breadth-First Search (BFS):
 *      - Since all edge weights are uniform (1 step), BFS is optimal for finding the shortest path.
 *      - Initialize a queue and add all valid starting cells in the leftmost column (`c = 0`) 
 *        that are marked as safe (`!unsafe[i][0]`).
 *      - Perform a level-order traversal by expanding to neighboring safe cells.
 *      - The first time we reach any cell in the rightmost column (`c = m - 1`), return its distance.
 *      - If the queue becomes empty and the rightmost column hasn't been reached, return -1.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * m), since we visit each cell a constant number of times during preprocessing and BFS traversal.
 *   - Space Complexity: O(n * m) to maintain the `unsafe` grid, `visited` tracking, and the BFS queue.
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int shortestPath(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] unsafe = new boolean[n][m];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 1: Preprocess the grid to mark landmines and their adjacent cells as unsafe
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    unsafe[i][j] = true;

                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                            unsafe[nr][nc] = true;
                        }
                    }
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // Step 2: Push all valid starting positions in the leftmost column into the queue
        for (int i = 0; i < n; i++) {
            if (!unsafe[i][0]) {
                q.offer(new int[]{i, 0, 1}); // {row, col, distance}
                visited[i][0] = true;
            }
        }

        // Step 3: Perform BFS to find the shortest path to the rightmost column
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            // Reached the rightmost column
            if (c == m - 1) {
                return dist;
            }
          for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !unsafe[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        return -1;
    }
}
