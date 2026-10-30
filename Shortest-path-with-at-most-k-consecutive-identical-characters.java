/*
 * ==========================================
 * PROBLEM STATEMENT: Shortest Path With At Most K Consecutive Identical Characters
 * ==========================================
 * You are given an integer `n` representing the number of nodes in a directed weighted graph, 
 * a list of edges, node labels, and an integer `k`. Find the minimum total edge weight of a path 
 * from node 0 to node n - 1 such that the concatenation of the labels along the path contains 
 * at most `k` consecutive identical characters. If no valid path exists, return -1.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dijkstra with State Tracking)
 * ==========================================
 * - Approach:
 *   1. This is a shortest path problem on a graph where the state at any node depends not just 
 *      on the current node, but also on the last character seen and how many times it has consecutively appeared.
 *   2. Use Dijkstra's algorithm where the priority queue stores states `(cost, u, lastChar, streakCount)`.
 *   3. Since the graph can revisit nodes with different streak states, keep a minimum cost table/map 
 *      indexed by `(node, lastChar, streakCount)` to avoid redundant states and ensure convergence.
 *   4. For each neighbor, check if the edge label matches the `lastChar`. If yes, increment the streak count 
 *      (provided it doesn't exceed `k`). If it's a different character, reset the streak count to 1.
 * 
 * - Complexity:
 *   - Time Complexity: O(E * k * 26 * log(V * k * 26)) due to the expanded state space in Dijkstra's algorithm.
 *   - Space Complexity: O(V * k * 26) to store the visited distance states.
 */

import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u  = edge[0];
            int v  = edge[1];
            int wt = edge[2];

            adj.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[]{v, wt});
        }
        int[][] result = new int[n][k + 1];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        result[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d     = top[0];
            int node  = top[1];
            int count = top[2];

            if (d > result[node][count])
                continue;

            if (node == n - 1)
                return d;

            if (!adj.containsKey(node))
                continue;

            for (int[] vec : adj.get(node)) {
                int adjNode = vec[0];
                int wt      = vec[1];

                int newCount = (labels.charAt(adjNode) == labels.charAt(node)) ? count + 1 : 1;

                if (newCount > k)
                    continue;

                int newDist = d + wt;

                if (newDist < result[adjNode][newCount]) {
                    result[adjNode][newCount] = newDist;
                    pq.offer(new int[]{newDist, adjNode, newCount});
                }
            }
        }
         return -1;
    }
}

