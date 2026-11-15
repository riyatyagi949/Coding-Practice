/*
 * ==========================================
 * PROBLEM STATEMENT: Number of Sub-arrays With Odd Sum
 * ==========================================
 * Given an array of integers `arr`, return the number of subarrays with an odd sum.
 * Since the answer can be very large, return it modulo 10^9 + 7.
 * 
 * Constraints:
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prefix Sum Parity Counting)
 * ==========================================
 * - Approach:
 *   1. The sum of a subarray from index `i` to `j` is odd if and only if the parity of the prefix sum 
 *      at index `j` is different from the parity of the prefix sum at index `i - 1`.
 *   2. Maintain the count of seen even and odd prefix sums dynamically as we iterate through the array.
 *   3. Base case: prefix sum of 0 (before any elements) is even, so initialize `even = 1` and `odd = 0`.
 *   4. For each number, update the running prefix sum. 
 *      - If the current prefix sum is even, it can form an odd subarray sum with all previous odd prefix sums. 
 *        Add `odd` to the answer and increment `even`.
 *      - If the current prefix sum is odd, it can form an odd subarray sum with all previous even prefix sums. 
 *        Add `even` to the answer and increment `odd`.
 *   5. Apply modulo `10^9 + 7` at each addition step to prevent integer overflow.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) since we traverse the array in a single pass.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int numOfSubarrays(int[] arr) {
        long MOD = 1_000_000_007;
        long even = 1; // Base case: prefix sum 0 is even
        long odd = 0;
        long ans = 0;
        int sum = 0;

        for (int num : arr) {
            sum += num;
            
            if (sum % 2 == 0) {
                ans = (ans + odd) % MOD;
                even++;
            } else {
                ans = (ans + even) % MOD;
                odd++;
            }
        }
      return (int) ans;
    }
}
