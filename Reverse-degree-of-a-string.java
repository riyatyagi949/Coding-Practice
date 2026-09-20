/*
 * ==========================================
 * PROBLEM STATEMENT: Reverse Degree of a String
 * ==========================================
 * Given a string `s`, calculate its reverse degree. 
 * The reverse degree is calculated as follows:
 * For each character, multiply its position in the reversed alphabet ('a' = 26, 'b' = 25, ..., 'z' = 1) 
 * with its position in the string (1-indexed).
 * Sum these products for all characters in the string and return the total reverse degree.
 * 
 * Constraints:
 * 1 <= s.length <= 1000
 * s contains only lowercase English letters.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Linear Scan)
 * ==========================================
 * - Approach:
 *   1. Iterate through each character of the string using 1-based indexing for string position `i` (from 1 to `s.length()`).
 *   2. For each character `c`, determine its position in the reversed alphabet: 
 *      - Normal 0-based index from 'a' is `c - 'a'`.
 *      - Reversed position formula: `26 - (c - 'a')`.
 *   3. Multiply the reversed alphabet position by the 1-indexed string position `i`.
 *   4. Accumulate the products into a total sum using a `long` to prevent any potential overflow (though results typically fit in standard integers).
 * 
 * - Complexity:
 *   - Time Complexity: O(N), where `N` is the length of the string `s`, since we traverse the string once.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public long reverseDegree(String s) {
        long totalDegree = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Calculate 1-indexed position in the string
            long stringPosition = i + 1;
            
            // Calculate position in the reversed alphabet ('a' = 26, 'z' = 1)
            long reversedAlphabetPosition = 26 - (c - 'a');
            
            // Add product to total sum
            totalDegree += reversedAlphabetPosition * stringPosition;
        }

        return totalDegree;
    }
}
