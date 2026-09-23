/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Operations to Reduce X to Zero
 * ==========================================
 * You are given an integer array `nums` and an integer `x`. In one operation, you can either 
 * remove the leftmost or the rightmost element from the array `nums` and subtract its value from `x`. 
 * Return the minimum number of operations to reduce `x` to exactly 0 if it is possible, otherwise return -1.
 * 
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sliding Window / Longest Subarray Sum)
 * ==========================================
 * - Approach:
 *   1. Equivalent Problem Transformation: Removing elements from the ends to sum up to `x` is equivalent 
 *      to finding the **longest contiguous subarray** whose sum equals `totalSum - x`.
 *   2. Sliding Window / Two Pointers: 
 *      - Calculate the total sum of all elements in `nums`. If the total sum is less than `x`, it's impossible, return `-1`.
 *      - Target subarray sum `target = totalSum - x`. If `target == 0`, we need to remove all elements, so return `nums.length`.
 *      - Use a sliding window to find the maximum length of a subarray with sum equal to `target`.
 *   3. Result Calculation: Minimum operations = `nums.length - maxSubarrayLength`.
 * 
 * - Complexity:
 *   - Time Complexity: O(N), where `N` is the length of `nums`, since each element is visited at most twice by the sliding window pointers.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;
        
        // If target is less than 0, x is greater than the total sum of the array
        if (target < 0) {
            return -1;
        }
        
        // If target is 0, we need to remove all elements
        if (target == 0) {
            return nums.length;
        }

        int n = nums.length;
        int currentSum = 0;
        int maxLen = -1;
        int left = 0;

        // Sliding window to find the longest subarray with sum equal to `target`
        for (int right = 0; right < n; right++) {
            currentSum += nums[right];

            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // If no such subarray exists, return -1; otherwise return total elements minus max subarray length
        return maxLen == -1 ? -1 : n - maxLen;
    }
}
