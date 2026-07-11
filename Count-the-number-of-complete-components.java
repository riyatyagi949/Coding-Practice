/**
 * PROBLEM STATEMENT: 2685. Count the Number of Complete Components
 * --------------------------------------------------------------------------------
 * Given n vertices (0 to n-1) and an undirected graph via edges, return the number
 * of "complete" connected components.
 * * Definitions:
 * 1. Connected Component: A subgraph where every vertex is reachable from every 
 * other vertex, and no edges exist to vertices outside this component.
 * 2. Complete Component: A component where every distinct pair of vertices 
 * has a direct edge connecting them.
 * * Optimal Condition for a Complete Component:
 * For a component with 'V' vertices and 'E' edges:
 * A component is complete if and only if every vertex has a degree equal to (V - 1),
 * which results in the total number of edges being E = V * (V - 1) / 2.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Graph Traversal (DFS or BFS)
 * --------------------------------------------------------------------------------
 * 1. Build an adjacency list representation of the graph.
 * 2. Keep a 'visited' array to track processed vertices.
 * 3. Iterate through all vertices 0 to n-1. If a vertex is not visited:
 * a. Perform a DFS/BFS to find all vertices in its connected component.
 * b. During traversal, count the number of vertices (V) and the total number 
 * of edges (E) within that component.
 * c. Since each edge is counted twice (once for each endpoint), the actual 
 * edge count is (sum of degrees) / 2.
 * d. Check if E == V * (V - 1) / 2. If true, increment the count of 
 * complete components.
 * 4. Return the total count.
 * --------------------------------------------------------------------------------
 * COMPLEXITY:
 * --------------------------------------------------------------------------------
 * Time: O(V + E), where V is the number of vertices and E is the number of edges.
 * Space: O(V + E) for the adjacency list and visited array.
 */

import java.util.*;

class Solution {
    private void dfs(int i, Map<Integer, List<Integer>> adj, boolean[] visited, int[] info) {
        visited[i] = true;
        info[0]++;
        info[1] += adj.getOrDefault(i, new ArrayList<>()).size();
        
        for (int ngbr : adj.getOrDefault(i, new ArrayList<>())) {
            if (!visited[ngbr]) {
                dfs(ngbr, adj, visited, info);
            }
        }
    }
    
    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int result = 0;
        
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            int[] info = new int[2];
            dfs(i, adj, visited, info);
            
            if (info[0] * (info[0] - 1) == info[1]) {
                result++;
            }
        }
        
        return result;
    }
}
