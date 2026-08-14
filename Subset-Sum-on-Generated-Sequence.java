/*
 * ==========================================
 * PROBLEM STATEMENT: Subset Sum on Generated Sequence
 * ==========================================
 * There are n children standing in a queue, each assigned a number arr[i]. 
 * The teacher writes s on a paper and gives it to the first child.
 * 
 * Each child writes the sum of all numbers already on the paper and arr[i], then passes it to the next child.
 * Return true if x can be formed by adding some of the numbers written on the paper; else return false.
 * 
 * Constraints:
 * 1 <= arr.size() <= 10^5
 * 1 <= arr[i] <= 10^9
 * 1 <= s <= 10^9
 * 0 <= x <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Bitset Optimization / Subset Sum)
 * ==========================================
 * - Approach:
 *   1. Sequence Generation: The sequence generated on the paper grows extremely fast because each new number 
 *      is the sum of all previous numbers plus arr[i]. 
 *   2. Bitset DP: We can use a `long[]` array to act as a bitset where the i-th bit represents whether sum `i` can be formed.
 *      - We initialize the bitset to include the initial value `s`.
 *      - For each element in `arr`, we compute the next sequence number (`next = currentSum + a`). 
 *      - If `next > x`, we can safely break early since the sequence grows monotonically and subsequent numbers will exceed `x`.
 *      - We use a bitwise `add` helper function to update all reachable subset sums efficiently in-place.
 *   3. Final Check: We check if the bit corresponding to target `x` is set in our bitset.
 * 
 * - Complexity:
 *   - Time Complexity: O(N * (X / 64)), where N is the number of elements and X is the target value. Due to exponential growth, the loop terminates very quickly.
 *   - Space Complexity: O(X / 64) for the bitset array.
 */

class Solution {
    public boolean isPossible(int[] arr, int s, int x) {
        // Base case: if initial sum exceeds target, it's only possible if target is 0
        if (s > x) return x == 0;

        // Bitset to track all possible subset sums up to x
        long[] dp = new long[(x >> 6) + 1];
        dp[0] = 1L; // 0 is always formable (empty subset)
        long currentSum = s;

        // Add the initial value s to the subset sum bitset
        add(dp, s, x);

        // Generate the sequence dynamically and update subset sums
        for (int a : arr) {
            long next = currentSum + a;

            // If the generated number exceeds x, stop processing further
            if (next > x) {
                break;
            }

            // Include the new number into the subset sum possibilities
            add(dp, next, x);
            currentSum += next;
        }

        // Return true if target x can be formed (i.e., bit at position x is set)
        return ((dp[x >> 6] >>> (x & 63)) & 1L) != 0;
    }

    // Helper method to update the bitset by adding a new value to all existing subset sums
    private void add(long[] dp, long value, int x) {
        int shift = (int) value;
        int wordShift = shift >> 6;
        int bitShift = shift & 63;

        for (int i = dp.length - 1; i >= wordShift; i--) {
            long shifted = dp[i - wordShift] << bitShift;

            if (bitShift != 0 && i - wordShift - 1 >= 0) {
                shifted |= dp[i - wordShift - 1] >>> (64 - bitShift);
            }
            dp[i] |= shifted;
        }

        // Mask out any bits exceeding target x
        int extraBits = (dp.length << 6) - (x + 1);
        if (extraBits > 0) {
            dp[dp.length - 1] &= (-1L >>> extraBits);
        }
    }
}
