/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Nesting Depth of the Parentheses
 * ==========================================
 * Given a valid parentheses string `s`, return the nesting depth of `s`. 
 * The nesting depth is the maximum number of nested parentheses.
 * 
 * Constraints:
 * 1 <= s.length <= 100
 * `s` consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
 * The parentheses expression `s` is a VPS (Valid Parentheses String).
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Single Pass / Counter Tracking)
 * ==========================================
 * - Approach:
 *   1. We can track the current nesting depth using a simple integer counter (`currentDepth`).
 *   2. Iterate through each character of the string `s`:
 *      - If the character is `'('`, increment `currentDepth` because we are entering a deeper nesting level.
 *      - Keep track of the maximum depth reached so far using `maxDepth`.
 *      - If the character is `')'`, decrement `currentDepth` as we exit a nesting level.
 *   3. Return `maxDepth` after inspecting the entire string.
 * 
 * - Complexity:
 *   - Time Complexity: O(N), where `N` is the length of the string `s`, since we traverse it once.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int maxDepth(String s) {
        int currentDepth = 0;
        int maxDepth = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            }
            else if (c == ')') {
                currentDepth--;
            }
        }
        return maxDepth;
    }
}
