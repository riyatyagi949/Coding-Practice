/**
 * PROBLEM STATEMENT: 2573. Find the String with LCP
 * --------------------------------------------------------------------------------
 * We define the LCP matrix of a string 'word' of length 'n' such that LCP[i][j] 
 * is the length of the longest common prefix between substrings word[i...n-1] 
 * and word[j...n-1].
 * * Given an n x n LCP matrix, return the lexicographically smallest string that 
 * generates this matrix. If no such string exists, return an empty string "".
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY ASSIGNMENT + DP VERIFICATION
 * --------------------------------------------------------------------------------
 * 1. Greedy Construction:
 * - To get the lexicographically smallest string, we want to use 'a' as much 
 * as possible, then 'b', etc.
 * - If LCP[i][j] > 0, it implies word[i] must be equal to word[j].
 * - We iterate through the string. For each unassigned index i, we assign the 
 * smallest available character ('a' through 'z').
 * - Once word[i] is assigned, we immediately assign the same character to 
 * all indices j where LCP[i][j] > 0.
 * * 2. Validation (Crucial Step):
 * - A greedy assignment based only on LCP[i][j] > 0 might produce a string 
 * that doesn't actually satisfy the specific values in the LCP matrix.
 * - We must verify the constructed string against the LCP matrix using the 
 * standard LCP property:
 * - If word[i] == word[j]: LCP[i][j] = 1 + LCP[i+1][j+1]
 * - If word[i] != word[j]: LCP[i][j] = 0
 * - We also check boundary conditions (e.g., LCP values shouldn't exceed 
 * remaining string length).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n^2)
 * - Greedy construction takes O(n^2) to fill the character array.
 * - Validation involves a nested loop over the n x n matrix, taking O(n^2).
 * Space Complexity: O(n)
 * - We store the resulting string in a character array of size n.
 * --------------------------------------------------------------------------------
 */

// Code ------------------

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        char currChar = 'a';

        for (int i = 0; i < n; i++) {
            if (s[i] != '\0')
              continue; 
            
            if (currChar > 'z') 
              return "";
            
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    s[j] = currChar;
                }
            }
            currChar++;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expectedLCP;
                
                if (s[i] == s[j]) {
                    expectedLCP = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] + 1 : 1;
                } else {
                    expectedLCP = 0;
                }

                if (lcp[i][j] != expectedLCP) {
                    return ""; 
                }
            }
        }

        return new String(s);
    }
}
