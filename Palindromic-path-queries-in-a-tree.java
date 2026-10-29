/*
 * ==========================================
 * PROBLEM STATEMENT: Palindromic Path Queries in a Tree
 * ==========================================
 * Given an undirected tree with `n` nodes, an initial string `s` representing character assignments,
 * and a list of queries (either updating a node's character or checking if the path between two nodes 
 * can form a palindrome). Return a boolean array for the query results.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Heavy-Light Decomposition + Segment Tree)
 * ==========================================
 * - Approach:
 *   1. A string can be rearranged into a palindrome if and only if at most one character has an odd frequency.
 *   2. Represent character frequencies using a 26-bit mask, where the i-th bit indicates whether the count 
 *      of the i-th lowercase letter is odd (using XOR toggles).
 *   3. Since updates and path queries are dynamic, use Heavy-Light Decomposition (HLD) combined with a Segment Tree 
 *      to maintain and query the range XOR sums of character masks along tree paths.
 *   4. For each query, evaluate the path mask. A path forms a valid palindrome if the resulting mask has 
 *      at most one bit set, checked via `mask == 0 || (mask & (mask - 1)) == 0`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n + q * log^2 n), where `n` is the number of nodes and `q` is the number of queries.
 *   - Space Complexity: O(n) for the tree, HLD arrays, and segment tree structures.
 */

import java.util.*;

class Solution {
    private List<Integer>[] tree;
    private int[] parent, depth, heavy, head, pos, sz;
    private int[] treeMasks;
    private int curPos;
    private int[] segTree;
    private char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        sz = new int[n];
        treeMasks = new int[n];

        Arrays.fill(heavy, -1);
        dfs1(0, -1, 0);
        curPos = 0;
        dfs2(0, 0);

        segTree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                chars[u] = c;
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                ans.add(mask == 0 || (mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        sz[u] = 1;
        int maxSubTree = 0;
        for (int v : tree[u]) {
            if (v != p) {
                int sub = dfs1(v, u, d + 1);
                sz[u] += sub;
                if (sub > maxSubTree) {
                    maxSubTree = sub;
                    heavy[u] = v;
                }
            }
        }
        return sz[u];
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos;
        treeMasks[curPos++] = 1 << (chars[u] - 'a');
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : tree[u]) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            segTree[node] = treeMasks[l];
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            segTree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(node * 2, l, mid, idx, val);
        else update(node * 2 + 1, mid + 1, r, idx, val);
        segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return segTree[node];
        int mid = (l + r) / 2;
        return query(node * 2, l, mid, ql, qr) ^ query(node * 2 + 1, mid + 1, r, ql, qr);
    }

    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int t = u; u = v; v = t;
            }
            res ^= query(1, 0, chars.length - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int t = u; u = v; v = t;
        }
        res ^= query(1, 0, chars.length - 1, pos[u], pos[v]);
        return res;
    }
}
