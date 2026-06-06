/**
 * PROBLEM STATEMENT: 2574. Left and Right Sum Differences
 * --------------------------------------------------------------------------------
 * Given an integer array 'nums', calculate an 'answer' array where:
 * answer[i] = |leftSum[i] - rightSum[i]|
 * - leftSum[i]: sum of elements to the left of index i (0 if none).
 * - rightSum[i]: sum of elements to the right of index i (0 if none).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Prefix/Suffix Sum optimization
 * --------------------------------------------------------------------------------
 * 1. Pre-calculate the total sum of the array.
 * 2. As we iterate through the array:
 * - The current 'total' variable (initially total sum) represents the 
 * sum of elements strictly to the right of index i after subtracting nums[i].
 * - Maintain a 'leftSum' variable that tracks the sum of elements 
 * to the left.
 * - The answer at index i is simply |leftSum - rightSum|.
 * - Update leftSum by adding the current nums[i] for the next iteration.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the array. We perform one pass 
 * to calculate total sum and one pass to compute differences.
 * Space Complexity: O(1) (excluding the output array), as we only use a few 
 * variables for sum tracking.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        
        // Step 1: Calculate total sum of the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        int[] ans = new int[n];

        // Step 2: Iterate through the array to calculate differences
        for (int i = 0; i < n; i++) {
            // Subtract current element from totalSum to get rightSum[i]
            totalSum -= nums[i];
            
            // Calculate absolute difference between leftSum and rightSum
            ans[i] = Math.abs(leftSum - totalSum);
            
            // Add current element to leftSum for the next index
            leftSum += nums[i];
        }
        
        return ans;
    }
}
