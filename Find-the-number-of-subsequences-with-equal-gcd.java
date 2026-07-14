/**
 * PROBLEM STATEMENT: 3336. Find the Number of Subsequences With Equal GCD
 * --------------------------------------------------------------------------------
 * Given an integer array 'nums', find the number of pairs of disjoint non-empty 
 * subsequences (seq1, seq2) such that the GCD of the elements in seq1 is equal 
 * to the GCD of the elements in seq2.
 *
 * Conditions:
 * 1. seq1 and seq2 must be disjoint (no common indices).
 * 2. gcd(seq1) == gcd(seq2).
 * 3. Both subsequences must be non-empty.
 * 4. Return the result modulo 10^9 + 7.
 *
 * OPTIMAL SOLUTION: Dynamic Programming
 * --------------------------------------------------------------------------------
 * 1. We use DP with the state dp[i][g1][g2], representing the number of ways to 
 * form subsequences with current GCDs 'g1' and 'g2' using elements from index 'i' onwards.
 * 2. At each index 'i', we have three choices:
 * a) Skip the current element: transition to state (g1, g2) at i+1.
 * b) Add current element to seq1: transition to state (gcd(g1, nums[i]), g2) at i+1.
 * c) Add current element to seq2: transition to state (g1, gcd(g2, nums[i])) at i+1.
 * 3. Base case: If both subsequences are non-empty and their final GCDs match, count it.
 * 4. Space optimization: Since each state at index 'i' depends only on 'i+1', 
 * we use a 2D array [max_val + 1][max_val + 1] to save space.
 * * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * M^2), where N is the array length (up to 200) 
 * and M is the maximum value in nums (up to 200). 
 * O(200 * 200^2) = 8,000,000 operations, which fits well within limits.
 * Space Complexity: O(M^2) for the DP table.
 */

class Solution {
    int MOD = 1_000_000_007;

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;

        int maxEl = -1;
        for (int x : nums)
            maxEl = Math.max(maxEl, x);

        int[][] prev = new int[maxEl + 1][maxEl + 1];

        for (int first = maxEl; first >= 0; first--) {
            for (int second = maxEl; second >= 0; second--) {
                boolean bothNonEmpty = (first != 0 && second != 0);
                boolean gcdsMatch    = (first == second);
                prev[first][second] = (bothNonEmpty && gcdsMatch) ? 1 : 0;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int[][] curr = new int[maxEl + 1][maxEl + 1];
            for (int first = maxEl; first >= 0; first--) {
                for (int second = maxEl; second >= 0; second--) {

                    int skip  = prev[first][second];

                    int take1 = prev[gcd(first, nums[i])][second];
                    int take2 = prev[first][gcd(second, nums[i])];

                    curr[first][second] = (int)((0L + skip + take1 + take2) % MOD);
                }
            }
            prev = curr;
        }

        return prev[0][0];
    }
}
