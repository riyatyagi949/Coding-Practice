/*
 * ==========================================
 * PROBLEM STATEMENT: Generate Parentheses
 * ==========================================
 * Given `n` pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * Constraints:
 * 1 <= n <= 8
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Backtracking / Recursion)
 * ==========================================
 * - Approach:
 *   1. Use a backtracking approach to build strings of length `2 * n` character by character.
 *   2. Maintain the count of opening (`open`) and closing (`close`) brackets used so far.
 *   3. Conditions for valid placement:
 *      - We can add an opening bracket `'('` as long as `open < n`.
 *      - We can add a closing bracket `')'` as long as `close < open` (to ensure it matches an existing open bracket).
 *   4. Base Case: When the current string length reaches `2 * n` (i.e., `open == n` and `close == n`), 
 *      add the valid combination to the result list.
 * 
 * - Complexity:
 *   - Time Complexity: O(4^n / \sqrt{n}) which represents the Catalan number complexity for generating valid parentheses.
 *   - Space Complexity: O(n) for the recursion stack and temporary string builder storage.
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // Base case: if the current combination is complete
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // We can add an opening bracket if we haven't used all 'n' opening brackets
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // We can add a closing bracket if there are unmatched opening brackets
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}
