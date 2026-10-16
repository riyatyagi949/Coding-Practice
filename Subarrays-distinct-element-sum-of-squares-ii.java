/*
 * ==========================================
 * PROBLEM STATEMENT: Subarrays Distinct Element Sum of Squares II
 * ==========================================
 * Given a 0-indexed integer array `nums`, return the sum of the squares of distinct counts 
 * of all subarrays of `nums`. Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Segment Tree with Lazy Propagation)
 * ==========================================
 * - Approach:
 *   1. Algebraic Insight: 
 *      Let distinct count of subarray from index `l` to `r` be $c(l, r)$. 
 *      When we expand the right endpoint from `r-1` to `r`, the distinct count for all starting points 
 *      $l$ in the range `[last_occurrence[nums[r]] + 1, r]` increases by 1. 
 *      If a value increases from $c$ to $c+1$, its square changes from $c^2$ to $(c+1)^2 = c^2 + 2c + 1$.
 *   2. Segment Tree Design:
 *      We need a Segment Tree supporting range updates (`add 1` to elements in range `[l, r]`) and range sum queries 
 *      of squares of distinct counts over all starting points `l` from `0` to `r`.
 *      - Each node maintains:
 *        - `sum`: sum of $c(l)^2$ for the range.
 *        - `val`: sum of $c(l)$ for the range.
 *        - `lazy`: pending lazy addition for range updates.
 *   3. Algorithm:
 *      - Maintain `lastSeen` map for each number's previous index.
 *      - Iterate `r` from `0` to `n - 1`:
 *        - The previous occurrence of `nums[r]` gives the left boundary `l = lastSeen.getOrDefault(nums[r], -1) + 1`.
 *        - Update the segment tree for range `[l, r]` by adding `1` (which implicitly handles $c^2 \to (c+1)^2$ via linear updates since $\sum (c+1)^2 = \sum c^2 + 2\sum c + \text{length}$).
 *        - Query the total sum of squares from `0` to `r` and add it to the global total.
 *        - Update `lastSeen.put(nums[r], r)`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log n) due to segment tree operations across `n` elements.
 *   - Space Complexity: O(n) to store the segment tree and hash map.
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    class SegmentTree {
        int n;
        long[] sumSq;
        long[] sum;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            sumSq = new long[4 * n];
            sum = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void apply(int node, long delta, int len) {
            // (c + delta)^2 = c^2 + 2*delta*c + delta^2 * len
            sumSq[node] = (sumSq[node] + 2 * delta * sum[node] + delta * delta * len) % MOD;
            sum[node] = (sum[node] + delta * len) % MOD;
            lazy[node] = (lazy[node] + delta) % MOD;
        }

        private void pushDown(int node, int leftLen, int rightLen) {
            if (lazy[node] != 0) {
                apply(2 * node, lazy[node], leftLen);
                apply(2 * node + 1, lazy[node], rightLen);
                lazy[node] = 0;
            }
        }

        public void update(int node, int l, int r, int ql, int qr, long delta) {
            if (ql > r || qr < l) {
                return;
            }
            if (ql <= l && r <= qr) {
                apply(node, delta, r - l + 1);
                return;
            }
            int mid = l + (r - l) / 2;
            pushDown(node, mid - l + 1, r - mid);
            update(2 * node, l, mid, ql, qr, delta);
            update(2 * node + 1, mid + 1, r, ql, qr, delta);
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        }

        public long query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) {
                return 0;
            }
            if (ql <= l && r <= qr) {
                return sumSq[node];
            }
            int mid = l + (r - l) / 2;
            pushDown(node, mid - l + 1, r - mid);
            long leftResult = query(2 * node, l, mid, ql, qr);
            long rightResult = query(2 * node + 1, mid + 1, r, ql, qr);
            return (leftResult + rightResult) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;
        long currentTotalSq = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.getOrDefault(nums[r], -1) + 1;
            lastSeen.put(nums[r], r);

            // Update the segment tree for range [l, r] by adding 1
            tree.update(1, 0, n - 1, l, r, 1);

            // Query the total sum of squares from index 0 to r
            currentTotalSq = tree.query(1, 0, n - 1, 0, r);
            totalSum = (totalSum + currentTotalSq) % MOD;
        }

        return (int) totalSum;
    }
}
