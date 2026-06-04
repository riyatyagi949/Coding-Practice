/**
 * PROBLEM STATEMENT: 3751. Total Waviness of Numbers in Range I
 * --------------------------------------------------------------------------------
 * Calculate the total "waviness" of all integers in the inclusive range [num1, num2].
 * - A digit is a 'peak' if it is strictly greater than its neighbors.
 * - A digit is a 'valley' if it is strictly less than its neighbors.
 * - First and last digits cannot be peaks or valleys.
 * - Waviness is the sum of all peaks and valleys found in a number.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Iterative Digit Inspection
 * --------------------------------------------------------------------------------
 * 1. Iterate through every integer from num1 to num2.
 * 2. Convert the integer to a string or character array to access digits by index.
 * 3. For each number, iterate from index 1 to length-2 (skipping boundaries).
 * 4. Apply the condition: 
 * - Peak: (digits[i] > digits[i-1]) AND (digits[i] > digits[i+1])
 * - Valley: (digits[i] < digits[i-1]) AND (digits[i] < digits[i+1])
 * 5. Accumulate the count of these occurrences into a total sum.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(K * D), where K is the number of integers in range (num2 - num1)
 * and D is the number of digits per integer (max 5-6 digits here as per constraints).
 * Space Complexity: O(D) for storing the string representation of each number.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWavinessSum = 0;

        // Iterate through each number in the provided range
        for (int num = num1; num <= num2; num++) {
            String s = String.valueOf(num);
            int len = s.length();

            // Waviness is only possible for numbers with at least 3 digits
            if (len < 3)
              continue;

            // Check every digit except the first and last
            for (int i = 1; i < len - 1; i++) {
                int prev = s.charAt(i - 1) - '0';
                int curr = s.charAt(i) - '0';
                int next = s.charAt(i + 1) - '0';

                // Check for peak or valley condition
                if ((curr > prev && curr > next) || (curr < prev && curr < next)) {
                    totalWavinessSum++;
                }
            }
        }
        return totalWavinessSum;
    }
}
