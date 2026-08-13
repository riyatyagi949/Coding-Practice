/*
 * ==========================================
 * PROBLEM STATEMENT: Longest Path in a Directed Acyclic Graph
 * ==========================================
 * Given a weighted Directed Acyclic Graph (DAG) with V vertices numbered from 0 to V - 1, 
 * represented by edges[][], where edges[i] = [u, v, w] denotes a directed edge from u to v with weight w, 
 * and a source vertex src.
 * 
 * Return the distance array, where the value at index i represents the longest distance from src to vertex i.
 * If a vertex is unreachable from src, store Integer.MIN_VALUE for that vertex (displayed as INF).
 * 
 * Constraints:
 * 1 <= V <= 10^4
 * 0 <= src <= V - 1
 * 1 <= edges.size() <= V * (V - 1) / 2
 * 0 <= edges[i][0], edges[i][1] < V
 * -100 <= edges[i][2] <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Topological Sort + DP)
 * ==========================================
 * - Approach:
 *   1. Topological Sort: Since the graph is a Directed Acyclic Graph (DAG), we can compute a 
 *      valid topological ordering of the vertices using Kahn's algorithm (indegree-based queue).
 *   2. Dynamic Programming over Topological Order: 
 *      - Initialize a distance array `dist` of size V with `Integer.MIN_VALUE`, and set `dist[src] = 0`.
 *      - Process vertices sequentially in topological order. For each vertex `u`, if it is reachable 
 *        (`dist[u] != Integer.MIN_VALUE`), relax all its outgoing edges `(v, w)`:
 *        `dist[v] = Math.max(dist[v], dist[u] + w)`.
 * 
 * - Complexity:
 *   - Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges. 
 *     Constructing the graph, finding the topological order, and relaxing edges all take linear time.
 *   - Space Complexity: O(V + E) for storing the adjacency list, indegree array, queue, and distance array.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] maxDistance(int V, int src, ArrayList<ArrayList<Integer>> edges) {
        // Step 1: Build adjacency list and compute indegrees for topological sort
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        int[] indegree = new int[V];
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (ArrayList<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);
            int w = e.get(2);
            
            adj.get(u).add(new int[]{v, w});
            indegree[v]++;
        }
        
        // Step 2: Kahn's Algorithm to find topological ordering
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        
        ArrayList<Integer> topo = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                indegree[v]--;
                
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        
        // Step 3: Initialize distance array with MIN_VALUE and set source distance to 0
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[src] = 0;
        
        // Step 4: Relax edges in topological order to find the longest paths
        for (int u : topo) {
            if (dist[u] == Integer.MIN_VALUE) {
                continue; // Unreachable node from source, skip
            }
            
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int w = edge[1];
                
                dist[v] = Math.max(dist[v], dist[u] + w);
            }
        }
        
        return dist;
    }
}
