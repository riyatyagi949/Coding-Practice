/**
 * PROBLEM STATEMENT: 3753. Total Waviness of Numbers in Range II
 * --------------------------------------------------------------------------------
 * Calculate the total waviness of all integers in the inclusive range [num1, num2].
 * - A digit is a 'peak' if strictly greater than neighbors.
 * - A digit is a 'valley' if strictly less than neighbors.
 * - Constraints: num1, num2 up to 10^15 (requires O(log N) Digit DP solution).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Digit Dynamic Programming
 * --------------------------------------------------------------------------------
 * 1. Range Logic: Use the principle that solve(num2) - solve(num1 - 1) gives 
 * the range sum.
 * 2. State definition: 
 * - pos: current digit position being filled.
 * - tight: constraint flag to ensure we don't exceed the number's digits.
 * - started: boolean flag to track if we have started placing non-zero digits.
 * - prev2, prev1: keep track of the two preceding digits to evaluate waviness.
 * 3. Accumulation: Each state returns a pair {count, sum}, where 'count' is 
 * the number of ways to complete the number, and 'sum' is the accumulated 
 * waviness of all numbers formed from this point forward.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(D * 10 * 10 * 10), where D is the number of digits (approx 15).
 * Space Complexity: O(D * 10 * 10), for the memoization table.
 * --------------------------------------------------------------------------------
 */
class Solution {
    static class Pair {
        long cnt;
        long sum;

        Pair(long c, long s) {
            cnt = c;
            sum = s;
        }
    }

    String digits;
    Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x <= 0) 
        return 0;

        digits = String.valueOf(x);

        memo = new Pair[17][2][2][11][11];

        return dfs(0, 1, 0, 10, 10).sum;
    }

    private Pair dfs(int pos,
                     int tight,
                     int started,
                     int prev2,
                     int prev1) {

        if (pos == digits.length()) {
            return new Pair(1, 0);
        }

        if (memo[pos][tight][started][prev2][prev1] != null) {
            return memo[pos][tight][started][prev2][prev1];
        }

        int limit = tight == 1
                ? digits.charAt(pos) - '0'
                : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                Pair child =
                    dfs(pos + 1, ntight, 0, 10, 10);

                totalCnt += child.cnt;
                totalSum += child.sum;
            } else {

                if (started == 0) {

                    Pair child =
                        dfs(pos + 1, ntight, 1, 10, d);

                    totalCnt += child.cnt;
                    totalSum += child.sum;

                } else if (prev2 == 10) {

                    Pair child =
                        dfs(pos + 1, ntight, 1, prev1, d);

                    totalCnt += child.cnt;
                    totalSum += child.sum;

                } else {

                    int add = 0;

                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }

                    Pair child =
                        dfs(pos + 1, ntight, 1, prev1, d);

                    totalCnt += child.cnt;
                    totalSum += child.sum + add * child.cnt;
                }
            }
        }

        return memo[pos][tight][started][prev2][prev1]
                = new Pair(totalCnt, totalSum);
    }
}
