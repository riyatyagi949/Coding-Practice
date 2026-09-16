/*
 * ==========================================
 * PROBLEM STATEMENT: Dominant Pairs
 * ==========================================
 * Given an even-sized integer array `arr[]`, count the number of dominant pairs. 
 * A pair of indices (i, j) is called dominant if:
 * 1. 0 <= i < arr.size() / 2
 * 2. arr.size() / 2 <= j < arr.size()
 * 3. arr[i] >= 5 * arr[j]
 * Return the total number of dominant pairs (0-based indexing).
 * 
 * Constraints:
 * 1 <= arr.size() <= 10^4
 * -10^4 <= arr[i] <= 10^4
 * arr.size() is even.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sorting & Two Pointers)
 * ==========================================
 * - Approach:
 *   1. Division: Split the array into two halves: the first half (`0` to `n/2 - 1`) and the second half (`n/2` to `n - 1`).
 *   2. Sorting: Sort both halves independently in ascending order.
 *   3. Two-Pointer Technique: 
 *      - Use a pointer `j` for the second half and iterate through each element `i` in the first half.
 *      - Since both halves are sorted, if `first[i] >= 5 * second[j]`, then `first[i]` will also satisfy 
 *        the condition for all elements in the second half up to index `j`. We can advance `j` and accumulate 
 *        the count of valid elements (`j` elements).
 * 
 * - Complexity:
 *   - Time Complexity: O(N log N) due to sorting the two halves of size `N/2`.
 *   - Space Complexity: O(N) to store the elements of the split arrays.
 */

import java.util.Arrays;

class Solution {
    public int dominantPairs(int[] arr) {
        int n = arr.length;
        int half = n / 2;

        int[] first = new int[half];
        int[] second = new int[half];

        // Step 1: Split the array into two halves
        for (int i = 0; i < half; i++) {
            first[i] = arr[i];
            second[i] = arr[i + half];
        }
        
        // Step 2: Sort both halves in ascending order
        Arrays.sort(first);
        Arrays.sort(second);

        int j = 0;
        int count = 0;

        // Step 3: Use two pointers to count dominant pairs efficiently
        for (int i = 0; i < half; i++) {
            while (j < half && (long) first[i] >= 5L * second[j]) {
                j++;
            }
            count += j;
        }

        return count;
    }
}
