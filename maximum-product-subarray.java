/**
 * PROBLEM STATEMENT: Maximum Product Subarray
 * --------------------------------------------------------------------------------
 * Given an array arr[] that contains positive and negative integers (and zero). 
 * Find the maximum product that can be obtained from any contiguous subarray.
 * * Note: The result is guaranteed to fit in a 32-bit integer.
 * * Example 1:
 * Input: arr[] = [-2, 6, -3, -10, 0, 2]
 * Output: 180
 * Explanation: [6, -3, -10] gives 6 * -3 * -10 = 180.
 * * Example 2:
 * Input: arr[] = [-1, -3, -10, 0, 6]
 * Output: 30
 * Explanation: [-3, -10] gives 30.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Dynamic Programming (Modified Kadane's)
 * --------------------------------------------------------------------------------
 * 1. The Challenge:
 * Unlike the Maximum Sum Subarray (Kadane's), a very small negative product 
 * can become a very large positive product if multiplied by another negative number.
 * * 2. The Strategy:
 * At each index 'i', we maintain two values:
 * - 'maxEnding': The maximum product ending at this index.
 * - 'minEnding': The minimum product ending at this index.
 * * 3. State Transitions:
 * - If arr[i] is positive: 
 * New maxEnding = max(arr[i], maxEnding * arr[i])
 * New minEnding = min(arr[i], minEnding * arr[i])
 * - If arr[i] is negative: 
 * Multiplying by a negative swaps the roles of max and min. 
 * New maxEnding = max(arr[i], minEnding * arr[i])
 * New minEnding = min(arr[i], maxEnding * arr[i])
 * - If arr[i] is zero: 
 * Both maxEnding and minEnding reset to 0.
 * * 4. Implementation Detail:
 * We swap maxEnding and minEnding when we encounter a negative number to 
 * simplify the logic into a single consistent update rule.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array exactly once.
 * Space Complexity: O(1)
 * - We use a constant amount of extra space for the three integer variables.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    int maxProduct(int[] arr) {
        int maxEnding = arr[0];
        int minEnding = arr[0];
        int maxSoFar = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];

            if (curr < 0) {
                int temp = maxEnding;
                maxEnding = minEnding;
                minEnding = temp;
            }

            maxEnding = Math.max(curr, maxEnding * curr);
            minEnding = Math.min(curr, minEnding * curr);

            maxSoFar = Math.max(maxSoFar, maxEnding);
        }

        return maxSoFar;
    }
}


