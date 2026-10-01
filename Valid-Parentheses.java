/*
 * ==========================================
 * PROBLEM STATEMENT: Valid Parentheses
 * ==========================================
 * Given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * 
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Stack Data Structure)
 * ==========================================
 * - Approach:
 *   1. Use a Stack to keep track of opening brackets encountered during the traversal.
 *   2. Iterate through each character of the string:
 *      - If the character is an opening bracket ('(', '{', '['), push it onto the stack.
 *      - If the character is a closing bracket (')', '}', ']'):
 *        - Check if the stack is empty. If it is, there is no matching opening bracket, return false.
 *        - Pop the top element from the stack and verify if it matches the corresponding opening bracket type. 
 *          If it doesn't match, return false.
 *   3. After the loop completes, check if the stack is empty. If it is empty, all opening brackets 
 *      were correctly matched and closed, return true. Otherwise, return false.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the string, since we traverse the string once.
 *   - Space Complexity: O(n) in the worst case (e.g., all opening brackets) to store elements in the stack.
 */

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If it's an opening bracket, push to stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // If it's a closing bracket and stack is empty, it's invalid
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check for matching pair
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If stack is empty, all brackets matched successfully
        return stack.isEmpty();
    }
}
