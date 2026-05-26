/**
 * PROBLEM STATEMENT: 3120. Count the Number of Special Characters I
 * --------------------------------------------------------------------------------
 * Given a string 'word', a letter is considered "special" if it appears in the 
 * string in both its lowercase and uppercase forms.
 * * Goal: Return the total count of such unique special characters.
 * * Example: word = "aaAbcBC" -> 'a', 'b', and 'c' are special. Output = 3.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: HASH-BASED TRACKING
 * --------------------------------------------------------------------------------
 * 1. Use two HashSets: One to store all unique lowercase letters found, and another
 * for all unique uppercase letters found.
 * 2. Iterate through the string once to populate these sets.
 * 3. Iterate through the lowercase set. For each character, check if its 
 * uppercase counterpart exists in the uppercase set.
 * 4. Increment a counter if both forms are present.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - N is the length of the string. We perform a single pass over the string 
 * and a constant-time check against the sets (size is at most 26).
 * Space Complexity: O(1)
 * - The HashSets store at most 26 lowercase and 26 uppercase English letters, 
 * which is constant extra space regardless of input size.
 * --------------------------------------------------------------------------------
 */

import java.util.HashSet;

class Solution {
    public int numberOfSpecialChars(String word) {
        // HashSets provide O(1) average time complexity for lookups
        HashSet<Character> lower = new HashSet<>();
        HashSet<Character> upper = new HashSet<>();
        
        // Single pass: O(N)
        for (char ch : word.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                lower.add(ch);
            } else {
                upper.add(ch);
            }
        }
        
        int count = 0;
        
        // Check for presence of pairs: O(26) -> O(1)
        for (char ch : lower) {
            if (upper.contains(Character.toUpperCase(ch))) {
                count++;
            }
        }
        
        return count;
    }
}
