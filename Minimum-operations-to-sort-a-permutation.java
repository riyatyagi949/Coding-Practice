/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Operations to Sort a Permutation
 * ==========================================
 * Given an integer array `nums` of length `n`, where `nums` is a permutation of integers from `0` to `n - 1`.
 * You may perform only the following operations:
 * - Reverse the entire array.
 * - Rotate Left by One: Move the first element to the end of the array, and rest elements to left by one position.
 * Return an integer denoting the minimum number of operations required to sort the array in increasing order. 
 * If it is not possible to sort the array using only the given operations, return -1.
 * 
 * Constraints:
 * 1 <= n <= 10^5
 * 0 <= nums[i] <= n - 1
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Cyclic Shift and Position Analysis)
 * ==========================================
 * - Approach:
 *   1. A left rotation by one shifts elements cyclically, and reversing the array flips the traversal direction (from step +1 to step -1).
 *   2. The sorted permutation must start with `0` and increment sequentially. We locate the index of `0` in the original array, denoted as `zero`.
 *   3. We evaluate whether the elements form a valid increasing sequence with a step of `+1` or `-1` starting from the position of `0`.
 *   4. Based on the valid step direction, we calculate the minimum operations required using combinations of left rotations and reversals.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) to find the position of 0 and validate the sequence step across the array.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index where 0 is located
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        final int finalZero = zero;
        // Helper function to check if elements form a sorted order with a given step (+1 or -1)
        java.util.function.IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;
                if (nums[prev] > nums[curr]) {
                    return false;
                }
            }
            return true;
        };
        
        int ans = Integer.MAX_VALUE;
        
        // Check for step +1 (increasing sequentially in forward direction)
        if (check.test(1)) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }
        
        // Check for step -1 (increasing sequentially in reverse direction)
        if (check.test(-1)) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
