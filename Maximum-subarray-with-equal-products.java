/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Subarray With Equal Products
 * ==========================================
 * Given an array `nums` of positive integers, a subarray `arr` is product equivalent if
 * `prod(arr) == lcm(arr) * gcd(arr)`.
 * Return the length of the longest product equivalent subarray of `nums`.
 *
 * Constraints:
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 10
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Brute-Force Subarray Scan + GCD/LCM)
 * ==========================================
 * - Approach:
 *   1. Every single-element subarray is trivially product equivalent (`x == x * x`), so `ans`
 *      starts at `1`.
 *   2. For each starting index `i`, maintain running `product`, `gcd`, and `lcm` as `j`
 *      extends from `i` to `n - 1` (`gcd(0, x) == x` for Euclidean GCD).
 *   3. Use `long` for the running product (and `lcm` guard) so intermediate multiplications
 *      do not overflow 32-bit integers even for larger arrays.
 *   4. Whenever `product == (long) gcd * lcm`, update `ans = Math.max(ans, j - i + 1)`.
 *
 * - Complexity:
 *   - Time Complexity: O(n^2 * log(maxVal)) for checking all `O(n^2)` subarrays and
 *     computing GCD/LCM at each step (well within limits for `n <= 100`).
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long product = 1L;
            int gcd = 0;
            long lcm = 1L;

            for (int j = i; j < n; j++) {
                int num = nums[j];
                product *= num;
                gcd = gcd(gcd, num);
                lcm = lcm(lcm, num);

                if (product == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
      return ans;
    }
  private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
  private long lcm(long a, int b) {
        return (a / gcd((int) a, b)) * b;
    }
}
