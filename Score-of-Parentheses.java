/*
 * ==========================================
 * PROBLEM STATEMENT: Score of Parentheses
 * ==========================================
 * Given a balanced parentheses string `s`, return the score of the string.
 * The score of a balanced parentheses string is based on the following rule:
 * - "()" has score 1.
 * - AB has score A + B, where A and B are balanced parentheses strings.
 * - (A) has score 2 * A, where A is a balanced parentheses string.
 * 
 * Constraints:
 * 2 <= s.length <= 50
 * s consists of only '(' and ')'
 * s is a balanced parentheses string
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Stack / Depth Tracking)
 * ==========================================
 * - Approach:
 *   1. Use a Stack to keep track of the scores at each nesting level.
 *   2. Push `0` onto the stack initially to represent the score at the base level.
 *   3. Iterate through each character of the string:
 *      - If `ch == '('`, push `0` to the stack to start a new nested score level.
 *      - If `ch == ')'`:
 *        - Pop the top score (`v`) from the stack.
 *        - Peek the current score container from the stack and add to it either `1` (if `v == 0`, meaning it was an empty `()`) 
 *          or `2 * v` (if `v > 0`, meaning it was a nested `(A)`).
 *   4. At the end, the only remaining value in the stack will be the total score of the string.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the string, since each character is processed once.
 *   - Space Complexity: O(n) in the worst case for the stack.
 */

import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Base score level

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(0);
            } 
            else {
                int v = stack.pop();
                int innerScore = (v == 0) ? 1 : 2 * v;
                stack.push(stack.pop() + innerScore);
            }
        }

        return stack.pop();
    }
}
