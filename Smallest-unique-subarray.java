/*
 * ==========================================
 * PROBLEM STATEMENT: Smallest Unique Subarray
 * ==========================================
 * Given an integer array `nums`, find the minimum length of a subarray that appears
 * only once in `nums` (not identical to any other subarray of the same length).
 * Return the minimum possible length of such a subarray.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Binary Search + Rolling Hash)
 * ==========================================
 * - Approach:
 *   1. Monotonicity: If a unique subarray of length `L` exists, any longer length `L' > L`
 *      can also contain a unique subarray (a subarray of length `n` is always unique).
 *      Binary search `len` in `[1, n]` to find the smallest valid length.
 *   2. Precompute powers of `base` (`1_000_003L`) and polynomial rolling prefix hashes
 *      modulo `1_000_000_007L` (`nums[i] <= 10^5`, so `base > max(nums[i])` is chosen
 *      to avoid small-base collisions).
 *   3. To check `hasUniqueSubarray(len)`: compute the rolling hash of every subarray of
 *      length `len`, count occurrences in a hash map, and return `true` if any hash
 *      has frequency `1`.
 *
 * - Complexity:
 *   - Time Complexity: O(n log n) — `O(log n)` binary search iterations, each scanning
 *     `O(n)` subarrays in `O(1)` hash computation per window.
 *   - Space Complexity: O(n) for `pow`, `prefix`, and the hash map.
 */

import java.util.HashMap;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long base = 1_000_003L;
        long mod = 1_000_000_007L;

        long[] pow = new long[n + 1];
        long[] prefix = new long[n + 1];

        pow[0] = 1;

        for (int i = 0; i < n; i++) {
            pow[i + 1] = (pow[i] * base) % mod;
            prefix[i + 1] = (prefix[i] * base + nums[i]) % mod;
        }

        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (hasUniqueSubarray(mid, n, prefix, pow, mod)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int len, int n, long[] prefix, long[] pow, long mod) {
        HashMap<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            long hash = (prefix[i + len] - (prefix[i] * pow[len]) % mod + mod) % mod;
            freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
