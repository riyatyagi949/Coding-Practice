/*
 * ==========================================
 * PROBLEM STATEMENT: Longest Colored Path
 * ==========================================
 * Given an undirected acyclic graph (tree) with `n` nodes where each node is colored either Red ('R') or 
 * Blue ('B'). A valid path must follow the structure: Only Red nodes, Only Blue nodes, or Some Red nodes 
 * followed by some Blue nodes (Blue -> Red transition is forbidden). Find the maximum number of nodes in a valid path.
 * 
 * Constraints:
 * s.size() <= 10^5
 * 1 <= edges[i][j] <= s.size()
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Tree Dynamic Programming / Re-rooting / Bottom-Up Tree DP)
 * ==========================================
 * - Approach:
 *   1. We can view any valid path in the tree as passing through a highest node (the "turning point" or root of the path).
 *   2. For each node `v`, we compute paths that can combine at `v`:
 *      - Path coming from one subtree (or parent) with valid color constraints (e.g., matching the prefix/suffix condition).
 *      - Path going into another subtree.
 *   3. We use BFS to establish a topological ordering from the root (`0`), and then process nodes bottom-up 
 *      to compute dynamic programming states (`same`, `arm`, `bef`) and combine the top two maximum paths from 
 *      distinct children to find the global maximum path length (`ans`).
 * 
 * - Complexity:
 *   - Time Complexity: O(N), since each node and edge is visited a constant number of times.
 *   - Space Complexity: O(N) auxiliary space for the adjacency list, DP arrays, and queue.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int longestPath(String s, int[][] edges) {
        int n = s.length();
        
        // Step 1: Build adjacency list for the undirected tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Step 2: Perform BFS to get a bottom-up processing order (parent-child relationships)
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        int[] order = new int[n];
        boolean[] visited = new boolean[n];
        int idx = 0;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[idx++] = u;
            for (int w : adj.get(u)) {
                if (!visited[w]) {
                    visited[w] = true;
                    parent[w] = u;
                    queue.add(w);
                }
            }
        }

        int[] same = new int[n]; 
        int[] arm = new int[n];  
        int[] bef = new int[n]; 
        
        Arrays.fill(same, 1);
        Arrays.fill(arm, 1);
        Arrays.fill(bef, 1);

        // Keep track of top 2 maximum values and their child indices for combining paths efficiently
        int[] b1v = new int[n], b2v = new int[n];
        int[] b1c = new int[n], b2c = new int[n];
        int[] a1v = new int[n], a2v = new int[n];
        int[] a1c = new int[n], a2c = new int[n];
        Arrays.fill(b1c, -1); Arrays.fill(b2c, -1);
        Arrays.fill(a1c, -1); Arrays.fill(a2c, -1);

        int ans = 1;

        // Step 3: Process nodes bottom-up (reverse BFS order)
        for (int i = n - 1; i >= 0; i--) {
            int v = order[i];

            int[] bVals = {b1v[v], b2v[v]};
            int[] bChs  = {b1c[v], b2c[v]};
            int[] aVals = {a1v[v], a2v[v]};
            int[] aChs  = {a1c[v], a2c[v]};

            int best = 0;
            for (int bi = 0; bi < 2; bi++) {
                for (int ai = 0; ai < 2; ai++) {
                    if (bChs[bi] != aChs[ai]) {
                        best = Math.max(best, bVals[bi] + aVals[ai]);
                    }
                }
            }
            ans = Math.max(ans, 1 + best);

            int p = parent[v];
            if (p == -1) continue;
            char sv = s.charAt(v), sp = s.charAt(p);

            if (sv == sp) same[p] = Math.max(same[p], 1 + same[v]);

            if (sp == 'R' || sv == 'B') {
                arm[p] = Math.max(arm[p], 1 + arm[v]);
            }

            if (sp == 'B' || sv == 'R') {
                bef[p] = Math.max(bef[p], 1 + bef[v]);
            }

            int candBefore, candAfter;
            if (sp == 'R') {
                candBefore = (sv == 'R') ? same[v] : 0;
                candAfter = arm[v];
            } else {
                candBefore = bef[v];
                candAfter = (sv == 'B') ? same[v] : 0;
            }

            // Update top 2 before candidates
            if (candBefore > b1v[p]) {
                b2v[p] = b1v[p]; b2c[p] = b1c[p];
                b1v[p] = candBefore; b1c[p] = v;
            } else if (candBefore > b2v[p]) {
                b2v[p] = candBefore; b2c[p] = v;
            }

            // Update top 2 after candidates
            if (candAfter > a1v[p]) {
                a2v[p] = a1v[p]; a2c[p] = a1c[p];
                a1v[p] = candAfter; a1c[p] = v;
            } else if (candAfter > a2v[p]) {
                a2v[p] = candAfter; a2c[p] = v;
            }
        }
        
        return ans;
    }
}
