/**
 * PROBLEM STATEMENT: 1848. Minimum Distance to the Target Element
 * --------------------------------------------------------------------------------
 * Given an integer array 'nums', a 'target' value, and a 'start' index:
 * - Find an index 'i' such that nums[i] == target.
 * - Minimize the absolute distance abs(i - start).
 * - Return the minimum value of abs(i - start).
 * * Note: The problem guarantees that the target exists in the array.
 * * Example 1:
 * Input: nums = [1,2,3,4,5], target = 5, start = 3
 * Output: 1
 * Explanation: nums[4] == 5, distance = abs(4 - 3) = 1.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single Linear Scan
 * --------------------------------------------------------------------------------
 * 1. Approach:
 * Since we need to find the minimum absolute difference, we can iterate through 
 * the array once and check every index.
 * * 2. Optimization Strategy:
 * - We can iterate from i = 0 to n-1 and track the minimum distance.
 * - An early exit is possible if the current minimum distance is 0, as it 
 * cannot be smaller.
 * - Alternatively, one could expand outwards from the 'start' index to find 
 * the target faster, but a simple linear scan is O(N) and very efficient 
 * given the constraints (N <= 1000).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array 'nums' at most once.
 * Space Complexity: O(1)
 * - We only use a single variable to track the minimum distance found.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int minDist = Integer.MAX_VALUE;

        // Iterate through the array to find all occurrences of target
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                // Calculate absolute distance from start
                int currentDist = Math.abs(i - start);
                
                // Update minimum distance found so far
                if (currentDist < minDist) {
                    minDist = currentDist;
                }
                
                // Early exit: if distance is 0, we found the best possible result
                if (minDist == 0) return 0;
            }
        }

        return minDist;
    }
}
