/*
 * ==========================================
 * PROBLEM STATEMENT: Triplets with Sum in Range
 * ==========================================
 * Given an array arr[] and a range from l to r, the task is to count the number of triplets 
 * having a sum in the range [l, r].
 * 
 * Constraints:
 * 1 <= arr.size() <= 10^3
 * 1 <= arr[i] <= 10^3
 * 1 <= l <= r <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sorting + Two Pointers)
 * ==========================================
 * - Approach:
 *   1. Range Query Property: The number of triplets with sum in the range [l, r] can be found using 
 *      the inclusion-exclusion principle: `countSumInRange = countLessEqual(r) - countLessEqual(l - 1)`.
 *   2. Sorting: We first sort the array to enable efficient two-pointer traversal.
 *   3. Two Pointers (`countLessEqual` function): 
 *      - Fix an element at index `i` as the first element of the triplet.
 *      - Use two pointers, `j = i + 1` and `k = n - 1`, to find pairs whose sum with `arr[i]` is `<= target`.
 *      - If `arr[i] + arr[j] + arr[k] <= target`, all elements between `j` and `k` paired with `arr[i]` and `arr[j]` 
 *        will also have a sum `<= target`. Thus, we add `(k - j)` to our count and increment `j`.
 *      - Otherwise, we decrement `k` to decrease the sum.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log n + n^2) = O(n^2), where n is the size of the array. Sorting takes O(n log n) 
 *     and the two-pointer traversal runs in O(n^2) time.
 *   - Space Complexity: O(1) auxiliary space (ignoring sorting stack space).
 */

import java.util.Arrays;

class Solution {
    public int countTriplets(int[] arr, int l, int r) {
        // Step 1: Sort the array to use two-pointer technique
        Arrays.sort(arr);

        // Step 2: Use range query: count(sum <= r) - count(sum <= l - 1)
        long ans = countLessEqual(arr, r) - countLessEqual(arr, l - 1);
        return (int) ans;
    }

    // Helper method to count how many triplets have a sum less than or equal to target
    private long countLessEqual(int[] arr, int target) {
        int n = arr.length;
        long count = 0;

        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                long sum = (long) arr[i] + arr[j] + arr[k];

                if (sum <= target) {
                    // All elements from j+1 to k form valid triplets with arr[i] and arr[j]
                    count += (k - j);
                    j++;
                } 
                else {
                    // Sum exceeds target, move the right pointer down
                    k--;
                }
            }
        }
        return count;
    }
}
