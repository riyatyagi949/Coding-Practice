/**
 * PROBLEM STATEMENT: 3612. Process String with Special Operations I
 * --------------------------------------------------------------------------------
 * Given a string 's' containing lowercase English letters and special characters
 * ('*', '#', '%'), process the string based on these operations:
 * - '*': Remove the last character from the result (if not empty).
 * - '#': Duplicate the current result and append it to the end.
 * - '%': Reverse the current result.
 * - [a-z]: Append the character to the result.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Simulation
 * --------------------------------------------------------------------------------
 * 1. Use a StringBuilder to maintain the current state of the result.
 * 2. Iterate through each character of the input string 's'.
 * 3. Use conditional checks to apply the corresponding logic:
 * - Lowercase letter: Append directly.
 * - '*': Delete the last character using `deleteCharAt(sb.length() - 1)`.
 * - '#': Append a copy of the current contents using `sb.append(sb.toString())`.
 * - '%': Reverse the contents using `sb.reverse()`.
 * 4. Return the final string built by the StringBuilder.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(2^N), where N is the length of the string 's'. Operations 
 * like '#' (duplication) and '%' (reversal) have costs proportional to the current 
 * result length, which can grow exponentially in the worst case (e.g., repeating '#').
 * Space Complexity: O(2^N) to store the resulting string as it grows.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                // Append character directly
                sb.append(ch);
            } else if (ch == '*') {
                // Remove last character if result is not empty
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else if (ch == '#') {
                // Duplicate current result
                sb.append(sb.toString());
            } else if (ch == '%') {
                // Reverse current result
                sb.reverse();
            }
        }
        
        return sb.toString();
    }
}
