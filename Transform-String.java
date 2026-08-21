/*
 * ==========================================
 * PROBLEM STATEMENT: Transform String
 * ==========================================
 * Given two strings s1 and s2, find the minimum number of steps required to transform string s1 into string s2. 
 * The only allowed operation is selecting a character from s1 and inserting it at the beginning of s1. 
 * If transformation is not possible, return -1.
 * 
 * Constraints:
 * 1 <= s1.length(), s2.length() <= 10^4
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Frequency Check + Backward Two Pointers)
 * ==========================================
 * - Approach:
 *   1. Length & Character Validation: First, check if both strings have the same length. If not, transformation is impossible (-1).
 *      Next, use a frequency array to ensure both strings contain the exact same set of characters with identical frequencies. 
 *      If they don't match, return -1.
 *   2. Greedy Backward Matching: 
 *      - Start matching characters from the end of both strings using two pointers (`i` for s1 and `j` for s2).
 *      - If `s1.charAt(i) == s2.charAt(j)`, it means this character is already in its correct relative position at the suffix, 
 *        so we decrement both `i` and `j`.
 *      - If `s1.charAt(i) != s2.charAt(j)`, it means character `s2.charAt(j)` needs to be moved to the front. 
 *        We increment our `operations` counter and decrement `i` (skipping/moving characters that need to be shifted).
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where n is the length of the strings. We traverse the strings a constant number of times.
 *   - Space Complexity: O(1) auxiliary space (using a fixed-size frequency array of 256).
 */

class Solution {
    int transform(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        
        // Step 1: If lengths differ, transformation is impossible
        if (n1 != n2) {
            return -1;
        }
        
        // Step 2: Check if both strings have the exact same character frequencies
        int[] count = new int[256];
        for (int i = 0; i < n1; i++) {
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }
        
        for (int i = 0; i < 256; i++) {
            if (count[i] != 0) {
                return -1;
            }
        }
        
        // Step 3: Traverse from the end to count operations required for transformation
        int i = n1 - 1;
        int j = n2 - 1;
        int operations = 0;
        
        while (i >= 0 && j >= 0) {
            // If characters don't match, s1's character at index i must be moved to the front
            while (i >= 0 && s1.charAt(i) != s2.charAt(j)) {
                operations++;
                i--;
            }
            // If we found a matching character, move both pointers backward
            if (i >= 0) {
                i--;
                j--;
            }
        }
        return operations;
    }
}
