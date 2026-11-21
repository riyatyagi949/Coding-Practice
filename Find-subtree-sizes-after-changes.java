/*
 * ==========================================
 * PROBLEM STATEMENT: Find Subtree Sizes After Changes
 * ==========================================
 * Given a tree rooted at node 0 with `n` nodes, represented by `parent` (where `parent[0] == -1`)
 * and a string `s` of length `n` assigning a lowercase letter to each node.
 * Simultaneously for every node `x` (1 <= x < n):
 * - Find the closest proper ancestor `y` of `x` with `s[y] == s[x]`.
 * - If such `y` exists, detach `x` from its current parent and attach `x` as a child of `y`.
 * Return an array `answer` of size `n` where `answer[i]` is the subtree size of node `i`
 * in the resulting tree.
 *
 * Constraints:
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * s consists only of lowercase English letters.
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (DFS Ancestor Tracking + Post-Order Rollup)
 * ==========================================
 * - Approach:
 *   1. Build the original tree adjacency list from `parent` (root at node 0).
 *   2. Maintain `last[c]` as the nearest ancestor of the current node with character `'a' + c`,
 *      initialized to `-1`. Initialize `resultSize[i] = 1` for all nodes.
 *   3. During DFS at `currNode` with character `c`:
 *      - Record `nearestAncestor = last[c]` before setting `last[c] = currNode` (so `currNode`
 *        is available as an ancestor for its descendants, not itself).
 *      - Recursively process all children in the original tree.
 *      - Backtrack by restoring `last[c] = nearestAncestor`.
 *      - On exit (post-order), if `currNode != 0`, its final parent is `nearestAncestor` if
 *        `nearestAncestor != -1`, else its original `parent[currNode]`. Add `resultSize[currNode]`
 *        to `resultSize[newParent]`.
 *   4. Because children finish before their parents and re-parenting only moves a node to a
 *      strict ancestor in the original tree (preserving the DAG/tree hierarchy), post-order
 *      accumulation computes exact final subtree sizes in a single traversal.
 *
 * - Complexity:
 *   - Time Complexity: O(n) — each node and edge is visited once during DFS.
 *   - Space Complexity: O(n) for the adjacency list, result array, and recursion call stack.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    private int[] resultSize;
    private int[] last;

    private void dfs(int currNode, String s, int[] parent) {
        int c = s.charAt(currNode) - 'a';
        int nearestAncestor = last[c];

        // Expose currNode as the closest ancestor with character c to its descendants
        last[c] = currNode;

        for (int child : adj.getOrDefault(currNode, Collections.emptyList())) {
            dfs(child, s, parent);
        }

        // Restore ancestor state on backtracking
        last[c] = nearestAncestor;

        if (currNode != 0) {
            int newParent = (nearestAncestor != -1) ? nearestAncestor : parent[currNode];
            parent[currNode] = newParent;
            resultSize[newParent] += resultSize[currNode];
        }
    }

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        for (int i = 1; i < n; i++) {
            int u = parent[i];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(i);
        }

        resultSize = new int[n];
        Arrays.fill(resultSize, 1);

        last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s, parent);

        return resultSize;
    }
}
