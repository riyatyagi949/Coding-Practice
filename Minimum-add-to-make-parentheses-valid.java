/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Add to Make Parentheses Valid
 * ==========================================
 * A parentheses string is valid if and only if:
 * - It is the empty string,
 * - It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * - It can be written as (A), where A is a valid string.
 * Given a parentheses string `s`, return the minimum number of moves required to make `s` valid. 
 * In one move, you can insert a parenthesis at any position of the string.
 * 
 * Constraints:
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy Count Tracking)
 * ==========================================
 * - Approach:
 *   1. We need to find how many opening brackets lack a matching closing bracket, and how many closing brackets lack a matching opening bracket.
 *   2. Maintain two counters:
 *      - `openNeeded`: tracks unmatched closing brackets `)` that require an opening bracket `(` added before them.
 *      - `closeNeeded`: tracks unmatched opening brackets `(` that require a closing bracket `)` added after them.
 *   3. Iterate through each character of the string:
 *      - If `ch == '('`, we need a closing bracket, so increment `closeNeeded`.
 *      - If `ch == ')'`:
 *        - Check if we have an unmatched opening bracket (`closeNeeded > 0`). If so, match it by decrementing `closeNeeded`.
 *        - Otherwise, this closing bracket is unmatched, so we need an opening bracket, increment `openNeeded`.
 *   4. The total minimum additions needed is the sum of `openNeeded` and `closeNeeded`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the string, since we traverse the string once.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int closeNeeded = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                closeNeeded++;
            }
            else { // ch == ')'
                if (closeNeeded > 0) {
                    closeNeeded--; // Match with an existing opening bracket
                }
                else {
                    openNeeded++;  // Unmatched closing bracket needs an opening bracket
                }
            }
        }
       return openNeeded + closeNeeded;
    }
}
