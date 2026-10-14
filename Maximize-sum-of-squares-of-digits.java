/*
 * ==========================================
 * PROBLEM STATEMENT: Maximize Sum of Squares of Digits
 * ==========================================
 * Given two positive integers `num` and `sum`, construct a positive integer `n` such that:
 * 1. The number of digits in `n` is exactly `num`.
 * 2. The sum of the digits in `n` is exactly `sum`.
 * 3. The score of `n` (sum of the squares of its digits) is maximized.
 * If there are multiple possible integers with the maximum score, return the maximum one. 
 * If no such integer exists, return an empty string.
 * 
 * Constraints:
 * 1 <= num <= 2 * 10^5
 * 1 <= sum <= 2 * 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy + Convexity Principle)
 * ==========================================
 * - Approach:
 *   1. Feasibility Check: If `num * 9 < sum`, it is impossible to form the required sum, so return `""`.
 *   2. Greedy Principle for Maximum Score: Since $f(x) = x^2$ is a convex function, to maximize the sum of squares 
 *      for a fixed sum, we must distribute the values as unevenly as possible (concentrating the sum into fewer digits 
 *      by making them as large as 9).
 *   3. Greedy Principle for Maximum Number (Lexicographical Order): To make the final number as large as possible, 
 *      we place the larger digits at the most significant positions (from left to right).
 *   4. Construction:
 *      - Fill as many leading digits with `9` as possible using `sum / 9`.
 *      - Put the remaining remainder (`sum % 9`) in the next position.
 *      - Fill the remaining trailing positions with `0` to keep the most significant digits as large as possible, 
 *        ensuring the maximum numerical value.
 * 
 * - Complexity:
 *   - Time Complexity: O(num), since we construct the result string of length `num`.
 *   - Space Complexity: O(num) to store the result string.
 */

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the maximum possible sum with 'num' digits is less than required, it's impossible
        if ((long) num * 9 < sum) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        
        // Greedily place 9s as long as the remaining sum requires it and we have digits left
        while (num > 0 && sum >= 9) {
            sb.append('9');
            sum -= 9;
            num--;
        }

        // If there's a remaining sum left, place it in the next single digit
        if (num > 0 && sum > 0) {
            sb.append((char) ('0' + sum));
            sum = 0;
            num--;
        }

        // Fill the rest of the positions with '0' to keep the highest possible value
        while (num > 0) {
            sb.append('0');
            num--;
        }

        return sb.toString();
    }
}
