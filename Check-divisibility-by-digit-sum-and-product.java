/*
 * ==========================================
 * PROBLEM STATEMENT: Check Divisibility by Digit Sum and Product
 * ==========================================
 * You are given a positive integer n. Determine whether n is divisible by the sum of 
 * the following two values:
 * 1. The digit sum of n (the sum of its digits).
 * 2. The digit product of n (the product of its digits).
 * Return true if n is divisible by this sum; otherwise, return false.
 * 
 * Constraints:
 * 1 <= n <= 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Digit Extraction)
 * ==========================================
 * - Approach:
 *   1. Extract each digit of the integer `n` using modulo (`% 10`) and division (`/ 10`) operations.
 *   2. Maintain running totals for both the **digit sum** and the **digit product**.
 *   3. Sum the digit sum and digit product together to get the target divisor.
 *   4. Check if `n` is divisible by this divisor (i.e., `divisor != 0 && n % divisor == 0`).
 * 
 * - Complexity:
 *   - Time Complexity: O(log10(n)), since the number of iterations equals the number of digits in `n`.
 *   - Space Complexity: O(1) auxiliary space, as we only use a few variables.
 */

class Solution {
    public boolean checkDivisibility(int n) {
        int original = n;
        int digitSum = 0;
        int digitProduct = 1;

        // Extract digits and compute sum and product
        while (n > 0) {
            int digit = n % 10;
            digitSum += digit;
            digitProduct *= digit;
            n /= 10;
        }
        int divisor = digitSum + digitProduct;

        // Return true if divisor is non-zero and original number is divisible by it
        return divisor != 0 && original % divisor == 0;
    }
}
