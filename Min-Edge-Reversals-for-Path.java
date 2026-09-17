/*
 * ==========================================
 * PROBLEM STATEMENT: Min Edge Reversals for Path
 * ==========================================
 * Given a directed graph with `n` vertices and a set of directed edges, find the minimum 
 * number of edges that need to be reversed so that there exists at least one path from `src` to `dst`.
 * If it is not possible to create a path from `src` to `dst`, return -1.
 * 
 * Constraints:
 * 1 <= n, m <= 10^5
 * 1 <= edges[i][0], edges[i][1] <= n
 * 1 <= src, dst <= n
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (0-1 BFS / Shortest Path in Weighted Graph)
 * ==========================================
 * - Approach:
 *   1. Graph Modeling: 
 *      - For each original directed edge `u -> v`, we add a forward edge with a cost/weight of `0` 
 *        (since moving along the existing direction requires zero reversals).
 *      - We also add a backward edge from `v -> u` with a cost/weight of `1` (representing an edge reversal).
 *   2. 0-1 BFS (Double-Ended Queue):
 *      - Since edge weights are only `0` or `1`, Dijkstra's algorithm can be optimized using a 
 *        `Deque` (0-1 BFS).
 *      - When a neighbor `v` is reached with a cost of `0`, we add it to the **front** of the deque.
 *      - When a neighbor `v` is reached with a cost of `1`, we add it to the **back** of the deque.
 *      - This guarantees that nodes are processed in non-decreasing order of distance.
 * 
 * - Complexity:
 *   - Time Complexity: O(N + M), where `N` is the number of vertices and `M` is the number of edges, 
 *     since each vertex and edge is processed a constant number of times.
 *   - Space Complexity: O(N + M) to store the adjacency list representation of the graph, distance array, and deque.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    public int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {
        // Step 1: Build the adjacency list with weighted edges (0 for original direction, 1 for reverse)
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(new int[]{v, 0}); // Original edge: cost 0
            graph[v].add(new int[]{u, 1}); // Reversed edge: cost 1
        }

        // Step 2: Initialize distance array and 0-1 BFS deque
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();

        dist[src] = 0;
        deque.addFirst(src);

        // Step 3: Perform 0-1 BFS traversal
        while (!deque.isEmpty()) {
            int u = deque.pollFirst();

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int cost = edge[1];

                // Relaxation step
                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;

                    if (cost == 0) {
                        deque.addFirst(v); // 0-cost edges get priority at the front
                    } else {
                        deque.addLast(v);  // 1-cost edges go to the back
                    }
                }
            }
        }

        // Return minimum reversals to reach `dst`, or -1 if unreachable
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
