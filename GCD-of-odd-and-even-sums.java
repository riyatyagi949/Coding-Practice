/**
 * PROBLEM STATEMENT: 3658. GCD of Odd and Even Sums
 * --------------------------------------------------------------------------------
 * Given an integer n, calculate:
 * - sumOdd: Sum of the first n positive odd numbers (1 + 3 + 5 + ... + (2n-1))
 * - sumEven: Sum of the first n positive even numbers (2 + 4 + 6 + ... + 2n)
 * Return the GCD(sumOdd, sumEven).
 * * MATHEMATICAL DERIVATION:
 * 1. The sum of the first n positive odd numbers is n^2.
 * Example n=4: 1+3+5+7 = 16 = 4^2.
 * 2. The sum of the first n positive even numbers is n(n+1).
 * Example n=4: 2+4+6+8 = 20 = 4*5.
 * 3. We need GCD(n^2, n(n+1)).
 * 4. Since n is a common factor in both:
 * GCD(n^2, n(n+1)) = n * GCD(n, n+1).
 * 5. The GCD of any two consecutive integers (n and n+1) is always 1.
 * 6. Therefore, GCD(n^2, n(n+1)) = n * 1 = n.
 *
 * OPTIMAL SOLUTION:
 * --------------------------------------------------------------------------------
 * Based on the mathematical derivation, the result is simply n.
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

class Solution {
    public int gcdOfOddAndEvenSums(int n) {
        // Since sumOdd = n^2 and sumEven = n * (n + 1)
        // GCD(n^2, n * (n + 1)) = n * GCD(n, n + 1)
        // The GCD of two consecutive integers (n, n + 1) is always 1.
        // Thus, the result is n * 1 = n.
        return n;
    }
}
