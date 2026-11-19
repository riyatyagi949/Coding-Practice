/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Score by Changing Two Elements
 * ==========================================
 * Given an integer array `nums` of length >= 3:
 * - Low score: minimum absolute difference between any two elements.
 * - High score: maximum absolute difference between any two elements.
 * - Score: low score + high score.
 * Return the minimum possible score after changing at most two elements of `nums`.
 *
 * Constraints:
 * 3 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sorting + Extreme Trimming)
 * ==========================================
 * - Approach:
 *   1. Since `nums.length >= 3`, we can always set two elements equal to a third element,
 *      making the low score `0` (minimum possible absolute difference is always >= 0).
 *   2. With low score fixed at `0`, minimizing the total score reduces to minimizing the
 *      high score (`max - min`) after changing two elements.
 *   3. Sort `nums` in ascending order (`nums[0] <= nums[1] <= ... <= nums[n - 1]`).
 *      Changing two elements effectively trims two extremes from the sorted endpoints:
 *      - Change the two largest elements (`nums[n - 1]`, `nums[n - 2]`): high score = `nums[n - 3] - nums[0]`
 *      - Change the two smallest elements (`nums[0]`, `nums[1]`): high score = `nums[n - 1] - nums[2]`
 *      - Change one smallest and one largest (`nums[0]`, `nums[n - 1]`): high score = `nums[n - 2] - nums[1]`
 *   4. Return the minimum of these three candidates.
 *
 * - Complexity:
 *   - Time Complexity: O(n log n) due to sorting the array.
 *   - Space Complexity: O(log n) to O(n) for Java's dual-pivot Quicksort / TimSort stack.
 */

import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // Low score can always be made 0 since n >= 3; minimize high score (max - min)
        int dropTwoLargest = nums[n - 3] - nums[0];
        int dropTwoSmallest = nums[n - 1] - nums[2];
        int dropBothEnds = nums[n - 2] - nums[1];

        return Math.min(dropTwoLargest, Math.min(dropTwoSmallest, dropBothEnds));
    }
}
