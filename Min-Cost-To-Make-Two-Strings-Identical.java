/*
 * ==========================================
 * PROBLEM STATEMENT: Min Cost To Make Two Strings Identical
 * ==========================================
 * Given two strings `s1` and `s2`, and two integers `costS1` and `costS2` (representing the deletion cost 
 * per character for `s1` and `s2` respectively), find the minimum total cost required to make the two strings identical. 
 * You can delete characters from either string while preserving the order of the remaining characters.
 * 
 * Constraints:
 * 1 <= s1.size(), s2.size() <= 1000
 * 1 <= costS1, costS2 <= 10^5
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Longest Common Subsequence - LCS)
 * ==========================================
 * - Approach:
 *   1. To minimize the deletion cost, we must maximize the length of the common characters that are retained, 
 *      which corresponds to finding the **Longest Common Subsequence (LCS)** of `s1` and `s2`.
 *   2. Use dynamic programming to compute the length of the LCS between `s1` and `s2`.
 *   3. Once the LCS length is known:
 *      - Number of characters to delete from `s1` = `s1.length() - lcs`
 *      - Number of characters to delete from `s2` = `s2.length() - lcs`
 *   4. Multiply the number of deletions by their respective costs (`costS1` and `costS2`) and sum them up.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * m), where `n` and `m` are the lengths of `s1` and `s2`.
 *   - Space Complexity: O(n * m) to store the DP table (can be optimized to O(min(n, m)), but O(n * m) easily fits within limits).
 */

class Solution {
    public int findMinCost(String s1, String s2, int costS1, int costS2) {
        int n = s1.length();
        int m = s2.length();

        // Step 1: Compute the Longest Common Subsequence (LCS) table using Dynamic Programming
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[n][m];

        // Step 2: Calculate the number of deletions needed for each string
        int deleteFromS1 = n - lcs;
        int deleteFromS2 = m - lcs;

        // Step 3: Compute and return the total minimum deletion cost
        return deleteFromS1 * costS1 + deleteFromS2 * costS2;
    }
}
