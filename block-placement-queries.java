/**
 * PROBLEM STATEMENT: 3161. Block Placement Queries
 * --------------------------------------------------------------------------------
 * Given an infinite number line starting at 0:
 * - Query Type 1: Build an obstacle at distance 'x'.
 * - Query Type 2: Check if a block of size 'sz' fits in the range [0, x].
 * * A block can be placed if there exists a gap between obstacles (or the origin) 
 * of size at least 'sz' within the interval [0, x].
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Segment Tree + TreeSet
 * --------------------------------------------------------------------------------
 * 1. TreeSet: Keeps track of sorted obstacle positions, allowing us to find the 
 * nearest existing obstacles (floor/ceiling) to an added obstacle in O(log N).
 * 2. Segment Tree: Stores the sizes of gaps between consecutive obstacles. 
 * Each leaf node at index 'x' stores the gap size ending at 'x'.
 * 3. Update (Type 1): When adding an obstacle at 'x', find its predecessor 'prev'.
 * The gap between 'prev' and 'next' is split into two: [prev, x] and [x, next].
 * We update the Segment Tree at position 'x' and 'next'.
 * 4. Query (Type 2): Find the largest gap in the interval [0, last_obstacle]. 
 * Also, consider the gap between the last obstacle and 'x'. If max(gap, x - last) >= sz,
 * the block can be placed.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(Q log M), where Q is the number of queries and M is the 
 * maximum coordinate value (50,000).
 * Space Complexity: O(M) for the segment tree nodes.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {

    class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) / 2;

            if (idx <= mid)
                update(node * 2, l, mid, idx, val);
            else
                update(node * 2 + 1, mid + 1, r, idx, val);

            tree[node] = Math.max(tree[node * 2],
                                  tree[node * 2 + 1]);
        }

        int query(int L, int R) {
            return query(1, 0, n - 1, L, R);
        }

        int query(int node, int l, int r, int L, int R) {
            if (R < l || r < L)
                return 0;

            if (L <= l && r <= R)
                return tree[node];

            int mid = (l + r) / 2;

            return Math.max(
                query(node * 2, l, mid, L, R),
                query(node * 2 + 1, mid + 1, r, L, R)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int MAX = 50001;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        SegmentTree seg = new SegmentTree(MAX + 1);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int x = q[1];

                Integer prev = obstacles.floor(x);
                Integer next = obstacles.ceiling(x);

                if (prev == null) prev = 0;

                seg.update(x, x - prev);

                if (next != null)
                    seg.update(next, next - x);

                obstacles.add(x);

            } 
            else {

                int x = q[1];
                int sz = q[2];

                Integer last = obstacles.floor(x);

                int bestGap = 0;

                if (last != null)
                    bestGap = seg.query(0, last);

                int tailGap = x - last;

                ans.add(Math.max(bestGap, tailGap) >= sz);
            }
        }

        return ans;
    }
}

