/**
 * PROBLEM STATEMENT: 788. Rotated Digits
 * --------------------------------------------------------------------------------
 * An integer x is 'good' if after rotating each digit individually by 180 degrees, 
 * we get a valid number that is different from x.
 * 
 * Rules for rotation:
 * - 0, 1, 8 rotate to themselves.
 * - 2 and 5 rotate to each other.
 * - 6 and 9 rotate to each other.
 * - 3, 4, and 7 are invalid (they don't form a digit after rotation).
 * 
 * Goal: Given n, return the count of good integers in the range [1, n].
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Iterative Digit Checking
 * --------------------------------------------------------------------------------
 * 1. Iterate through every number from 1 to n.
 * 2. For each number, examine its digits one by one.
 * 3. A number is "good" if:
 *    - It contains ONLY digits from the set {0, 1, 8, 2, 5, 6, 9}.
 *    - It contains AT LEAST ONE digit from the set {2, 5, 6, 9} (this ensures 
 *      the number changes after rotation).
 * 4. If a number contains 3, 4, or 7, it is immediately disqualified.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * log10(N))
 * - We iterate N times. For each N, we check its digits, which takes log10(N) time.
 * Space Complexity: O(1)
 * - We use a constant amount of extra space regardless of input size.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int rotatedDigits(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (isGood(i))
             count++;
        }

        return count;
    }

    private boolean isGood(int num) {
        boolean isDifferent = false;

        while (num > 0) {
            int digit = num % 10;

            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }

            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                isDifferent = true;
            }

            num /= 10;
        }

        return isDifferent;
    }
}

