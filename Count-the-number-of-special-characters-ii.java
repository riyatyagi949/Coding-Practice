/**
 * PROBLEM STATEMENT: 3121. Count the Number of Special Characters II
 * --------------------------------------------------------------------------------
 * A character 'c' is "special" if:
 * 1. It appears in the string both in lowercase and uppercase.
 * 2. Every lowercase occurrence of 'c' appears BEFORE the first uppercase 
 * occurrence of 'c'.
 * Return the count of such special characters.
 * * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single Pass Tracking
 * --------------------------------------------------------------------------------
 * To satisfy the condition, we need to track two specific pieces of information 
 * for each letter of the alphabet (a-z):
 * 1. The index of the LAST occurrence of the lowercase letter.
 * 2. The index of the FIRST occurrence of the uppercase letter.
 * * We traverse the string once:
 * - Update the last seen index for every lowercase letter.
 * - Capture the first seen index for every uppercase letter (only if not 
 * previously seen).
 * * After the pass, iterate through the 26 letters:
 * A letter is special if:
 * - Both lowercase and uppercase versions exist (indices != -1).
 * - lastSmallIndex < firstCapitalIndex (guarantees all lowercase appear before 
 * the first uppercase).
 * * --------------------------------------------------------------------------------
 * TIME AND SPACE COMPLEXITY
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the string. We iterate 
 * through the string once and perform a constant-time lookup for 26 letters.
 * * Space Complexity: O(1). We use two fixed-size arrays of length 26, 
 * regardless of the input string length.
 */

import java.util.Arrays;

class Solution {
    public int numberOfSpecialChars(String word) {
        // Track indices for 26 letters
        int[] lastSmall = new int[26];
        int[] firstCapital = new int[26];

        // Initialize with -1 to indicate character not found
        Arrays.fill(lastSmall, -1);
        Arrays.fill(firstCapital, -1);

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {
                // Update the last seen position of the lowercase char
                lastSmall[ch - 'a'] = i;
            } else {
                // Store only the first seen position of the uppercase char
                if (firstCapital[ch - 'A'] == -1) {
                    firstCapital[ch - 'A'] = i;
                }
            }
        }

        int count = 0;
        // Check conditions for each letter in the alphabet
        for (int i = 0; i < 26; i++) {
            // Condition 1: Both must exist
            // Condition 2: Last lowercase must appear before the first uppercase
            if (lastSmall[i] != -1 && firstCapital[i] != -1 &&
               lastSmall[i] < firstCapital[i]) {
                count++;
            }
        }
        return count;
    }
}
