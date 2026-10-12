/*
 * ==========================================
 * PROBLEM STATEMENT: Apply Operations on Array to Maximize Sum of Squares
 * ==========================================
 * You are given a 0-indexed integer array `nums` and a positive integer `k`.
 * You can perform the operation: choose distinct indices `i` and `j`, and update:
 * - `nums[i] = nums[i] AND nums[j]`
 * - `nums[j] = nums[i] OR nums[j]`
 * Choose `k` elements from the final array and return the maximum sum of their squares modulo 10^9 + 7.
 * 
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Bit Manipulation & Greedy)
 * ==========================================
 * - Approach:
 *   1. The bitwise `AND` and `OR` operations between any two numbers preserve the total count of set bits 
 *      at each bit position across the entire array; they just redistribute them.
 *   2. To maximize the sum of squares ($a^2 + b^2$), squaring heavily favors larger numbers. Therefore, 
 *      we should greedily concentrate as many set bits as possible into the top `k` largest numbers we construct.
 *   3. Count the frequency of each bit position (from 0 to 31) across all numbers in `nums`.
 *   4. Construct `k` numbers from left to right: for each number, iterate through all bit positions, 
 *      and if a bit count is greater than 0, include that bit in the current number and decrement its count.
 *   5. Square each constructed number, add it to the total sum modulo $10^9 + 7$.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log M + k log M), where n is the array length and M is the maximum value in `nums` (up to 31 bit positions).
 *   - Space Complexity: O(log M) to store the bit count array of size 31.
 */

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[32];
        
        // Step 1: Count the set bits at each position across all numbers
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSum = 0;
        long MOD = 1_000_000_007;

        // Step 2: Greedily construct k numbers to maximize the sum of squares
        for (int step = 0; step < k; step++) {
            long currNum = 0;
            for (int i = 0; i < 32; i++) {
                if (bitCounts[i] > 0) {
                    currNum |= (1L << i);
                    bitCounts[i]--;
                }
            }
            totalSum = (totalSum + (currNum * currNum) % MOD) % MOD;
        }

        return (int) totalSum;
    }
}
