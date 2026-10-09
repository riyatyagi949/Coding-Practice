/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Insertions to Balance a Parentheses String
 * ==========================================
 * Given a parentheses string `s` containing only the characters '(' and ')'. 
 * A parentheses string is balanced if and only if:
 * - Any left parenthesis '(' must have a corresponding two right parentheses '))'.
 * - Left parenthesis '(' must go before the corresponding two right parentheses '))'.
 * Return the minimum number of insertions needed to make `s` balanced.
 * 
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of '(' and ')' only.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy Count Tracking)
 * ==========================================
 * - Approach:
 *   1. We need to handle two types of deficits: unmatched opening parentheses and missing right parentheses.
 *   2. Maintain two counters:
 *      - `openNeeded`: tracks how many opening parentheses '(' are currently unmatched (each needs two closing parentheses ')' to balance).
 *      - `insertions`: tracks the total number of character insertions required.
 *   3. Iterate through the string character by character:
 *      - If we encounter an opening parenthesis `(`:
 *        - Each `(` requires two `)`s. If `openNeeded` has an odd number of expected closing parentheses from a previous single `)`, 
 *          we must insert one `)` immediately to complete that pair, and decrement `openNeeded`.
 *        - Then increment `openNeeded` to track the new open parenthesis.
 *      - If we encounter a closing parenthesis `)`:
 *        - Decrement `openNeeded`.
 *        - If `openNeeded` drops below 0, it means we have extra closing parentheses without a matching `(`. 
 *          In this case, we must insert a matching `(` (so `openNeeded = 1`), and increment our `insertions` count by 1.
 *   4. After processing the entire string, any remaining unmatched open parentheses in `openNeeded` each require two closing parentheses, 
 *      so we add `2 * openNeeded` to `insertions`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where `n` is the length of the string, since we make a single linear pass.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minInsertions(String s) {
        int openNeeded = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // If we have an odd openNeeded from before, it means a previous ')' was expecting 
                // a second ')' but encountered '(' instead. We must insert a ')' to fix it.
                if (openNeeded % 2 != 0) {
                    insertions++;
                    openNeeded--;
                }
                openNeeded += 2; // Each '(' requires two ')'s
            } else { // ch == ')'
                openNeeded--;
                // If openNeeded drops below 0, we have an extra ')' without an opening '('
                if (openNeeded < 0) {
                    insertions++;       // Insert an opening '('
                    openNeeded += 2;    // That inserted '(' now needs two ')'s (so openNeeded becomes 1)
                }
            }
        }

        // Any remaining open parentheses need two closing parentheses each
        return insertions + openNeeded;
    }
}
