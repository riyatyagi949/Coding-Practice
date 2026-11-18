/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Number of Changes to Make Binary String Beautiful
 * ==========================================
 * You are given a 0-indexed binary string `s` having an even length.
 * A string is beautiful if it can be partitioned into one or more substrings such that:
 * - Each substring has an even length.
 * - Each substring contains only '1's or only '0's.
 * Return the minimum number of changes required to make `s` beautiful.
 * 
 * Constraints:
 * 2 <= s.length <= 10^5
 * `s` has an even length.
 * s[i] is either '0' or '1'.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy Pairwise Comparison)
 * ==========================================
 * - Approach:
 *   1. Since every substring in the partition must have an even length and contain identical characters, 
 *      we can independently partition the entire string into adjacent disjoint pairs of characters (i.e., `(s[0], s[1])`, `(s[2], s[3])`, etc.).
 *   2. For each pair at indices `(i, i+1)`, if both characters are already equal (`s.charAt(i) == s.charAt(i + 1)`), 
 *      zero changes are required for that pair.
 *   3. If they differ, exactly one change is required to make them match (either change `s[i+1]` to match `s[i]` or vice versa).
 *   4. Sum up the changes across all non-overlapping pairs and return the total.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) since we inspect each character in the string once.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minChanges(String s) {
        int changes = 0;

        // Check characters in adjacent pairs of size 2
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        return changes;
    }
}
