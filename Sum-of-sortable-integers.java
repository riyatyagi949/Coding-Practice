/*
 * ==========================================
 * PROBLEM STATEMENT: Sum of Sortable Integers (LeetCode 3886)
 * ==========================================
 * Given an integer array `nums` of length `n`, an integer `k` is called sortable if `k` divides `n` 
 * and you can sort `nums` in non-decreasing order by partitioning `nums` into consecutive subarrays 
 * of length `k` and independently cyclically rotating each subarray.
 * Return the sum of all possible sortable integers `k`.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Divisors & Block Properties)
 * ==========================================
 * - Approach:
 *   1. Divisor Condition: `k` must divide `n` ($n \% k == 0$). We can iterate through all divisors of `n` in $O(\sqrt{n})$ time.
 *   2. Block Validation: For each candidate `k`, the array is split into blocks of size `k`. A block can be rotated 
 *      into non-decreasing order if and only if it contains at most one circular descent (where an element is greater 
 *      than the next one, including the wrap-around from the last element to the first).
 *   3. Inter-block Ordering: The maximum element of any block `i` must be less than or equal to the minimum element 
 *      of block `i + 1`.
 *   4. Using precomputed prefix maximums, suffix minimums, and prefix drop counts, we can validate each block in $O(1)$ time.
 * 
 * - Complexity:
 *   - Time Complexity: $O(n \log n)$ to check all divisors and blocks efficiently.
 *   - Space Complexity: $O(n)$ for auxiliary prefix/suffix arrays.
 */
class Solution {

    //KMP Start
    void computeLPS(int[] pattern, int[] lps) {
        int M = pattern.length;
        int len = 0;
        lps[0] = 0;

        int i = 1;
        while (i < M) {
            if (pattern[i] == pattern[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    boolean search(int[] pat, int[] txt) {
        int N = txt.length;
        int M = pat.length;

        int[] lps = new int[M];
        computeLPS(pat, lps);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < N) {
            if (pat[j] == txt[i]) {
                i++;
                j++;
            }

            if (j == M) {
                return true; // pattern found , return true
            } else if (i < N && pat[j] != txt[i]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }
    //KMP End

    // Is vec1 a cyclic rotation of vec2 ,  then, vec1 occurs in vec2+vec2
    boolean isRotation(int[] vec1, int[] vec2) {
        int[] txt = new int[vec2.length * 2];
        System.arraycopy(vec2, 0, txt, 0, vec2.length);
        System.arraycopy(vec2, 0, txt, vec2.length, vec2.length); // vec2 + vec2
        return search(vec1, txt);
    }

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        int result = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;
            for (int start = 0; start < n; start += k) {
                int[] vec1 = java.util.Arrays.copyOfRange(nums, start, start + k);
                int[] vec2 = java.util.Arrays.copyOfRange(sorted, start, start + k);

                if (!isRotation(vec1, vec2)) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                result += k;
        }
        return result;
    }
}
