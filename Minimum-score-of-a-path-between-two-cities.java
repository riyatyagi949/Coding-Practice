/**
 * PROBLEM STATEMENT: 2492. Minimum Score of a Path Between Two Cities
 * --------------------------------------------------------------------------------
 * You are given n cities and a list of bidirectional roads with associated distances.
 * The "score" of a path is defined as the minimum distance of any road in that path.
 * Your goal is to find the minimum possible score among all paths starting at city 1 
 * and ending at city n. 
 * Note: You can traverse roads and visit cities multiple times. This effectively 
 * means the answer is simply the minimum weight of any road in the entire 
 * connected component that contains both city 1 and city n.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Breadth-First Search (BFS) / Graph Traversal
 * --------------------------------------------------------------------------------
 * 1. Build an adjacency list representation of the graph.
 * 2. Since we can traverse any road in the connected component of city 1, 
 * perform a BFS (or DFS) starting from city 1 to visit all reachable cities.
 * 3. During the traversal, keep track of the minimum road weight encountered 
 * across all edges connected to the reachable nodes.
 * 4. The final result is the global minimum weight found during this traversal.
 * --------------------------------------------------------------------------------
 * 
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(V + E), where V is the number of cities and E is the number 
 * of roads. We visit each node and edge at most once.
 * Space Complexity: O(V + E) to store the adjacency list and the visited array.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Build the adjacency list where each entry is [neighbor, distance]
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] road : roads) {
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }

        // BFS traversal to find all reachable edges in the component
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(1);
        visited[1] = true;

        int minScore = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int[] edge : graph[node]) {
                // Update the minimum score found so far
                minScore = Math.min(minScore, edge[1]);

                // If the neighbor city hasn't been visited, add to queue
                if (!visited[edge[0]]) {
                    visited[edge[0]] = true;
                    q.offer(edge[0]);
                }
            }
        }

        return minScore;
    }
}
