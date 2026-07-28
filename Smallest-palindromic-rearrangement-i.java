// Problem Statement:
// You are given a palindromic string s.
// Return the lexicographically smallest palindromic permutation of s.
// Constraints: 1 <= s.length <= 10^5, consisting of lowercase English letters.

// Optimal Solution in Java:
// Runtime: 0 ms
// Time Complexity: O(n) - where n is the length of the string, for frequency counting and building the result.
// Space Complexity: O(n) - to store the frequency array and result strings.

class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        StringBuilder left = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {
             for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }
             if ((freq[i] & 1) == 1) {
                middle = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);

        if (middle != 0)
            ans.append(middle);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }
}
