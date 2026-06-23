/**
 * PROBLEM STATEMENT: 3699. Number of ZigZag Arrays I
 * --------------------------------------------------------------------------------
 * A ZigZag array of length n is defined as follows:
 * 1. Each element lies in the range [l, r].
 * 2. No two adjacent elements are equal (a[i] != a[i+1]).
 * 3. No three consecutive elements form a strictly increasing (a[i] < a[i+1] < a[i+2])
 * or strictly decreasing (a[i] > a[i+1] > a[i+2]) sequence.
 * Return the total number of valid ZigZag arrays modulo 10^9 + 7.
 * * * Constraints:
 * 3 <= n <= 2000
 * 1 <= l < r <= 2000
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Dynamic Programming
 * --------------------------------------------------------------------------------
 * 1. Let up[i][x] be the number of valid ZigZag arrays of length 'i' ending with value 'x',
 * where the last move was strictly increasing (a[i-1] < a[i]).
 * 2. Let down[i][x] be the number of valid ZigZag arrays of length 'i' ending with value 'x',
 * where the last move was strictly decreasing (a[i-1] > a[i]).
 * 3. Transitions:
 * - To form an increasing move ending at x: the previous move must have been decreasing,
 * and the previous value must be < x.
 * up[i][x] = sum(down[i-1][y]) for all y < x.
 * - To form a decreasing move ending at x: the previous move must have been increasing,
 * and the previous value must be > x.
 * down[i][x] = sum(up[i-1][y]) for all y > x.
 * 4. Optimization: Use prefix/suffix sums to calculate the transitions in O(1) 
 * instead of O(M), resulting in O(N*M) total time.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * M), where N is the length of the array and M = (r - l + 1) 
 * is the size of the range.
 * Space Complexity: O(M), using two arrays to store current state DP values.
 * --------------------------------------------------------------------------------
 */
class Solution {
   static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int b = 0; b < m; b++) {
            up[b] = b;       
            down[b] = m - 1 - b;  
        }

        if (n == 2) {
            long ans = 0;
            for (int i = 0; i < m; i++) {
                ans = (ans + up[i] + down[i]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {

            long[] prefixDown = new long[m];
            long[] suffixUp = new long[m];

            prefixDown[0] = down[0];
            for (int i = 1; i < m; i++) {
                prefixDown[i] =
                    (prefixDown[i - 1] + down[i]) % MOD;
            }

            suffixUp[m - 1] = up[m - 1];
            for (int i = m - 2; i >= 0; i--) {
                suffixUp[i] =
                    (suffixUp[i + 1] + up[i]) % MOD;
            }

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            for (int x = 0; x < m; x++) {

                if (x > 0) {
                    newUp[x] = prefixDown[x - 1];
                }
                if (x < m - 1) {
                    newDown[x] = suffixUp[x + 1];
                }
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}
