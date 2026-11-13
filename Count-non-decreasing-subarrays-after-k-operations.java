/*
 * ==========================================
 * PROBLEM STATEMENT: Count Non-Decreasing Subarrays After K Operations
 * ==========================================
 * Given an integer array `nums` of `n` integers and an integer `k`. For each subarray, 
 * you can apply up to `k` operations (incrementing any element by 1). 
 * Return the number of subarrays that can be made non-decreasing after at most `k` operations.
 * 
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Monotonic Deque + Sliding Window)
 * ==========================================
 * - Approach:
 *   1. We iterate backward from right to left (`l` from `n - 1` down to `0`), dynamically maintaining 
 *      the minimum operation cost to make the suffix starting at `l` up to `r` non-decreasing.
 *   2. Use a deque (`dq`) to store grouped blocks of values and their counts representing the modified 
 *      non-decreasing sequence profile.
 *   3. When incorporating `nums[l]`, we merge blocks from the front of the deque that are less than or equal 
 *      to `nums[l]`, updating the total `cost` of operations required.
 *   4. If the accumulated `cost` exceeds `k`, we shrink our window from the right (`r`) by removing elements 
 *      from the back of the deque until the cost fits within `k`.
 *   5. For each valid left index `l`, all right endpoints from `l` up to `r` form valid non-decreasing 
 *      subarrays, contributing `(r - l + 1)` to our total answer.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) since each element is inserted and removed from the deque at most once.
 *   - Space Complexity: O(n) auxiliary space for the deque.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, long k) {
        int n = nums.length;
        // Each block in the deque: {value, count}; front = leftmost, values increase toward the back
        Deque<long[]> dq = new ArrayDeque<>();
        long cost = 0, ans = 0;
        int r = n - 1;

        // Iterate backwards to maintain suffix windows
        for (int l = n - 1; l >= 0; l--) {
            long v = nums[l];
            long cnt = 1;

            // Merge front blocks that nums[l] now dominates
            while (!dq.isEmpty() && dq.peekFirst()[0] <= v) {
                long[] b = dq.pollFirst();
                cost += (v - b[0]) * b[1];
                cnt += b[1];
            }
            dq.offerFirst(new long[]{v, cnt});

            // Shrink the window from the right until the total operation cost fits within k
            while (cost > k) {
                long[] last = dq.peekLast();
                cost -= last[0] - nums[r];
                r--;
                if (--last[1] == 0) {
                    dq.pollLast();
                }
            }

            // All subarrays starting at l and ending anywhere from l to r are valid
            ans += r - l + 1;
        }

        return ans;
    }
}
