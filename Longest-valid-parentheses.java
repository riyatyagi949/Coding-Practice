/*
 * ==========================================
 * PROBLEM STATEMENT: Longest Valid Parentheses
 * ==========================================
 * Given a string containing just the characters '(' and ')', return the length of the longest 
 * valid (well-formed) parentheses substring.
 * 
 * Constraints:
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Stack-based)
 * ==========================================
 * - Approach:
 *   1. Use a stack to store the indices of characters.
 *   2. Push `-1` to the stack as a base index for matching valid substrings.
 *   3. Iterate through the string character by character:
 *      - If the character is '(', push its index onto the stack.
 *      - If the character is ')', pop the top element from the stack.
 *      - If the stack becomes empty after popping, push the current index `i` onto the stack as the new base.
 *      - If the stack is not empty, the length of the current valid substring is `i - stack.peek()`. 
 *        Update the maximum length found so far.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the string, since each character is pushed and popped at most once.
 *   - Space Complexity: O(n) in the worst case to store indices in the stack.
 */

import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } 
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
