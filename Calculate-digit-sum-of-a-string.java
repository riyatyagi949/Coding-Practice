/*
 * ==========================================
 * PROBLEM STATEMENT: Calculate Digit Sum of a String
 * ==========================================
 * You are given a string `s` consisting of digits and an integer `k`. 
 * While the length of `s` is greater than `k`:
 * 1. Divide `s` into consecutive groups of size `k` (the last group can be smaller).
 * 2. Replace each group with the string representing the sum of its digits.
 * 3. Merge the groups together to form a new string.
 * Return `s` after all rounds have been completed.
 * 
 * Constraints:
 * 1 <= s.length <= 100
 * 2 <= k <= 100
 * s consists of digits only.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Simulation)
 * ==========================================
 * - Approach:
 *   1. Directly simulate the transformation process using a `while` loop that continues as long as `s.length() > k`.
 *   2. In each round, construct the new string by iterating through `s` in increments of `k`.
 *   3. For each chunk of size `k`, sum up its individual numeric digit values.
 *   4. Append the resulting string representations of the sums to a temporary `StringBuilder`.
 *   5. Assign the new string back to `s` and repeat until `s.length() <= k`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log_k n) where `n` is the length of string `s`, since the length shrinks by a factor of `k` each round.
 *   - Space Complexity: O(n) to store intermediate strings and builder buffers.
 */

class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            for (int i = 0; i < s.length(); i += k) {
                int end = Math.min(i + k, s.length());
                String group = s.substring(i, end);
                
                int sum = 0;
                for (int j = 0; j < group.length(); j++) {
                    sum += group.charAt(j) - '0';
                }
                
                nextRound.append(sum);
            }
            
            s = nextRound.toString();
        }
        
        return s;
    }
}
