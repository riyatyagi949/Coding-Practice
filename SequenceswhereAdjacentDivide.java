/**
 * PROBLEM STATEMENT: Sequences where Adjacent Divide
 * --------------------------------------------------------------------------------
 * Given two positive integers n and m. Find the number of arrays of size n that can 
 * be formed such that:
 * - Each element is in the range [1, m].
 * - All adjacent elements are such that one of them divides the other i.e., 
 *   element Ai divides Ai + 1 or Ai + 1 divides Ai.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DYNAMIC PROGRAMMING
 * --------------------------------------------------------------------------------
 * We use a DP table where `dp[len][last]` stores the number of valid sequences of 
 * length `len` ending with the value `last`.
 * 
 * 1. Base Case: For sequences of length 1, each element from 1 to m has exactly 1 way.
 * 2. Transition: For each length from 2 to n, and for each possible ending number 
 *    `last`, we iterate through all possible previous numbers `prev` from 1 to m.
 * 3. Condition: If `prev % last == 0` or `last % prev == 0`, we add `dp[len - 1][prev]` 
 *    to `dp[len][last]`.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: 
 * - O(n * m^2) where n is the length of the array and m is the maximum range of elements.
 * 
 * Space Complexity: O(n * m)
 * - The space is used for storing the 2D DP table of size (n + 1) x (m + 1).
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int count(int n, int m) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= m; i++) {
            dp[1][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int last = 1; last <= m; last++) {
                for (int prev = 1; prev <= m; prev++)
                {
                    if (prev % last == 0 || last % prev == 0) {
                        dp[len][last] += dp[len - 1][prev];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            ans += dp[n][i];
        }

        return ans;
    }
}
