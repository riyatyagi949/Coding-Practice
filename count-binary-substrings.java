/**
 * PROBLEM STATEMENT: 696. Count Binary Substrings
 * --------------------------------------------------------------------------------
 * Given a binary string s, return the number of non-empty substrings that have the 
 * same number of 0's and 1's, and all the 0's and all the 1's in these substrings 
 * are grouped consecutively.
 * * Substrings that occur multiple times are counted the number of times they occur.
 * * Example 1:
 * Input: s = "00110011"
 * Output: 6
 * Explanation: "0011", "01", "1100", "10", "0011", and "01".
 * * Example 2:
 * Input: s = "10101"
 * Output: 4
 * Explanation: "10", "01", "10", "01".
 * * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Grouping Consecutive Characters
 * --------------------------------------------------------------------------------
 * 1. Concept:
 * We can simplify the string into groups of consecutive identical characters.
 * For "00110011", the group lengths are [2, 2, 2, 2] (two 0s, two 1s, two 0s, two 1s).
 * * 2. Intersection Logic:
 * For any two adjacent groups of lengths `prev` and `curr`, the number of valid 
 * binary substrings we can form is min(prev, curr).
 * - Example: "00011" -> prev=3, curr=2. Substrings: "01", "0011". (Total 2)
 * * 3. Algorithm:
 * - Instead of storing all group lengths in an array, we only need to track the 
 * length of the previous group and the current group as we iterate through 
 * the string to save space.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the string exactly once.
 * Space Complexity: O(1)
 * - We only use a few integer variables regardless of the input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int countBinarySubstrings(String s) {
        int prevGroup = 0;
        int currGroup = 1;
        int result = 0;
        
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == s.charAt(i - 1)) {
                currGroup++;
            } 
            else {
                result += Math.min(prevGroup, currGroup);
                prevGroup = currGroup;
                currGroup = 1;
            }
        }
        result += Math.min(prevGroup, currGroup);
        
        return result;
    }
}

