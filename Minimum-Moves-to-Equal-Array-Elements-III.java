/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Moves to Equal Array Elements III
 * ==========================================
 * Given an integer array `nums`, in one move you may increase the value of any single element by 1.
 * Return the minimum total number of moves required so that all elements in `nums` become equal.
 * 
 * Constraints:
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Maximum Value Target)
 * ==========================================
 * - Approach:
 *   1. To make all elements equal by only *increasing* elements, every number must be incremented 
 *      until it matches the maximum value present in the array.
 *   2. Find the maximum element (`max`) in `nums`.
 *   3. For each element in the array, the number of increments needed is `max - nums[i]`.
 *   4. Sum these differences up for all elements to get the total minimum moves.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) to find the maximum value and compute the total moves.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        int totalMoves = 0;
        for (int num : nums) {
            totalMoves += (max - num);
        }
        
        return totalMoves;
    }
}
