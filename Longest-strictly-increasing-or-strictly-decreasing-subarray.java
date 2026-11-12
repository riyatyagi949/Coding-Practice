/*
 * ==========================================
 * PROBLEM STATEMENT: Longest Strictly Increasing or Strictly Decreasing Subarray
 * ==========================================
 * You are given an array of integers `nums`. Return the length of the longest subarray of 
 * `nums` which is either strictly increasing or strictly decreasing.
 * 
 * Constraints:
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Linear Scan)
 * ==========================================
 * - Approach:
 *   1. Track both the current strictly increasing length (`inc`) and strictly decreasing length (`dec`), 
 *      along with a global maximum (`maxLength`).
 *   2. Iterate through the array starting from index 1:
 *      - If `nums[i] > nums[i - 1]`, increment `inc` and reset `dec` to 1.
 *      - If `nums[i] < nums[i - 1]`, increment `dec` and reset `inc` to 1.
 *      - If `nums[i] == nums[i - 1]`, reset both `inc` and `dec` to 1.
 *   3. Update `maxLength` at each step with the maximum of `inc`, `dec`, and the previous `maxLength`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) since we make a single pass through the array.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLength = 1;
        int inc = 1;
        int dec = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }

            maxLength = Math.max(maxLength, Math.max(inc, dec));
        }

        return maxLength;
    }
}
