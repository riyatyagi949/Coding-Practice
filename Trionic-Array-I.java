/*
 * PROBLEM STATEMENT: Trionic Array I
 * ---------------------------------------------------------
 * An array 'nums' of length 'n' is "trionic" if there exist indices 0 < p < q < n - 1 such that:
 * 1. nums[0...p] is strictly increasing (The first ascent).
 * 2. nums[p...q] is strictly decreasing (The descent).
 * 3. nums[q...n - 1] is strictly increasing (The final ascent).
 * * Return true if the array satisfies these three segments, otherwise return false.
 * * Example: nums = [1, 3, 5, 4, 2, 6]
 * - [1, 3, 5] increases (p=2)
 * - [5, 4, 2] decreases (q=4)
 * - [2, 6] increases (end)
 * Output: true
 */
/*
 * COMPLEXITY ANALYSIS:
 * ---------------------------------------------------------
 * TIME COMPLEXITY: O(n)
 * - We traverse the array exactly once from left to right using a single pointer 'i'.
 * - Each element is visited at most once.
 * * SPACE COMPLEXITY: O(1)
 * - We only use a few integer variables (n, i, startOfDescent) regardless of input size.
 * - No extra data structures like lists or maps are used.
 */
// Optimal Solution in Java - 
class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;

        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i == 0 || i == n - 1)
         return false;

        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }
        if (i == n - 1) 
        return false;

        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        return i == n - 1;
    }
}
