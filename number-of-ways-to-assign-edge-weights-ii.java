/**
 * PROBLEM STATEMENT: 3559. Number of Ways to Assign Edge Weights II
 * --------------------------------------------------------------------------------
 * Given an undirected tree, assign weights (1 or 2) to each edge.
 * For multiple queries [u, v], find the number of ways to assign weights to the
 * edges on the path between u and v such that the total path cost is odd.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: LCA + Combinatorics
 * --------------------------------------------------------------------------------
 * 1. Path length between two nodes u and v in a tree is given by:
 * dist(u, v) = depth[u] + depth[v] - 2 * depth[LCA(u, v)].
 * 2. Similar to the single path case, if path length is 'L', we need an odd number
 * of edges to have weight 1.
 * 3. The number of ways to choose an odd number of weight-1 edges out of L edges
 * is 2^(L-1), provided L > 0. If L == 0 (u == v), the cost is 0 (even), 
 * so the answer is 0.
 * 4. We use Binary Lifting (LCA algorithm) to find LCA efficiently for each query.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O((N + Q) log N)
 * - O(N log N) for preprocessing the Binary Lifting table (DFS + lifting).
 * - O(Q log N) to answer Q queries using LCA.
 * Space Complexity: O(N log N)
 * - O(N log N) to store the sparse table for LCA.
 * --------------------------------------------------------------------------------
 */
import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;
    int LOG;
    int[][] up;
    int[] depth;
    long[] pow2;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        dfs(1, 0, graph);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                int mid = up[i][j - 1];
                up[i][j] = mid == 0 ? 0 : up[mid][j - 1];
            }
        }

        pow2 = new long[n + 1];
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int lca = getLCA(u, v);

            int len = depth[u] + depth[v] - 2 * depth[lca];

            if (len == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) pow2[len - 1];
            }
        }

        return ans;
    }

    private void dfs(int node, int parent,
                     ArrayList<Integer>[] graph) {

        up[node][0] = parent;

        for (int nei : graph[node]) {

            if (nei == parent) continue;

            depth[nei] = depth[node] + 1;

            dfs(nei, node, graph);
        }
    }

    private int getLCA(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = LOG - 1; j >= 0; j--) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {

            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }
}

