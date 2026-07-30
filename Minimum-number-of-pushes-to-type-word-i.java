// Problem Statement:
// You are given a string word containing distinct lowercase English letters.
// Find the minimum number of times the keys will be pushed to type the string word after remapping keys 2 to 9.
// Constraints: 1 <= word.length <= 26, all letters are distinct.

// Optimal Solution in Java:
// Runtime: 0 ms
// Time Complexity: O(n) - where n is the length of the string, iterating through each character.
// Space Complexity: O(1) - constant auxiliary space used.

class Solution {
    public int minimumPushes(String word) {
        int ans = 0;

        // Since all letters are distinct, we assign characters greedily to minimize pushes:
        // The first 8 characters require 1 push each, the next 8 require 2 pushes each, and so on.
        for (int i = 0; i < word.length(); i++) {
            ans += (i / 8) + 1;
        }
        return ans;
    }
}
