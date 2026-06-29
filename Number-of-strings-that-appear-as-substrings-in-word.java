/**
 * PROBLEM STATEMENT:
 * Given an array of strings 'patterns' and a string 'word', 
 * count how many strings in 'patterns' exist as a substring within 'word'.
 *
 * OPTIMAL SOLUTION:
 * Use a loop to iterate through the array and the String.contains() 
 * method provided by Java to check for the existence of the substring.
 *
 * COMPLEXITY:
 * - Time: O(P * L * W), where P = patterns.length, L = max pattern length, W = word length.
 * - Space: O(1)
 */

public class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        // Check every pattern to see if it is contained in the target word
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                count++;
            }
        }
        return count;
    }
}
