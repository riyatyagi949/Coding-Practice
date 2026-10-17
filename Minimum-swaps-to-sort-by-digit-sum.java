/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Swaps to Sort by Digit Sum (LeetCode 3551)
 * ==========================================
 * You are given an array `nums` of distinct positive integers. 
 * You need to sort the array in increasing order based on the sum of the digits of each number. 
 * If two numbers have the same digit sum, the smaller number appears first. 
 * Return the minimum number of swaps required to rearrange `nums` into this sorted order.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Permutation Cycles)
 * ==========================================
 * - Approach:
 *   1. Compute the digit sum for each number in `nums`.
 *   2. Create a custom-sorted arrangement (or array of pairs/objects containing `(digit_sum, value, original_index)`) 
 *      to determine the exact target position each element should move to.
 *   3. Sort this collection primarily by digit sum, and secondarily by value.
 *   4. Map each element to its correct target index in the sorted order.
 *   5. Use cycle decomposition (graph traversal) to find the minimum number of swaps: 
 *      - Total swaps = `n - (number of disjoint cycles)`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log n) due to sorting the elements based on digit sums and values.
 *   - Space Complexity: O(n) to store target positions, tracking arrays, and sorting structures.
 */

import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Step 1: Store elements with their original indices and digit sums
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Step 2: Sort indices based on custom criteria: digit sum ascending, then value ascending
        Arrays.sort(indices, (a, b) -> {
            int sumA = getDigitSum(nums[a]);
            int sumB = getDigitSum(nums[b]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        // Step 3: Find the number of cycles to compute minimum swaps = n - cycles
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || indices[i] == i) {
                if (indices[i] == i) visited[i] = true;
                continue;
            }

            // Traverse the cycle
            cycles++;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = indices[curr]; // Move to the target index position
            }
        }

        return n - cycles;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
