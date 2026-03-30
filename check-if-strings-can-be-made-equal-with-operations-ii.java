/**
 * PROBLEM STATEMENT: 2840. Check if Strings Can be Made Equal With Operations II
 * --------------------------------------------------------------------------------
 * You are given two strings s1 and s2 of length n. 
 * Operation: Choose any two indices i and j such that i < j and (j - i) is EVEN.
 * Swap the characters at those indices.
 * * Return true if you can make s1 equal to s2 using any number of operations.
 * * Constraints:
 * - n == s1.length == s2.length
 * - 1 <= n <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: PARITY-BASED FREQUENCY COUNTING
 * --------------------------------------------------------------------------------
 * 1. The Key Insight: 
 * Because (j - i) must be even, a character at an even index can only be 
 * swapped with another character at an even index. Similarly, odd-indexed 
 * characters stay in odd-indexed positions.
 * * 2. The Logic:
 * If we can freely swap any two even-indexed characters, we can arrange the 
 * even-indexed characters of s1 into any permutation. Therefore, s1 can 
 * become s2 IF AND ONLY IF:
 * - The set of characters at even indices in s1 is identical to those in s2.
 * - The set of characters at odd indices in s1 is identical to those in s2.
 * * 3. Implementation:
 * Use frequency arrays (size 26 for lowercase English letters) to count 
 * characters at even and odd positions separately for both strings. 
 * If the counts match for both parities, return true.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: $O(N)$
 * - We iterate through the strings exactly once ($O(N)$).
 * - We iterate through the frequency arrays of size 26 ($O(26)$ or $O(1)$).
 * * Space Complexity: $O(1)$
 * - We use a fixed amount of extra space (two arrays of size 26), 
 * regardless of the input string length $N$.
 * --------------------------------------------------------------------------------
 */

// Optimal Solution in Java ----------------------

class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[] even = new int[26];
        int[] odd = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                even[s1.charAt(i) - 'a']++;
                even[s2.charAt(i) - 'a']--;
            }
             else {
                odd[s1.charAt(i) - 'a']++;
                odd[s2.charAt(i) - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (even[i] != 0 || odd[i] != 0) {
                return false;
            }
        }

        return true;
    }
}

