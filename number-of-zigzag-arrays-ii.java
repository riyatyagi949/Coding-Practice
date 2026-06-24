/**
 * PROBLEM STATEMENT: 3700. Number of ZigZag Arrays II
 * --------------------------------------------------------------------------------
 * A ZigZag array of length n is defined as:
 * 1. Each element in range [l, r].
 * 2. No two adjacent elements are equal.
 * 3. No three consecutive elements form a strictly increasing or decreasing sequence.
 * Return total number of valid ZigZag arrays modulo 10^9 + 7.
 *
 * Constraints:
 * 3 <= n <= 10^9
 * 1 <= l < r <= 75
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Matrix Exponentiation
 * --------------------------------------------------------------------------------
 * 1. State: Let dp[i][curr][prev] be the count of valid ZigZag arrays of length i 
 * ending with (prev, curr).
 * ZigZag conditions imply:
 * - If prev < curr, next element x must satisfy x < curr (to avoid increasing).
 * - If prev > curr, next element x must satisfy x > curr (to avoid decreasing).
 * - Also, x != curr.
 * 2. Transition: This can be represented by a linear transformation:
 * V_{i} = T * V_{i-1}, where V is a state vector of size M*M and T is a transition matrix.
 * 3. Matrix Exponentiation: Since N is up to 10^9, we compute T^(N-2) using binary 
 * exponentiation in O((M^2)^3 * log N) time, which is roughly O(M^6 log N).
 * Given M <= 75 is quite large for M^6, we observe the state can be compressed 
 * to just (curr, direction), reducing the matrix size to (2M) * (2M).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O((2M)^3 * log N), where M = (r - l + 1). With M <= 75, this 
 * fits within time limits.
 * Space Complexity: O((2M)^2) to store the transition matrix.
 * --------------------------------------------------------------------------------
 */

class Solution {
     static final long MOD = 1_000_000_007L;
     public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;
        int S = 2 * m;

        long[][] T = new long[S][S];

        for (int x = 0; x < m; x++) {
            int upX = x;
            int downX = m + x;

            for (int y = 0; y < x; y++) {
                int downY = m + y;
                T[downY][upX] = 1;
            }

            for (int y = x + 1; y < m; y++) {
                int upY = y;
                T[upY][downX] = 1;
            }
        }

        long[] init = new long[S];

        for (int a = 0; a < m; a++) {
            for (int b = 0; b < m; b++) {

                if (a == b) continue;

                if (a < b) {
                    init[b]++;
                } else {
                    init[m + b]++;
                }
            }
        }

        long[][] P = matPow(T, n - 2);

        long ans = 0;

        for (int i = 0; i < S; i++) {

            long cur = 0;

            for (int j = 0; j < S; j++) {
                cur = (cur + P[i][j] * init[j]) % MOD;
            }

            ans = (ans + cur) % MOD;
        }

        return (int) ans;
    }

    private long[][] matMul(long[][] A, long[][] B) {

        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {

                    if (B[k][j] == 0) continue;

                    C[i][j] =
                        (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }

    private long[][] matPow(long[][] A, long exp) {

        int n = A.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {

            if ((exp & 1) == 1) {
                res = matMul(res, A);
            }

            A = matMul(A, A);
            exp >>= 1;
        }

        return res;
    }
}

