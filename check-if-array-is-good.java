/**
 * PROBLEM STATEMENT: 2784. Check if Array is Good
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums'. We consider an array "good" if it is a 
 * permutation of an array base[n].
 * * base[n] = [1, 2, ..., n - 1, n, n] 
 * * In other words, it is an array of length n + 1 which contains numbers from 1 to n - 1 
 * exactly once, plus exactly two occurrences of the maximum value 'n'.
 * * Return true if the given array is good, otherwise return false.
 * * Constraints:
 * - 1 <= nums.length <= 100
 * - 1 <= nums[i] <= 200
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Frequency Array Counting
 * --------------------------------------------------------------------------------
 * 1. Determine 'n':
 * If 'nums' is a valid permutation of base[n], its length must be exactly n + 1.
 * Therefore, the target maximum value 'n' must be equal to (nums.length - 1).
 * * 2. Frequency Array Tracking:
 * We create a frequency array 'count' of size nums.length to keep track of occurrences.
 * - Any element 'a' in 'nums' that is greater than or equal to nums.length is instantly invalid.
 * - We populate the frequency array with counts of each number.
 * * 3. Validation Pass:
 * - Every number from 1 to n - 1 (which corresponds to index 1 to nums.length - 2) 
 * must appear exactly 1 time.
 * - The maximum number 'n' (which corresponds to index nums.length - 1) 
 * must appear exactly 2 times.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through the array once to count frequencies: O(N).
 * - We iterate through the count array once to validate frequency rules: O(N).
 * - Overall Time Complexity is linear, O(N).
 * * Space Complexity: O(N)
 * - We utilize an auxiliary counting array of size N to store frequencies.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public boolean isGood(int[] nums) {
        int len = nums.length;
        
        // base[n] has a length of n + 1. 
        // Therefore, the maximum expected element 'n' should be exactly (len - 1).
        // If an array has fewer than 2 elements, it can never form a valid base[1] = [1, 1].
        if (len < 2) {
            return false;
        }

        int[] count = new int[len];

        // Step 1: Count occurrences of each number
        for (int a : nums) {
            // Since the maximum valid number is len - 1, any value >= len is out of bounds
            if (a >= len) {
                return false;
            }
            count[a]++;
        }

        // Step 2: Validate that numbers from 1 to n - 1 appear exactly once
        for (int i = 1; i <= len - 2; i++) {
            if (count[i] != 1) {
                return false;
            }
        }

        // Step 3: Validate that the maximum number 'n' (at index len - 1) appears exactly twice
        if (count[len - 1] != 2) {
            return false;
        }

        return true;
    }
}
