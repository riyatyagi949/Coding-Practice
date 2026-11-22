/*
 * ==========================================
 * PROBLEM STATEMENT: Check if All A's Appears Before All B's
 * ==========================================
 * Given a string `s` consisting of only the characters `'a'` and `'b'`, return `true`
 * if every `'a'` appears before every `'b'` in `s`, and `false` otherwise.
 *
 * Constraints:
 * 1 <= s.length <= 100
 * s[i] is either 'a' or 'b'.
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Single-Pass State Tracking)
 * ==========================================
 * - Approach:
 *   1. Maintain a boolean flag `seenB` (initialized to `false`) indicating whether `'b'`
 *      has been encountered so far.
 *   2. Scan `s` from left to right:
 *      - When `'b'` is seen, set `seenB = true`.
 *      - When `'a'` is seen while `seenB` is already `true`, an `'a'` appears after a `'b'`,
 *        so immediately return `false`.
 *   3. If the loop completes without violation, return `true` (covers all-`'a'`, all-`'b'`,
 *      and valid `'a'*m + 'b'*k` strings).
 *
 * - Complexity:
 *   - Time Complexity: O(n), where `n` is the length of `s` (single linear scan).
 *   - Space Complexity: O(1) auxiliary space (using `s.charAt(i)` avoids the `O(n)` char array copy).
 */

class Solution {
    public boolean checkString(String s) {
        boolean seenB = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'b') {
                seenB = true;
            } 
            else if (seenB) {
                return false;
            }
        }
        return true;
    }
}
