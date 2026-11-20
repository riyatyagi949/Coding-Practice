/*
 * ==========================================
 * PROBLEM STATEMENT: Maximize Consecutive Elements in an Array After Modification
 * ==========================================
 * Given a 0-indexed array `nums` of positive integers, you can increase each element
 * by at most 1 (`+0` or `+1`). After modification, select elements that form a consecutive
 * sequence `[x, x + 1, x + 2, ...]` when sorted in increasing order.
 * Return the maximum number of consecutive elements that can be selected.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sorting + DP + Binary Search)
 * ==========================================
 * - Approach:
 *   1. Sort `nums` in ascending order so candidate elements appear in non-decreasing order.
 *   2. Define `dp[i][inc]` as the longest consecutive sequence starting from index `i`
 *      where `nums[i]` is incremented by `inc` (`0` or `1`), producing `currVal = nums[i] + inc`.
 *   3. To extend the consecutive chain from `currVal` to `currVal + 1` using an element at
 *      index `j > i`:
 *      - Either `nums[j] == currVal + 1` and we keep it unincremented (`dp[j][0]`), OR
 *      - `nums[j] == currVal` and we increment it by 1 (`dp[j][1]`).
 *   4. Since `nums` is sorted, use binary search (`findMethod`) in `[i + 1, n)` to locate
 *      the first occurrence of `currVal + 1` and `currVal` in `O(log n)` time.
 *   5. Track the maximum chain length across all states and return `result`.
 *
 * - Complexity:
 *   - Time Complexity: O(n log n) due to sorting and two binary searches per `(i, inc)` state.
 *   - Space Complexity: O(n) for the `dp` table (excluding sorting stack space).
 */

import java.util.Arrays;

class Solution {
    int n;
    int[][] dp;

    // Returns the smallest index >= start where nums[idx] == target, or -1 if not found
    private int findMethod(int[] nums, int start, int target) {
        int lo = start, hi = n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        if (lo < n && nums[lo] == target) {
            return lo;
        }
        return -1;
    }

    public int maxSelectedElements(int[] nums) {
        n = nums.length;
        Arrays.sort(nums);

        // dp[i][inc]: max consecutive elements starting at index i with increment inc (0 or 1)
        dp = new int[n + 1][2];
        int result = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int inc = 0; inc <= 1; inc++) {
                int currVal = nums[i] + inc;
                int len = 1;

                // Next value needed is currVal + 1; check if nums[j] == currVal + 1 (inc = 0)
                int j = findMethod(nums, i + 1, currVal + 1);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][0]);
                }

                // Or check if nums[j] == currVal and increment it by 1 to reach currVal + 1 (inc = 1)
                j = findMethod(nums, i + 1, currVal);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][1]);
                }

                dp[i][inc] = len;
                result = Math.max(result, len);
            }
        }

        return result;
    }
}
