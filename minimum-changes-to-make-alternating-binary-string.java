/**
 * PROBLEM STATEMENT: 1758. Minimum Changes To Make Alternating Binary String
 * --------------------------------------------------------------------------------
 * You are given a string 's' consisting only of the characters '0' and '1'. 
 * In one operation, you can change any '0' to '1' or vice versa.
 * * A string is "alternating" if no two adjacent characters are equal (e.g., "0101").
 * * Task: Return the minimum number of operations needed to make 's' alternating.
 * * Example 1:
 * Input: s = "0100"
 * Output: 1
 * Explanation: Change the last '0' to '1' to get "0101".
 * * Example 2:
 * Input: s = "1111"
 * Output: 2
 * Explanation: Reach "0101" or "1010" in 2 operations.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single Pass Greedy Counting
 * --------------------------------------------------------------------------------
 * There are only two possible alternating strings for any given length:
 * Pattern A: Starts with '0' (010101...)
 * Pattern B: Starts with '1' (101010...)
 * * Observation:
 * If a character at index 'i' needs to be changed to match Pattern A, it 
 * automatically matches Pattern B (and vice versa).
 * Therefore: Ops(Pattern B) = Total Length - Ops(Pattern A).
 * * Algorithm:
 * 1. Iterate through the string.
 * 2. Compare s[i] with the expected character for a "start with 0" pattern.
 * - At even indices, we expect '0'.
 * - At odd indices, we expect '1'.
 * 3. Count how many characters do NOT match this pattern (let's call this 'count0').
 * 4. The operations for the "start with 1" pattern would be (n - count0).
 * 5. Return the minimum of 'count0' and (n - count0).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the string exactly once.
 * Space Complexity: O(1)
 * - We only use a few integer variables regardless of the input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int minOperations(String s) {
        int countStartWith0 = 0;
        int countStartWith1 = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char expected0 = (i % 2 == 0) ? '0' : '1';
            char expected1 = (i % 2 == 0) ? '1' : '0';
            
            if (s.charAt(i) != expected0) {
                countStartWith0++;
            }
             if (s.charAt(i) != expected1) {
                countStartWith1++;
            }
        }
        return Math.min(countStartWith0, countStartWith1);
    }
}

