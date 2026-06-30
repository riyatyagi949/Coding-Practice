/**
 * PROBLEM STATEMENT:
 * Given a string 's' containing only characters 'a', 'b', and 'c', 
 * return the number of substrings that contain at least one occurrence of 'a', 'b', and 'c'.
 *
 * OPTIMAL SOLUTION:
 * Use the Sliding Window technique to maintain a valid window [left, right] 
 * that contains all three characters. 
 *
 * Logic:
 * 1. Expand the 'right' pointer to include characters in the window.
 * 2. Whenever the window contains at least one 'a', 'b', and 'c', any substring 
 * starting at 'left' and ending at 'right' or beyond (up to the end of the string) 
 * is valid. This adds (s.length() - right) to our count.
 * 3. Shrink the 'left' pointer to find the next smallest valid window and repeat.
 *
 * COMPLEXITY:
 * - Time: O(N), where N is the length of the string.
 * - Space: O(1), as the frequency array size is fixed (size 3).
 */

public class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3]; // To store frequency of 'a', 'b', 'c'
        int res = 0;
        int left = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            // Add current character to frequency array
            count[s.charAt(right) - 'a']++;

            // While window is valid, move left pointer to count all valid substrings
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // All substrings starting from 'left' ending from 'right' to 'n-1' are valid
                res += (n - right);
                
                // Remove character at 'left' and shrink window
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return res;
    }
}
