/*
 * ==========================================
 * PROBLEM STATEMENT: Valid Parenthesis String
 * ==========================================
 * Given a string `s` containing only three types of characters: '(', ')' and '*', 
 * return `true` if `s` is valid.
 * 
 * The rules for a valid string are:
 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * 4. '*' could be treated as a single right parenthesis ')', a single left parenthesis '(', or an empty string "".
 * 
 * Constraints:
 * 1 <= s.length <= 100
 * s[i] is '(', ')' or '*'.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy Range of Open Brackets)
 * ==========================================
 * - Approach:
 *   1. Instead of tracking a single count of open parentheses, we can track the **range** of possible 
 *      numbers of open parentheses (`minOpen` and `maxOpen`) at any given point.
 *   2. Iterate through each character of the string:
 *      - If `ch == '('`, increment both `minOpen` and `maxOpen`.
 *      - If `ch == ')'`, decrement both `minOpen` and `maxOpen`.
 *      - If `ch == '*'`, it can act as '(', ')', or empty:
 *        - `maxOpen` increases (treating '*' as '(').
 *        - `minOpen` decreases (treating '*' as ')').
 *   3. At any point, if `maxOpen < 0`, it means we have too many closing parentheses, so return `false`.
 *   4. Ensure `minOpen` never drops below 0, because we cannot have a negative number of open brackets (`minOpen = Math.max(0, minOpen)`).
 *   5. After the loop, if `minOpen == 0`, all open brackets have been validly matched, return `true`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the string, since we make a single pass.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { 
              // ch == '*'
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
            }

            if (maxOpen < 0) {
                return false; // More closing brackets than available '(' or '*'
            }

            if (minOpen < 0) {
                minOpen = 0; // minOpen cannot be negative, reset to 0
            }
        }

        return minOpen == 0;
    }
}
