/**
 * Problem Statement: 3474. Lexicographically Smallest Generated String
 * --------------------------------------------------------------------------------
 * Given str1 (length n) and str2 (length m), construct a string 'word' of length 
 * n + m - 1. 
 * - If str1[i] == 'T', word[i...i+m-1] must equal str2.
 * - If str1[i] == 'F', word[i...i+m-1] must NOT equal str2.
 * * Goal: Return the lexicographically smallest 'word' that satisfies these.
 * Return "" if impossible.
 * --------------------------------------------------------------------------------
 * Optimal Solution: Greedy with Rightmost Change
 * --------------------------------------------------------------------------------
 * 1. Initialize a char array 'word' of size n+m-1 with a placeholder.
 * 2. Process all 'T' constraints:
 * - For each 'T' at str1[i], fill word[i...i+m-1] with str2.
 * - If a conflict occurs (trying to overwrite a fixed char with a different one), return "".
 * 3. Fill all remaining placeholder spots with 'a' and mark them as 'changeable'.
 * 4. Process all 'F' constraints from left to right:
 * - Check if word[i...i+m-1] equals str2.
 * - If it does, we must break the match. To keep the string lexicographically 
 * smallest, we look for the rightmost 'changeable' index 'k' in the current 
 * window [i, i+m-1].
 * - Change word[k] to 'b'. Since the window matched str2 and word[k] was 'a',
 * str2[k-i] must have been 'a'. Changing 'a' to 'b' guaranteed breaks the match.
 * - If no index in the window is 'changeable', return "".
 * --------------------------------------------------------------------------------
 */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * M), where N is the length of str1 and M is the length of str2.
 * - Initial 'T' pass: O(N * M) in the worst case (overlapping 'T's).
 * - Initial fill: O(N + M).
 * - 'F' pass: For each index in str1, we check a substring of length M and 
 * potentially scan M characters to find a changeable one. O(N * M).
 * Given N=10^4 and M=500, N*M = 5*10^6, which comfortably fits in a 1s limit.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N + M)
 * - We store the 'word' array and a 'fixed' boolean array, both of size N+M-1.
 */
// Optimal Solution in Java -------------------------------


class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int N = n + m - 1;

        char[] word = new char[N];
        boolean[] canChange = new boolean[N];

        for (int i = 0; i < N; i++) {
            word[i] = '$';
        }
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                int idx = i;
                for (int j = 0; j < m; j++) {
                    if (word[idx] != '$' && word[idx] != str2.charAt(j)) {
                        return "";
                    }
                    word[idx] = str2.charAt(j);
                    idx++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (word[i] == '$') {
                word[i] = 'a';
                canChange[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {

                if (isSame(word, str2, i, m)) {
                    boolean changed = false;

                    for (int k = i + m - 1; k >= i; k--) {
                        if (canChange[k]) {
                            word[k] = 'b';  
                            canChange[k] = false;
                            changed = true;
                            break;
                        }
                    }
                     if (!changed)
                      return "";
                }
            }
        }
        return new String(word);
    }
    private boolean isSame(char[] word, String str2, int i, int m) {
        for (int j = 0; j < m; j++) {
            if (word[i] != str2.charAt(j))
             return false;
            i++;
        }
        return true;
    }
}
