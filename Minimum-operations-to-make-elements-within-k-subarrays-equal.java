/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Operations to Make Elements Within K Subarrays Equal
 * ==========================================
 * Given an integer array `nums`, integers `x` and `k`, find the minimum number of operations 
 * (incrementing or decrementing elements by 1) needed to have at least `k` non-overlapping 
 * subarrays of size exactly `x` where all elements within each subarray are equal.
 * 
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -10^6 <= nums[i] <= 10^6
 * 2 <= x <= nums.length
 * 1 <= k <= 15
 * 2 <= k * x <= nums.length
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sliding Window Median + Dynamic Programming)
 * ==========================================
 * - Approach:
 *   1. Cost Array Precomputation: For every subarray of size `x`, the minimum operations to make all 
 *      its elements equal is achieved when they are all transformed to the median of that subarray. 
 *      We use a running `MedianWindow` maintained with two `TreeMap` data structures to compute the 
 *      cost for each window of size `x` in O(n log x) total time.
 *   2. Dynamic Programming for Subarray Selection: 
 *      - We want to pick `k` non-overlapping subarrays of size `x` such that the sum of their transformation 
 *        costs is minimized.
 *      - Let `dp[j][i]` be the minimum cost to select `j` non-overlapping subarrays from the first `i` elements.
 *      - Recurrence: We can either skip the `i`-th element (`dp[j][i - 1]`) or take a subarray ending at index `i-1` 
 *        of size `x` (`dp[j - 1][i - x] + cost[i - x]`).
 *      - Space optimization: Since row `j` only depends on row `j-1`, we optimize space to 1D arrays (`prev` and `curr`).
 * 
 * - Complexity:
 *   - Time Complexity: O(n log x + k * n), which easily runs within time limits for n = 10^5 and k = 15.
 *   - Space Complexity: O(n) to store the cost and DP arrays.
 */

import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int sizeLeft = 0, sizeRight = 0;
        long sumLeft = 0, sumRight = 0;

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                sizeLeft++;
                sumLeft += val;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                sizeRight++;
                sumRight += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                erase(left, val);
                sizeLeft--;
                sumLeft -= val;
            } else {
                erase(right, val);
                sizeRight--;
                sumRight -= val;
            }
            balance();
        }

        void erase(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            int target = (sizeLeft + sizeRight + 1) / 2;

            while (sizeLeft > target) {
                int val = left.lastKey();
                erase(left, val);
                sizeLeft--;
                sumLeft -= val;

                right.put(val, right.getOrDefault(val, 0) + 1);
                sizeRight++;
                sumRight += val;
            }

            while (sizeLeft < target) {
                int val = right.firstKey();
                erase(right, val);
                sizeRight--;
                sumRight -= val;

                left.put(val, left.getOrDefault(val, 0) + 1);
                sizeLeft++;
                sumLeft += val;
            }
        }

        long getCost() {
            long median = left.lastKey();
            return median * sizeLeft - sumLeft + sumRight - median * sizeRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }
        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);
            cost[i - x + 1] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x) {
                    long take = prev[i - x] + cost[i - x];
                    curr[i] = Math.min(curr[i], take);
                }
            }
            
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
