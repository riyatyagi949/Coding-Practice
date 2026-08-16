/*
 * ==========================================
 * PROBLEM STATEMENT: Min Product Subset
 * ==========================================
 * Given an integer array arr[], find the minimum possible product that can be obtained 
 * by multiplying the elements of any non-empty subset of the array.
 * 
 * Constraints:
 * 1 <= arr.size() <= 10
 * -10 <= arr[i] <= 10
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy / Case Analysis)
 * ==========================================
 * - Approach: We analyze the counts of negative numbers, zeros, and positive numbers to find the minimum subset product:
 *   1. Count the number of negative elements and track the `maxNegative` (the negative number closest to zero, i.e., largest in value).
 *   2. Track the smallest positive element (`minPositive`) and whether any zeros exist (`hasZero`).
 *   3. Compute the product of all non-zero elements.
 *   4. Evaluate cases based on negative counts and zeros:
 *      - If the count of negative numbers is odd, the total product of all non-zero elements is already negative and optimal.
 *      - If the count of negative numbers is even and > 0, removing the `maxNegative` (dividing by it) makes the product negative and minimal.
 *      - If there are no negative numbers:
 *        - If zeros are present, the minimum product is 0.
 *        - If no zeros are present, the minimum product is simply the smallest positive element (`minPositive`).
 * 
 * - Complexity:
 *   - Time Complexity: O(n), since we iterate through the array of size n once.
 *   - Space Complexity: O(1), as we only use a few tracking variables.
 */

class Solution {
    public int minProd(int[] arr) {
        int minPositive = Integer.MAX_VALUE;
        int negativeCount = 0;
        int maxNegative = Integer.MIN_VALUE; 
        int product = 1;
        boolean hasZero = false;

        // Traverse the array to collect statistics
        for (int num : arr) {
            if (num == 0) {
                hasZero = true;
                continue;
            }

            if (num < 0) {
                negativeCount++;
                maxNegative = Math.max(maxNegative, num); // Track the largest negative (closest to 0)
            } else {
                minPositive = Math.min(minPositive, num);
            }
            product *= num;
        }
        
        // Edge case: array contains only a single 1 and no other numbers
        if (product == 1 && negativeCount == 0 && minPositive == Integer.MAX_VALUE) {
            return 0;
        }
        
        // Case 1: Odd number of negative elements -> product of all non-zeros is negative and minimal
        if (negativeCount % 2 == 1) {
            return product;
        }

        // Case 2: Even number of negative elements (> 0) -> remove the largest negative to make product negative
        if (negativeCount > 0) {
            return product / maxNegative;
        }

        // Case 3: No negative elements, but zero(s) exist -> minimum product is 0
        if (hasZero) {
            return 0;
        }

        // Case 4: Only positive elements -> minimum product is the smallest positive element
        return minPositive;
    }
}
