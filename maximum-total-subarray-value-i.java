/**
 * PROBLEM STATEMENT: 3689. Maximum Total Subarray Value I
 * --------------------------------------------------------------------------------
 * Given an array 'nums' and an integer 'k', choose exactly 'k' non-empty subarrays.
 * Value of a subarray = max(subarray) - min(subarray).
 * Return the maximum total value (sum of values of k subarrays).
 * * Subarrays can overlap, and the same subarray can be chosen multiple times.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Greedy DP/Observation
 * --------------------------------------------------------------------------------
 * 1. Observation: Since we can pick the same subarray k times, if we identify the 
 * subarray (l, r) that provides the absolute maximum value (max - min), we can 
 * simply select this subarray k times to achieve k * (max - min).
 * 2. Strategy:
 * - The maximum possible value for any subarray is (Global Max - Global Min).
 * - Since we can choose the same subarray multiple times, the answer is 
 * simply k * (Global Max - Global Min).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We perform a single pass over the array to find the global maximum and minimum.
 * Space Complexity: O(1)
 * - We only use two variables to track the min and max values.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        long minVal = nums[0];
        long maxVal = nums[0];
        
        // Find the absolute maximum and minimum in the array in one pass
        for (int num : nums) {
            if (num < minVal) minVal = num;
            if (num > maxVal) maxVal = num;
        }
        
        // The maximum possible value of a single subarray is (maxVal - minVal).
        // Since we can choose this subarray k times, the result is k * (maxVal - minVal).
        return (long) k * (maxVal - minVal);
    }
}
