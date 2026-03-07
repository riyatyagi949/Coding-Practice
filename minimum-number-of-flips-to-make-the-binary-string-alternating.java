/**
 * PROBLEM STATEMENT: 1888. Minimum Number of Flips to Make the Binary String Alternating
 * --------------------------------------------------------------------------------
 * You are given a binary string 's'. You can perform two types of operations:
 * 1. Type-1: Remove the first character and append it to the end (Cyclic Shift).
 * 2. Type-2: Flip any character ('0' -> '1' or '1' -> '0').
 * * Return the minimum number of type-2 operations needed to make 's' alternating.
 * A string is alternating if no two adjacent characters are equal (e.g., "0101" or "1010").
 * * Examples:
 * Input: s = "111000"
 * Output: 2
 * Explanation: Shift twice to get "100011", then flip two bits to get "101010".
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Sliding Window + Doubling the String
 * --------------------------------------------------------------------------------
 * 1. Handling Type-1 (Shifts): 
 * To represent all possible cyclic shifts of a string of length 'n', we can 
 * concatenate the string with itself (s + s). Any window of length 'n' in this 
 * doubled string represents one possible Type-1 configuration.
 * * 2. Target Patterns:
 * There are only two possible alternating patterns for any length 'n':
 * Pattern 1: Starts with '0' (010101...)
 * Pattern 2: Starts with '1' (101010...)
 * * 3. Sliding Window:
 * We slide a window of size 'n' over the doubled string (length 2n).
 * - As the window expands, we count the number of mismatches between the current 
 * character and both Pattern 1 and Pattern 2.
 * - Once the window exceeds size 'n', we remove the leftmost character and 
 * decrement the mismatch count if that character was a mismatch.
 * - The answer is the minimum mismatch count found for any window of size 'n'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We iterate over the doubled string (2 * n characters) exactly once.
 * - Each step (adding/removing from window) is O(1).
 * Space Complexity: O(n)
 * - We store the doubled string and the alternating patterns, each taking O(n).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String str = s + s;
        
        StringBuilder alt1 = new StringBuilder();
        StringBuilder alt2 = new StringBuilder();
        
        for(int i = 0; i < 2 * n; i++){
            if(i % 2 == 0){
                alt1.append('0');
                alt2.append('1');
            }
             else {
                alt1.append('1');
                alt2.append('0');
            }
        }
        int diff1 = 0, diff2 = 0;
        int l = 0;
        int res = Integer.MAX_VALUE;
        
        for(int r = 0; r < 2 * n; r++){
            
            if(str.charAt(r) != alt1.charAt(r)) diff1++;
            if(str.charAt(r) != alt2.charAt(r)) diff2++;
            
            if(r - l + 1 > n){
                if(str.charAt(l) != alt1.charAt(l)) diff1--;
                if(str.charAt(l) != alt2.charAt(l)) diff2--;
                l++;
            }
            if(r - l + 1 == n){
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }
        return res;
    }
}

