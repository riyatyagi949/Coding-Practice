/**
 * PROBLEM STATEMENT: 3620. Network Recovery Pathways
 * --------------------------------------------------------------------------------
 * Given a Directed Acyclic Graph (DAG) with n nodes and m edges, where each edge has a 
 * recovery cost. Some nodes are offline (cannot be intermediate nodes in a path).
 * Nodes 0 and n-1 are always online.
 * A path from 0 to n-1 is valid if:
 * 1. All intermediate nodes are online.
 * 2. Total cost of edges on the path <= k.
 * For each valid path, the 'score' is the minimum edge cost encountered on that path.
 * Goal: Return the maximum possible score among all valid paths, or -1 if none exist.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Binary Search on Answer + Dynamic Programming (DAG)
 * --------------------------------------------------------------------------------
 * 1. The possible range for the score is [min(edge_cost), max(edge_cost)].
 * 2. We can binary search for the answer 'mid'. For a fixed 'mid', we need to check 
 * if there exists a path where every edge cost >= 'mid' and the total cost <= k.
 * 3. Checking function: Perform a standard DAG path-finding (Shortest Path). 
 * If an edge's cost is < 'mid', it's treated as unusable. We use topological 
 * order to compute the shortest path efficiently in O(V + E).
 * 4. Since it's a DAG, we process nodes in topological order to ensure we 
 * calculate the minimum cost to reach node 'n-1' correctly.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O((V + E) * log(max_cost)), where V is nodes, E is edges.
 * Binary search adds the log factor, and DAG shortest path is linear.
 * Space Complexity: O(V + E) to store the adjacency list and topological sort.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    static class Edge {
        int to;
        int cost;

        Edge(int t, int c) {
            to = t;
            cost = c;
        }
    }
public int findMaxPathScore(int[][] edges, boolean[] online, long k){
        int n = online.length;

        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int[] indegree = new int[n];

        int low = Integer.MAX_VALUE;
        int high = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
            indegree[e[1]]++;

            low = Math.min(low, e[2]);
            high = Math.max(high, e[2]);
        }

        if (edges.length == 0)
            return -1;

        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();

        int[] indeg = indegree.clone();

        for (int i = 0; i < n; i++)
            if (indeg[i] == 0)
                q.offer(i);

        int idx = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (Edge e : graph[u]) {
                if (--indeg[e.to] == 0)
                    q.offer(e.to);
            }
        }

        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(mid, graph, topo, online, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int minEdge, List<Edge>[] graph, int[] topo,
                          boolean[] online, long k) {

        int n = graph.length;

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {

            if (dist[u] == INF)
                continue;

            if (u != 0 && u != n - 1 && !online[u])
                continue;

            for (Edge e : graph[u]) {

                if (e.cost < minEdge)
                    continue;

                int v = e.to;

                if (v != n - 1 && !online[v])
                    continue;

                if (dist[v] > dist[u] + e.cost) {
                    dist[v] = dist[u] + e.cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}

