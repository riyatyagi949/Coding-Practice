/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Changes to Make K Semi-palindromes
 * ==========================================
 * Given a string `s` and an integer `k`, partition `s` into `k` substrings such that 
 * the total letter changes needed to make each substring a semi-palindrome are minimized.
 * Return the minimum number of letter changes required.
 * 
 * Constraints:
 * 2 <= s.length <= 200
 * 1 <= k <= s.length / 2
 * s contains only lowercase English letters.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming + Precomputation)
 * ==========================================
 * - Approach:
 *   1. A semi-palindrome requires dividing a substring into `d` groups (where `d` is a proper divisor 
 *      of the substring length) such that each group forms a palindrome.
 *   2. Precomputation (`costMemo[i][j]`): Calculate the minimum character changes to make any substring 
 *      from index `i` to `j` a semi-palindrome across all valid divisors `d`.
 *   3. Dynamic Programming (`memo[start][pieces]`): Use a 2D DP table to find the optimal partitioning 
 *      of the string into `k` pieces, minimizing the cumulative cost of all partitions.
 * 
 * - Complexity:
 *   - Time Complexity: O(n^3 * sqrt(n)), where `n` is the length of the string, due to divisor checks and substring transitions.
 *   - Space Complexity: O(n^2 + n * k) for memoization tables and cost precomputations.
 */

import java.util.Arrays;

class Solution {
    int n;
    String str;
    int[][] costMemo;
    int[][] memo;

    private int getCost(int i, int j) {
        int len = j - i + 1;
        int minCost = Integer.MAX_VALUE;

        // Try every proper divisor d of the substring length
        for (int d = 1; d < len; d++) {
            if (len % d != 0) {
                continue;
            }

            int cost = 0;
            // Check each of the d groups
            for (int group = 0; group < d; group++) {
                int l = i + group;
                int r = l + (len / d - 1) * d;

                while (l < r) {
                    if (str.charAt(l) != str.charAt(r)) {
                        cost++;
                    }
                    l += d;
                    r -= d;
                }
            }

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public int minimumChanges(String s, int k) {
        n = s.length();
        str = s;

        // Precompute semi-palindrome conversion cost for every substring s[i...j]
        costMemo = new int[n][n];
        for (int[] row : costMemo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                costMemo[i][j] = getCost(i, j);
            }
        }

        // DP table to store min changes to partition substring s[start...n-1] into pieces remaining
        memo = new int[n + 1][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        memo[n][0] = 0;

        for (int start = n; start >= 0; start--) {
            for (int pieces = 1; pieces <= k; pieces++) {
                if (start + 2 * pieces > n) {
                    continue;
                }

                int result = Integer.MAX_VALUE;

                for (int end = start + 1; end < n; end++) {
                    int remainCost = memo[end + 1][pieces - 1];

                    if (remainCost == Integer.MAX_VALUE) {
                        continue;
                    }

                    result = Math.min(result, costMemo[start][end] + remainCost);
                }

                memo[start][pieces] = result;
            }
        }

        return memo[0][k];
    }
}
