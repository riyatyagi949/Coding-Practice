/**
 * PROBLEM STATEMENT: 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
 * --------------------------------------------------------------------------------
 * A decimal number is called "deci-binary" if each of its digits is either 0 or 1 
 * (without leading zeros). For example, 101 and 1100 are deci-binary.
 * * Given a string 'n' representing a positive decimal integer, return the minimum 
 * number of positive deci-binary numbers needed so that they sum up to 'n'.
 *
 * * Example:
 * Input: n = "32"
 * Output: 3
 * Explanation: 10 + 11 + 11 = 32. 
 * Note that we need at least 3 numbers because the first digit '3' can only be 
 * formed by summing at most one '1' from each deci-binary number in that position.
 *
 * * Constraints:
 * 1 <= n.length <= 10^5
 * n consists only of digits and has no leading zeros.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Maximum Digit Logic
 * --------------------------------------------------------------------------------
 * 1. The Core Observation:
 * To form any digit 'd' at any position in the number 'n', we need at least 'd' 
 * deci-binary numbers. For example, if a position has the digit '9', we need nine 
 * '1's at that specific decimal place. Since a deci-binary number can contribute 
 * at most a '1' to any position, the minimum number of deci-binary numbers required 
 * is equal to the largest digit present in the string.
 *
 * 2. Why this works:
 * If the max digit is 7, we can always construct 7 deci-binary numbers that sum 
 * to 'n' by greedily placing '1's in positions where the remaining value is 
 * still positive.
 *
 * 3. Early Exit Optimization:
 * Since the digits are only 0-9, if we encounter the digit '9', we can immediately 
 * return 9 as the answer, as it is impossible to have a higher requirement.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through the string 'n' once. N is the number of digits in the string.
 * - In the best case (if '9' is at the start), it takes O(1).
 *
 * Space Complexity: O(1)
 * - We only use a single integer variable to track the maximum digit found.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

class Solution {
   public int minPartitions(String n) {
        int maxDigit = 0;
        
        for (int i = 0; i < n.length(); i++) {
            int currentDigit = n.charAt(i) - '0';
            
            if (currentDigit > maxDigit) {
                maxDigit = currentDigit;
            }
                if (maxDigit == 9) {
                return 9;
            }
        }
         return maxDigit;
    }
}
