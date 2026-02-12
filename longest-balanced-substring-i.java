/**
 * PROBLEM STATEMENT: 3713. Longest Balanced Substring I
 * --------------------------------------------------------------------------------
 * You are given a string s consisting of lowercase English letters.
 * A substring of s is called balanced if all distinct characters in the substring 
 * appear the same number of times.
 * * Return the length of the longest balanced substring of s.
 * * Example 1:
 * Input: s = "abbac"
 * Output: 4
 * Explanation: "abba" is balanced because 'a' and 'b' each appear exactly 2 times.
 * * Example 2:
 * Input: s = "zzabccy"
 * Output: 4
 * Explanation: "zabc" is balanced because 'z', 'a', 'b', and 'c' appear exactly 1 time.
 * * Constraints:
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Sliding Window / Frequency Map
 * --------------------------------------------------------------------------------
 * 1. Concept:
 * A substring is balanced if for all characters 'c' present in the substring:
 * count(c) == K, where K is some constant.
 * * 2. Approach (O(N^2)):
 * Since N is up to 1000, an O(N^2) approach is efficient enough.
 * For every possible starting index 'i', we expand the substring to index 'j'.
 * At each step, we update the frequency of the new character and check the 
 * "balanced" condition.
 * * 3. Balanced Condition Check:
 * To check if all present characters have the same count efficiently:
 * - Keep track of the number of distinct characters currently in the window.
 * - Ensure that (total length of substring) % (number of distinct characters) == 0.
 * - If it is, the expected frequency is (length / distinct_count).
 * - Verify if every character in the frequency map matches this expected frequency.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2 * Σ), where Σ is the alphabet size (26). 
 * For N=1000, this is roughly 26 million operations, which passes easily.
 * Space Complexity: O(Σ) to store the frequency map for the current window.
 * --------------------------------------------------------------------------------
 */
// Code - 

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;

                int distinct = 0;
                int minFreq = Integer.MAX_VALUE;
                int maxFreq = 0;

                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        distinct++;
                        minFreq = Math.min(minFreq, freq[k]);
                        maxFreq = Math.max(maxFreq, freq[k]);
                    }
                }
                 if (minFreq == maxFreq) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
         return maxLen;
    }
}

