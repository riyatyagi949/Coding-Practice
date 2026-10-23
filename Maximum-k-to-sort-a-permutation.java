/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum K to Sort a Permutation
 * ==========================================
 * Given an integer array `nums` of length `n` which is a permutation of numbers in the range `[0..n-1]`.
 * You may swap elements at indices `i` and `j` only if `nums[i] AND nums[j] == k`.
 * Return the maximum value of `k` such that the array can be sorted in non-decreasing order using any number of such swaps. 
 * If nums is already sorted, return 0.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Bitwise AND of Misplaced Elements)
 * ==========================================
 * - Approach:
 *   1. For the array to be sortable using valid swaps where `nums[i] & nums[j] == k`, every element 
 *      that is not in its correct position (i.e., `nums[i] != i`) must be able to participate in the required swaps.
 *   2. Therefore, `k` must be a common bitmask present in the binary representation of all misplaced elements.
 *   3. The maximum possible `k` is simply the bitwise AND of all elements `nums[i]` where `nums[i] != i`.
 *   4. If the array is already sorted (no misplaced elements), `ans` remains `-1`, and we return `0`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) to iterate through the array.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (i != nums[i]) {
                if (ans == -1) {
                    ans = nums[i];
                }
                else {
                    ans &= nums[i];
                }
            }
        }
        return Math.max(ans, 0);
    }
}
