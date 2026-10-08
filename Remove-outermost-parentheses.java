/*
 * ==========================================
 * PROBLEM STATEMENT: Remove Outermost Parentheses
 * ==========================================
 * A valid parentheses string is either empty `""`, `"(" + A + ")"`, or `A + B`, where `A` and `B` are valid 
 * parentheses strings, and `+` represents string concatenation.
 * For example, `""`, `"()()"`, `"(())()"`, and `"(()(()))"` are all valid parentheses strings.
 * A valid parentheses string `s` is primitive if it is nonempty, and there does not exist a way to split it 
 * into `s = A + B`, with non-empty valid parentheses strings `A` and `B`.
 * Given a valid parentheses string `s`, consider its primitive decomposition: `s = P_1 + P_2 + ... + P_k`, 
 * where `P_i` are primitive valid parentheses strings.
 * Return `s` after removing the outermost parentheses of every primitive string in the primitive decomposition of `s`.
 * 
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either '(' or ')'
 * `s` is a valid parentheses string.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Depth Tracking / StringBuilder)
 * ==========================================
 * - Approach:
 *   1. We can identify the outer parentheses of primitive components by tracking the nesting depth.
 *   2. Maintain an `openCount` variable to keep track of the current depth of open parentheses.
 *   3. Iterate through each character of the string:
 *      - If `ch == '('`:
 *        - If `openCount > 0`, it means this opening parenthesis is *not* the outermost one of the current primitive string, 
 *          so we append it to our result.
 *        - Increment `openCount`.
 *      - If `ch == ')'`:
 *        - Decrement `openCount` first.
 *        - If `openCount > 0`, it means this closing parenthesis is *not* the outermost one of the current primitive string, 
 *          so we append it to our result.
 *   4. Return the constructed result string.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where `n` is the length of the string, since we traverse the string once.
 *   - Space Complexity: O(n) for the `StringBuilder` storage used to build the result.
 */

class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int openCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (openCount > 0) {
                    sb.append(ch);
                }
                openCount++;
            } 
            else { // ch == ')'
                openCount--;
                if (openCount > 0) {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }
}
