/**
 * PROBLEM STATEMENT: 2657. Find the Prefix Common Array of Two Arrays
 * --------------------------------------------------------------------------------
 * Given two permutations A and B of length n, find the prefix common array C,
 * where C[i] is the count of numbers present in both A[0...i] and B[0...i].
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Frequency Counting
 * --------------------------------------------------------------------------------
 * 1. Logic:
 * As we iterate through indices i from 0 to n-1, we keep track of how many times 
 * each number has appeared across both arrays.
 * 2. Counting:
 * - A number contributes to the "common" count only once it has appeared in 
 *   both A and B. 
 * - Using an array of size (n + 1) is more efficient than a HashMap given the
 *   constraint 1 <= A[i], B[i] <= n.
 * - When we increment the frequency of a number:
 *   - If the new frequency is 2, it means we have just encountered the 
 *     second instance of this number (one from A and one from B), so we increment 
 *     the common count.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We perform a single pass through the arrays of length N.
 * Space Complexity: O(N)
 * - We use a frequency array of size N + 1 to store occurrences.
 * --------------------------------------------------------------------------------
 */


import java.util.HashMap;

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        
        // Since 1 <= A[i], B[i] <= n, a simple integer array is faster than HashMap
        int[] freq = new int[n + 1];
        int commonCount = 0;

        for (int i = 0; i < n; i++) {
            // Process element from A
            freq[A[i]]++;
            if (freq[A[i]] == 2) {
                commonCount++;
            }

            // Process element from B
            freq[B[i]]++;
            if (freq[B[i]] == 2) {
                commonCount++;
            }

            // Store the cumulative count for the current prefix
            result[i] = commonCount;
        }
        
        return result;
    }
}
