/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Moves to Equal Array Elements
 * ==========================================
 * Given an integer array `nums` of size `n`, return the minimum number of moves required to make 
 * all array elements equal. In one move, you can increment `n - 1` elements of the array by 1.
 * 
 * Constraints:
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Mathematical Deduction)
 * ==========================================
 * - Approach:
 *   1. Incrementing `n - 1` elements by 1 is mathematically equivalent to decrementing 
 *      the remaining 1 element by 1 relative to the others.
 *   2. Therefore, instead of tracking how many increments it takes to reach a maximum, 
 *      we can calculate how many total decrements are needed to bring every element down to 
 *      the minimum element in the array.
 *   3. For each element, the number of decrements required is `num - min`.
 *   4. Summing this up across all elements gives: `sum(nums) - (min * n)`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) for a single pass to find the minimum and compute the sum.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        long sum = 0; // Use long to prevent any potential integer overflow during summation
        
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        
        return (int) (sum - (long) min * nums.length);
    }
}
