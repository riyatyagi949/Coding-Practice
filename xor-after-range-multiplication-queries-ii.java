/**
 * PROBLEM STATEMENT: 3655. XOR After Range Multiplication Queries II
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums' of length n and a 2D integer array 'queries' 
 * of size q, where queries[i] = [li, ri, ki, vi].
 * * For each query:
 * 1. Set idx = li.
 * 2. While idx <= ri:
 * Update: nums[idx] = (nums[idx] * vi) % (10^9 + 7).
 * Set idx += ki.
 * * Return the bitwise XOR of all elements in 'nums' after processing all queries.
 * * Constraints:
 * - n, q <= 10^5
 * - li, ri, ki <= n
 * - vi <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SQUARE ROOT DECOMPOSITION / BLOCK-WISE DIFFERENCE ARRAY
 * --------------------------------------------------------------------------------
 * 1. The Challenge:
 * A simple simulation (like version I) would take O(Q * N/K). If K=1 for all queries,
 * complexity becomes O(Q * N) = 10^10, which is too slow.
 * * 2. Square Root Heuristic:
 * - Large K (K >= sqrt(N)): The number of updates per query is at most N/sqrt(N) = sqrt(N).
 * Total complexity for these is O(Q * sqrt(N)).
 * - Small K (K < sqrt(N)): We use a "difference array" technique grouped by K.
 * * 3. Difference Array for Small K:
 * For a fixed K, we can view indices as being in different congruence classes modulo K.
 * For a query (L, R, K, V):
 * - Multiply diff[L] by V.
 * - Multiply diff[R + next_jump] by V_inverse (to stop the multiplication effect).
 * - After processing all queries for a fixed K, perform a prefix product with step K:
 * diff[i] = diff[i] * diff[i-K].
 * * 4. Modular Inverse:
 * Since the MOD (10^9 + 7) is prime, we use Fermat's Little Theorem: V^(MOD-2) % MOD.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O((Q + N) * sqrt(N))
 * - Processing large K: O(Q * sqrt(N)).
 * - Processing small K classes: sqrt(N) classes * O(N/K) work + Q queries.
 * Space Complexity: O(N + sqrt(N))
 * - Difference array O(N) and map for small K.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    int M = (int)1e9 + 7;
    long power(long a, long b) {
        long result = 1;
        a = a % M;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % M;
            }
            a = (a * a) % M;
            b >>= 1;
        }
        return result;
    }
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int blockSize = (int)Math.ceil(Math.sqrt(n));

        Map<Integer, List<int[]>> smallKMap = new HashMap<>();

        for (int[] query : queries) {
            int L = query[0];
            int R = query[1];
            int K = query[2];
            int V = query[3];

            if (K >= blockSize) {
                for (int i = L; i <= R; i += K) {
                    nums[i] = (int)((1L * nums[i] * V) % M);
                }
            } 
            else {
                smallKMap.computeIfAbsent(K, k -> new ArrayList<>()).add(query);
            }
        }
        for (Map.Entry<Integer, List<int[]>> entry : smallKMap.entrySet()) {
            int K = entry.getKey();
            List<int[]> allQueries = entry.getValue();

            long[] diff = new long[n];
            Arrays.fill(diff, 1);

            for (int[] query : allQueries) {
                int L = query[0];
                int R = query[1];
                int V = query[3];

                diff[L] = (diff[L] * V) % M;

                int steps = (R - L) / K;
                int next = L + (steps + 1) * K;

                if (next < n) {
                    diff[next] = (diff[next] * power(V, M - 2)) % M;
                }
            }
            for (int i = 0; i < n; i++) {
                if (i - K >= 0) {
                    diff[i] = (diff[i] * diff[i - K]) % M;
                }
            }
            for (int i = 0; i < n; i++) {
                nums[i] = (int)((1L * nums[i] * diff[i]) % M);
            }
        }
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}

