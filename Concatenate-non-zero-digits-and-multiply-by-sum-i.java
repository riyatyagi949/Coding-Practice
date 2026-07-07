/**
 * PROBLEM STATEMENT: 3754. Concatenate Non-Zero Digits and Multiply by Sum I
 * --------------------------------------------------------------------------------
 * Given an integer n:
 * 1. Form a new integer 'x' by concatenating all non-zero digits of n in order.
 * 2. Calculate 'sum', which is the sum of the digits in 'x'.
 * 3. Return 'x * sum'.
 * If there are no non-zero digits, x = 0.
 *
 * Example: n = 10203004
 * Non-zero digits: 1, 2, 3, 4 -> x = 1234
 * Sum of digits: 1 + 2 + 3 + 4 = 10
 * Result: 1234 * 10 = 12340
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: String/Digit Manipulation
 * --------------------------------------------------------------------------------
 * 1. Convert the integer to a string or process digits using modulo/division.
 * 2. Iterate through each digit of the number.
 * 3. If a digit is non-zero:
 * - Append it to a StringBuilder or accumulate it into a long variable 'x'.
 * - Add the digit value to a running variable 'sum'.
 * 4. Multiply 'x' by 'sum' and return the result.
 * 5. Handle the edge case where no non-zero digits exist (x = 0).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(D), where D is the number of digits in n (D = log10(n)).
 * Space Complexity: O(D) to store the concatenated digits as a string/long.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;

        String s = String.valueOf(n);

        for (char c : s.toCharArray()) {
            if (c != '0') {
                int digit = c - '0';
                x = x * 10 + digit;
                sum += digit;
            }
        }
        return x * sum;
    }
}
