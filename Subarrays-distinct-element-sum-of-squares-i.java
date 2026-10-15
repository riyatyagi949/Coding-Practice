/*
 * ==========================================
 * PROBLEM STATEMENT: Subarrays Distinct Element Sum of Squares I
 * ==========================================
 * You are given a 0-indexed integer array `nums`. 
 * The distinct count of a subarray is the number of unique values in that subarray.
 * Return the sum of the squares of the distinct counts of all subarrays of `nums`.
 * 
 * Constraints:
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Nested Loops with Hash Set)
 * ==========================================
 * - Approach:
 *   1. Since the constraints are small (`n <= 100`), we can evaluate all possible subarrays using two nested loops.
 *   2. The outer loop fixes the starting index `i` (`0` to `n - 1`), and the inner loop extends the ending index `j` (`i` to `n - 1`).
 *   3. Maintain a `HashSet` for each starting position to track distinct elements as we add `nums.get(j)`.
 *   4. For each subarray `nums[i..j]`, add the square of the size of the set (`count * count`) to our total sum.
 * 
 * - Complexity:
 *   - Time Complexity: O(n^2), where `n` is the length of the array, since we visit every pair of indices `(i, j)`.
 *   - Space Complexity: O(n) to store the distinct elements in the set for each starting position.
 */

import java.util.HashSet;
import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            HashSet<Integer> distinctSet = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinctSet.add(nums.get(j));
                int count = distinctSet.size();
                totalSum += count * count;
            }
        }

        return totalSum;
    }
}
