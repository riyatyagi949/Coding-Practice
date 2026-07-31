// Problem Statement:
// You are given a string word containing lowercase English letters.
// Find the minimum number of times the keys will be pushed to type the string word after remapping keys 2 to 9.
// Constraints: 1 <= word.length <= 10^5, word consists of lowercase English letters.

// Optimal Solution in Java:
// Runtime: 1 ms
// Time Complexity: O(N + Σ log Σ) where N is the length of the string and Σ = 26 (alphabet size), sorting frequencies takes O(26 log 26).
// Space Complexity: O(1) - constant space for the 26-element frequency array.

import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Frequency array to store the count of each lowercase English letter
        int[] freq = new int[26];

        // Count occurrences of each character in the word
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }
        
        // Sort the frequencies in ascending order so that the highest frequencies are at the end
        Arrays.sort(freq);

        int ans = 0;
        int push = 1;
        int count = 0;

        // Iterate from the highest frequency to the lowest
        for (int i = 25; i >= 0; i--) {
            // Stop if there are no more characters with non-zero frequency
            if (freq[i] == 0) 
                break;

            // Multiply the character frequency by the number of pushes required
            ans += freq[i] * push;
            count++;

            // Every 8 assigned characters, we must move to keys requiring an additional push
            if (count % 8 == 0) {
                push++;
            }
        }
        
        return ans;
    }
}
