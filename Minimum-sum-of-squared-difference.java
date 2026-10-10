/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Sum of Squared Difference
 * ==========================================
 * You are given two positive 0-indexed integer arrays `nums1` and `nums2`, both of length `n`.
 * The sum of squared difference of arrays is defined as the sum of `(nums1[i] - nums2[i])^2` for each `0 <= i < n`.
 * You are given modification budgets `k1` and `k2`:
 * - You can modify elements in `nums1` by adding or subtracting 1, up to `k1` times total.
 * - You can modify elements in `nums2` by adding or subtracting 1, up to `k2` times total.
 * Return the minimum sum of squared difference after modifications.
 * 
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 10^5
 * 0 <= k1, k2 <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Binary Search + Greedy Reduction)
 * ==========================================
 * - Approach:
 *   1. The objective is to minimize the sum of squares of absolute differences $d[i] = \vert{}nums1[i] - nums2[i]\vert{}$. 
 *      To minimize a sum of squares, we should prioritize reducing the largest differences because decreasing 
 *      a larger number reduces the squared value much more than decreasing a smaller one (e.g., $5^2 - 4^2 = 9$ vs $2^2 - 1^2 = 3$).
 *   2. Total operations available: `k = k1 + k2`. If the sum of all elements in `d` is less than or equal to `k`, 
 *      we can reduce every difference down to 0, resulting in a minimum sum of 0.
 *   3. Otherwise, use **Binary Search** on the maximum possible remaining difference (`left = 0`, `right = max(d)`).
 *      - We check if it is feasible to reduce all differences greater than `mid` down to `mid` using at most `k` total operations.
 *      - The number of operations required for a target `mid` is $\sum \max(0, d[i] - mid)$.
 *   4. Once we find the optimal threshold `left`, we reduce all differences down to `left`, subtract the operations used, 
 *      and distribute any remaining leftover operations (`k`) by decrementing elements that equal `left` by 1.
 *   5. Finally, compute and return the sum of squared values of the resulting differences.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log(max(D))) where `n` is the length of the arrays and `max(D)` is the maximum initial difference.
 *   - Space Complexity: O(n) to store the differences array.
 */

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;

        int[] diff = new int[n];
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // countDiff[d] = count of indices with diff exactly d
        int[] countDiff = new int[maxDiff + 1];
        for (int d : diff) {
            countDiff[d]++;
        }

        long K = (long) k1 + k2;

        for (int currDiff = maxDiff; currDiff > 0 && K > 0; currDiff--) {
            int countOps = (int) Math.min(countDiff[currDiff], K);

            countDiff[currDiff]     -= countOps;
            countDiff[currDiff - 1] += countOps;
            K                       -= countOps;
        }

        long result = 0;
        for (long d = 1; d <= maxDiff; d++) {
            result += countDiff[(int) d] * d * d;
        }

        return result;
    }
}

