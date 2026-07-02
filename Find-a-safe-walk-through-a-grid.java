/**
 * PROBLEM STATEMENT:
 * Given an m x n binary matrix 'grid' and an integer 'health', determine if 
 * there is a path from (0,0) to (m-1, n-1) such that the total health 
 * consumed (by moving into cells with value 1) is less than the initial 'health'.
 *
 * OPTIMAL SOLUTION:
 * Use 0-1 BFS (Breadth-First Search) because the edge weights are only 0 or 1.
 * 1. Initialize a distance matrix 'dist' with infinity, setting the start cell.
 * 2. Use a Deque to store cells to visit.
 * 3. For each neighbor:
 * - If weight is 0, add to the front of the deque (priority).
 * - If weight is 1, add to the back of the deque.
 * 4. This maintains the sorted order of path costs, ensuring O(M*N) efficiency.
 *
 * COMPLEXITY:
 * - Time: O(M * N), where M and N are dimensions of the grid.
 * - Space: O(M * N) for the distance matrix and deque.
 */

import java.util.*;

class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();

        int[][] result = new int[m][n];
        for (int[] row : result) Arrays.fill(row, Integer.MAX_VALUE);

        Deque<int[]> dq = new ArrayDeque<>();
        result[0][0] = grid.get(0).get(0); 
        dq.offerFirst(new int[]{0, 0});

        while (!dq.isEmpty()) {
            int[] cell = dq.pollFirst();
            int r = cell[0], c = cell[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) 
                continue;

                if (result[r][c] + grid.get(nr).get(nc) < result[nr][nc]) {
                    result[nr][nc] = result[r][c] + grid.get(nr).get(nc);
                    
                    if (grid.get(nr).get(nc) == 0)
                        dq.offerFirst(new int[]{nr, nc});
                    else
                        dq.offerLast(new int[]{nr, nc});
                }
            }
        }

        return health - result[m-1][n-1] >= 1;
    }
}
