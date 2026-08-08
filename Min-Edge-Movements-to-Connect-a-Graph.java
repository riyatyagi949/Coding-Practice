// Problem Statement:
// Given a graph with n vertices and m edges. 
// You can remove one edge and add it anywhere. 
// Find the minimum operations to connect the graph. Return -1 if impossible.
// Constraints: 1 <= n <= 10^5, 1 <= m <= 10^5.

// Optimal Solution in Java:
// We need at least (n - 1) edges to connect n vertices (a tree structure).
// If the total number of edges m < n - 1, it is impossible to connect the graph.
// Otherwise, the number of operations required is equal to the number of connected components minus 1.
// Runtime: O(m * alpha(n)) where alpha is the inverse Ackermann function (practically constant).
// Space Complexity: O(n) to store the DSU parent and rank arrays.

class Solution {
    int[] parent;
    int[] rank;

    // Find function with path compression
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union function with union by rank
    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        }
        else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } 
        else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }
    
    int minEdgesReq(int n, int[][] edges) {
        // If we have fewer than n-1 edges, we cannot connect n vertices.
        if (edges.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int components = n;
        
      // Perform union for each edge and track the number of connected components
        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                components--;
            }
        }
        
        // The minimum operations needed to connect 'components' pieces 
        // is (components - 1).
        return components - 1;
    }
}
