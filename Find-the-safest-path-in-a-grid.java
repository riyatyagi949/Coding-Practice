/**
 * PROBLEM STATEMENT:
 * Find a path from (0,0) to (n-1, n-1) that maximizes the "safeness factor."
 * The safeness factor is the minimum Manhattan distance from any cell in the path to any thief.
 *
 * OPTIMAL SOLUTION:
 * 1. Multi-source BFS: Calculate the distance of every empty cell to its nearest thief.
 * 2. Binary Search or Max-Heap (Dijkstra-like): 
 * - Once we have the "safeness" value for every cell, we want to find a path 
 * that maximizes the minimum value along that path.
 * - A Max-Heap (Priority Queue) allows us to greedily pick the path with the 
 * highest current minimum safeness.
 * * COMPLEXITY:
 * - Time: O(N^2 log N) due to Multi-source BFS (O(N^2)) and Priority Queue operations (O(N^2 log N)).
 * - Space: O(N^2) to store the distance grid.
 */

import java.util.*;

public class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);
        
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.add(new int[]{i, j});
                }
            }
        }
        
        // Multi-source BFS to find Manhattan distance to nearest thief for all cells
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dirs) {
                int ni = curr[0] + d[0], nj = curr[1] + d[1];
                if (ni >= 0 && ni < n && nj >= 0 && nj < n && dist[ni][nj] == -1) {
                    dist[ni][nj] = dist[curr[0]][curr[1]] + 1;
                    q.add(new int[]{ni, nj});
                }
            }
        }
        
        // Max-Heap to keep track of the path with the highest minimum safeness
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.add(new int[]{0, 0, dist[0][0]});
        dist[0][0] = -1; // Mark as visited
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], safeness = curr[2];
            
            if (r == n - 1 && c == n - 1) return safeness;
            
            for (int[] d : dirs) {
                int ni = r + d[0], nj = c + d[1];
                if (ni >= 0 && ni < n && nj >= 0 && nj < n && dist[ni][nj] != -1) {
                    pq.add(new int[]{ni, nj, Math.min(safeness, dist[ni][nj])});
                    dist[ni][nj] = -1; // Mark as visited
                }
            }
        }
        
        return 0;
    }
}
