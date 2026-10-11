/*
 * ==========================================
 * PROBLEM STATEMENT: Sum of Squares of Special Elements
 * ==========================================
 * You are given a 1-indexed integer array `nums` of length `n`.
 * An element `nums[i]` (using 1-based indexing) is called special if `i` divides `n`, i.e., `n % i == 0`.
 * Return the sum of the squares of all special elements of `nums`.
 * 
 * Constraints:
 * 1 <= nums.length == n <= 50
 * 1 <= nums[i] <= 50
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Direct Iteration)
 * ==========================================
 * - Approach:
 *   1. Iterate through the array using 0-based indexing from `i = 0` to `n - 1`.
 *   2. The corresponding 1-based index is `i + 1`.
 *   3. Check if the 1-based index divides `n` evenly (`n % (i + 1) == 0`).
 *   4. If it does, square the element `nums[i]` and add it to our running sum.
 *   5. Return the total sum.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the array, since we perform a single linear scan.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // Check if 1-based index (i + 1) divides n
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }
}
