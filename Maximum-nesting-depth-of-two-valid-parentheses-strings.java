/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Nesting Depth of Two Valid Parentheses Strings
 * ==========================================
 * Given a valid parentheses string `seq`, split it into two disjoint subsequences `A` and `B` 
 * such that both are valid parentheses strings (VPS), and `max(depth(A), depth(B))` is minimized.
 * Return an array of integers representing the assignment (0 for A, 1 for B).
 * 
 * Constraints:
 * 1 <= seq.length <= 10000
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy Alternation / Depth Parity)
 * ==========================================
 * - Approach:
 *   1. To minimize the maximum depth of the two resulting subsequences, we can balance the nesting 
 *      levels between `A` and `B`.
 *   2. We can track the current nesting depth as we iterate through the sequence. Alternatively, we can 
 *      use the parity of the current depth or distribute opening brackets evenly:
 *      - Assign opening brackets `(` alternately to `A` and `B` (or based on current depth parity) 
 *        to keep their individual nesting depths as low as possible.
 *      - For closing brackets `)`, assign them to the same subsequence that took the corresponding 
 *        opening bracket.
 *   3. A simple and elegant way to achieve this is by checking the nesting depth:
 *      - If we are at an even depth (0-indexed or 1-indexed), assign to one group; if odd, assign to the other.
 * 
 * - Complexity:
 *   - Time Complexity: O(N), where `N` is the length of `seq`, since we do a single pass.
 *   - Space Complexity: O(N) to store the result array.
 */

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);

            if (c == '(') {
                depth++;
                // Distribute opening brackets based on depth parity
                result[i] = depth % 2;
            } 
            else {
                // Closing brackets take the same group as their corresponding opening bracket
                result[i] = depth % 2;
                depth--;
            }
        }

        return result;
    }
}
