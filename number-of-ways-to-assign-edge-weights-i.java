/**
 * PROBLEM STATEMENT: 3558. Number of Ways to Assign Edge Weights I
 * --------------------------------------------------------------------------------
 * Given an undirected tree, assign weights (1 or 2) to each edge.
 * For a node x at max depth L, find the number of ways to assign weights 
 * on the path from root (node 1) to x such that the total path cost is odd.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Combinatorics
 * --------------------------------------------------------------------------------
 * 1. Find max depth L using BFS.
 * 2. Total cost of path with L edges = k*1 + (L-k)*2, where k is count of edges 
 * with weight 1.
 * 3. Cost is 2L - k. For cost to be odd, k must be odd.
 * 4. Number of ways to pick an odd number of edges (k) to be weight 1 out 
 * of L edges is 2^(L-1).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N) to traverse the tree and find the max depth.
 * Space Complexity: O(N) to store the adjacency list and depth/visited arrays.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int maxDepth = 0;
        boolean[] visited = new boolean[n + 1];
        int[] depth = new int[n + 1];
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;
        depth[1] = 0;
        
        while (!q.isEmpty()) {
            int u = q.poll();
            maxDepth = Math.max(maxDepth, depth[u]);
            
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    depth[v] = depth[u] + 1;
                    q.add(v);
                }
            }
        }
        int L = maxDepth;
        return (int) modPow(2, L - 1, MOD);
    }

    private long modPow(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}

