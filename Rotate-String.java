/**
 * PROBLEM STATEMENT: 796. Rotate String
 * --------------------------------------------------------------------------------
 * Given two strings 's' and 'goal', return true if and only if 's' can become 
 * 'goal' after some number of shifts on 's'.
 * 
 * A shift on 's' consists of moving the leftmost character of 's' to the 
 * rightmost position.
 * - Example: if s = "abcde", it becomes "bcdea" after one shift.
 * 
 * Constraints:
 * - 1 <= s.length, goal.length <= 100
 * - s and goal consist of lowercase English letters.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: STRING CONCATENATION
 * --------------------------------------------------------------------------------
 * 1. Check Lengths: 
 *    If s and goal have different lengths, s can never be rotated to match goal.
 * 
 * 2. The Concatenation Trick:
 *    A string s of length N has N possible rotations. 
 *    If we concatenate s with itself (s + s), the resulting string contains 
 *    EVERY possible rotation of s as a substring.
 *    - Example: s = "abcde"
 *    - s + s = "abcdeabcde"
 *    - Rotations: "abcde", "bcdea", "cdeab", "deabc", "eabcd"
 *    - All of these are visible within "abcdeabcde".
 * 
 * 3. Search:
 *    Simply check if 'goal' exists as a substring within (s + s).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: $O(N)$
 * - String concatenation takes $O(N)$.
 * - The .contains() method (substring search) typically uses an optimized 
 *   algorithm (like Boyer-Moore or naive) which performs efficiently. 
 *   In the worst case for modern JVMs, this is $O(N)$.
 * 
 * Space Complexity: $O(N)$
 * - We create a new string (s + s) which is twice the length of s.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) 
        return false;
        return (s + s).contains(goal);
    }
}
