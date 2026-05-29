/**
 * PROBLEM STATEMENT: 3300. Minimum Element After Replacement With Digit Sum
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums'.
 * Replace each element in 'nums' with the sum of its digits.
 * Return the minimum element in the array after all replacements.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION:
 * --------------------------------------------------------------------------------
 * 1. Iterate through each number in the input array.
 * 2. Calculate the sum of its digits using a helper method (repeated modulo 10
 * and division by 10).
 * 3. Keep track of the global minimum encountered during the iteration.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * K), where N is the length of the array and K is the 
 * average number of digits in each number (K <= 5 for numbers up to 10^4).
 * Space Complexity: O(1), as we only use a few variables for tracking the 
 * minimum value.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            min = Math.min(min, sum);
        }

        return min;
    }
}

