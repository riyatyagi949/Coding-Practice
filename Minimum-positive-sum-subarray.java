/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Positive Sum Subarray
 * ==========================================
 * Given an integer array `nums` and two integers `l` and `r`. Find the minimum sum of a subarray 
 * whose size is between `l` and `r` (inclusive) and whose sum is strictly greater than 0.
 * Return the minimum sum of such a subarray. If no such subarray exists, return -1.
 * 
 * Constraints:
 * 1 <= nums.length <= 100
 * 1 <= l <= r <= nums.length
 * -1000 <= nums[i] <= 1000
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Nested Iteration / Prefix Sum Window)
 * ==========================================
 * - Approach:
 *   1. Given the small constraints (`nums.length <= 100`), a straightforward nested loop or sliding window 
 *      checking all subarray lengths from `l` to `r` is extremely efficient and clean.
 *   2. Outer loop selects the starting index `i` of the subarray.
 *   3. Inner loop extends the ending index `j` such that the subarray length `(j - i + 1)` falls within `[l, r]`.
 *   4. Maintain the running sum for each valid subarray. If the sum is strictly positive (`> 0`), 
 *      update our minimum positive sum found so far.
 *   5. If no valid positive sum subarray is found after checking all possibilities, return `-1`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * (r - l)), which is roughly O(n^2) in the worst case, well within limits for n = 100.
 *   - Space Complexity: O(1) auxiliary space.
 */

import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Iterate over all possible starting positions
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Extend the subarray up to length r
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if length is within the valid range [l, r]
                if (length >= l && length <= r) {
                    if (currentSum > 0) {
                        minSum = Math.min(minSum, currentSum);
                    }
                }
                
                // If length exceeds r, we can stop extending this particular start index
                if (length > r) {
                    break;
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
