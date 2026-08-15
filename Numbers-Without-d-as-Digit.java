/*
 * ==========================================
 * PROBLEM STATEMENT: Numbers Without d as Digit
 * ==========================================
 * Given a number n, count the numbers from 1 to n that don’t contain digit d in their decimal representation.
 * 
 * Examples:
 * Input: n = 25, d = 3
 * Output: 22
 * Explanation: From 1 to 25, the numbers 3, 13, and 23 contain the digit 3, so the answer is 25 - 3 = 22.
 * 
 * Constraints:
 * 0 <= n <= 10^9
 * 0 <= d <= 9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Digit DP)
 * ==========================================
 * - Approach: We use Digit Dynamic Programming to count valid numbers up to n digit by digit.
 *   1. Convert n to a character array of digits to process position by position from left to right.
 *   2. Maintain state variables in our DP table:
 *      - `pos`: Current digit position being processed.
 *      - `tight`: Flag indicating whether our choices are restricted by the prefix of n (1 if restricted, 0 otherwise).
 *      - `started`: Flag indicating whether we have started forming a non-zero number (to handle leading zeros correctly).
 *   3. For each state, we iterate through possible digits from 0 up to the allowed limit (either `digits[pos] - '0'` if tight, or 9).
 *   4. If a chosen digit equals `d` and we have already started the number, we skip it.
 *   5. Aggregate the total valid paths ending at the last position where `started == 1`.
 * 
 * - Complexity:
 *   - Time Complexity: O(L * 2 * 2 * 10), where L is the number of digits in n (at most 10 for 10^9). This is extremely efficient and runs in O(log10(n)) time.
 *   - Space Complexity: O(L * 2 * 2) for the DP table, which uses negligible auxiliary space.
 */


class Solution {
    public int countWithout(int n, int d) {
        if (n == 0) return 0;

        char[] digits = String.valueOf(n).toCharArray();
        int len = digits.length;

        long[][][] dp = new long[len + 1][2][2];

        dp[0][1][0] = 1;

        for (int pos = 0; pos < len; pos++) {
           for (int tight = 0; tight <= 1; tight++) {
              for (int started = 0; started <= 1; started++) {

                    long ways = dp[pos][tight][started];
                    if (ways == 0) continue;

                    int limit = (tight == 1) ? digits[pos] - '0' : 9;

            for (int digit = 0; digit <= limit; digit++) {
                 int newTight = (tight == 1 && digit == limit) ? 1 : 0;
                 int newStarted =(started == 1 || digit != 0) ? 1 : 0;

                  if (newStarted == 1 && digit == d) {
                       continue;
                        }
                     dp[pos + 1][newTight][newStarted] += ways;
                    }
                }
            }
        }
        return (int) (dp[len][0][1] + dp[len][1][1] );
    }
}
