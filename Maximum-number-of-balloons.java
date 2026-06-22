/**
 * PROBLEM STATEMENT: 1189. Maximum Number of Balloons
 * --------------------------------------------------------------------------------
 * Given a string 'text', use its characters to form as many instances of the word 
 * "balloon" as possible. Each character can be used at most once.
 *
 * * Logic:
 * The word "balloon" requires:
 * 1 'b', 1 'a', 2 'l', 2 'o', 1 'n'.
 * We need to count the frequency of these characters in the input 'text' and 
 * determine how many full sets can be constructed.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Frequency Counting
 * --------------------------------------------------------------------------------
 * 1. Count the occurrences of 'b', 'a', 'l', 'o', and 'n' in the input string.
 * 2. Since 'l' and 'o' are needed twice, divide their respective counts by 2.
 * 3. The maximum number of "balloon" instances is the minimum of the counts 
 * of these required character "units".
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the string 'text'. 
 * We traverse the string once to populate the frequency counts.
 * Space Complexity: O(1), as the frequency array/map size is fixed (constant size 
 * 26 for English lowercase letters).
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int maxNumberOfBalloons(String text) {
        // Frequency array for lowercase English letters
        int[] counts = new int[26];
        
        // Count occurrences of each character
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Retrieve counts for characters in "balloon"
        int b = counts['b' - 'a'];
        int a = counts['a' - 'a'];
        int l = counts['l' - 'a'];
        int o = counts['o' - 'a'];
        int n = counts['n' - 'a'];
        
        // 'l' and 'o' are needed twice, so integer division by 2
        l /= 2;
        o /= 2;
        
        // The limiting character determines the maximum number of words
        int minCount = Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
        
        return minCount;
    }
}
