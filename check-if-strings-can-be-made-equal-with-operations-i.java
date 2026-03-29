/**
 * PROBLEM STATEMENT: 2839. Check if Strings Can be Made Equal With Operations I
 * --------------------------------------------------------------------------------
 * You are given two strings s1 and s2, both of length 4, consisting of lowercase 
 * English letters.
 * * You can apply the following operation on any of the two strings any number of times:
 * - Choose any two indices i and j such that j - i = 2, then swap the two 
 * characters at those indices in the string.
 * * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 * * Example:
 * Input: s1 = "abcd", s2 = "cdab"
 * Output: true
 * Explanation: 
 * - Swap indices 0 and 2 in s1: "abcd" -> "cbad"
 * - Swap indices 1 and 3 in s1: "cbad" -> "cdab"
 * Now s1 == s2.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: INDEX GROUPING (PARITY CHECK)
 * --------------------------------------------------------------------------------
 * The operation allows swapping characters only if the distance between them is 2.
 * In a string of length 4, this restricts swaps to specific pairs:
 * 1. Indices 0 and 2 (Even indices)
 * 2. Indices 1 and 3 (Odd indices)
 * * Characters at even indices can NEVER move to odd indices, and vice versa.
 * Therefore, s1 can be transformed into s2 if and only if:
 * - The set of characters at even indices {s1[0], s1[2]} is identical to the 
 * set of characters at even indices {s2[0], s2[2]}.
 * - The set of characters at odd indices {s1[1], s1[3]} is identical to the 
 * set of characters at odd indices {s2[1], s2[3]}.
 * * We can implement this by either:
 * - Explicitly checking the two possible configurations for each pair.
 * - Sorting the pairs and comparing them.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(1)
 * - The length of the strings is fixed at 4. Sorting arrays of size 2 or performing 
 * direct character comparisons takes constant time.
 * * Space Complexity: O(1)
 * - We only use a few character arrays of fixed size (2) to store the parity groups.
 * --------------------------------------------------------------------------------
 */
// Code----------------------------------

import java.util.Arrays;

class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Group characters by index parity for s1
        char[] s1Even = {s1.charAt(0), s1.charAt(2)};
        char[] s1Odd = {s1.charAt(1), s1.charAt(3)};
        
        // Group characters by index parity for s2
        char[] s2Even = {s2.charAt(0), s2.charAt(2)};
        char[] s2Odd = {s2.charAt(1), s2.charAt(3)};
        
        // Sort the pairs to easily compare the sets of characters
        Arrays.sort(s1Even);
        Arrays.sort(s1Odd);
        Arrays.sort(s2Even);
        Arrays.sort(s2Odd);
        
        // If both even sets and both odd sets match, the strings can be made equal
        return Arrays.equals(s1Even, s2Even) && Arrays.equals(s1Odd, s2Odd);
    }

    /**
     * Alternative Constant-Space approach without Arrays.sort:
     * * public boolean canBeEqual(String s1, String s2) {
     * return check(s1.charAt(0), s1.charAt(2), s2.charAt(0), s2.charAt(2)) &&
     * check(s1.charAt(1), s1.charAt(3), s2.charAt(1), s2.charAt(3));
     * }
     * * private boolean check(char a, char b, char c, char d) {
     * // Returns true if {a, b} == {c, d}
     * return (a == c && b == d) || (a == d && b == c);
     * }
     */
}
