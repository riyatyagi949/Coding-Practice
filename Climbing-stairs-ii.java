/*
 * ==========================================
 * PROBLEM STATEMENT: Climbing Stairs II
 * ==========================================
 * You are climbing a staircase with `n + 1` steps, numbered from `0` to `n`.
 * You are given a 1-indexed integer array `costs` of length `n`, where `costs[i]` is the cost of step `i`.
 * From step `i`, you can jump only to step `i + 1`, `i + 2`, or `i + 3`. 
 * The cost of jumping from step `i` to step `j` is defined as: `costs[j] + (j - i)^2`.
 * You start from step 0 with cost = 0. Return the minimum total cost to reach step `n`.
 * 
 * Constraints:
 * 1 <= n == costs.length <= 10^5
 * 1 <= costs[i] <= 10^4
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming - Tabulation)
 * ==========================================
 * - Approach:
 *   1. Define `dp[i]` as the minimum cost required to reach step `n` starting from step `i`.
 *   2. Base case: `dp[n] = 0`, since we are already at the target step `n`.
 *   3. Iterate backwards from `n - 1` down to `0`. For each step `i`, we can jump to `j = i + 1`, `i + 2`, or `i + 3`, 
 *      provided `j <= n`.
 *   4. The transition for each valid jump is: `dp[j] + costs[j - 1] + (j - i) * (j - i)`.
 *   5. Take the minimum over all valid choices of `j` to fill `dp[i]`. The answer is stored at `dp[0]`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) since each state checks at most 3 choices (constant transitions).
 *   - Space Complexity: O(n) to store the DP memoization array (can be optimized further to O(1) space).
 */

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step n from step i
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;

            // Try jumping 1 step ahead
            int j = i + 1;
            if (j <= n) {
                minCost = dp[j] + costs[j - 1] + (j - i) * (j - i);
            }

            // Try jumping 2 steps ahead
            j = i + 2;                              
            if (j <= n) {
                minCost = Math.min(minCost, dp[j] + costs[j - 1] + (j - i) * (j - i));
            }

            // Try jumping 3 steps ahead
            j = i + 3;                                 
            if (j <= n) {
                minCost = Math.min(minCost, dp[j] + costs[j - 1] + (j - i) * (j - i));
            }

            dp[i] = minCost;
        }
        
        return dp[0];
    }
}
