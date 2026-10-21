/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Operations to Sort a String
 * ==========================================
 * Given a string `s` consisting of lowercase English letters. 
 * In one operation, you can select any proper substring of `s` (i.e., not the entire string) 
 * and sort it in non-descending alphabetical order. 
 * Return the minimum number of operations required to make `s` sorted in non-descending order, 
 * or return -1 if it is not possible.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Case Analysis)
 * ==========================================
 * - Approach:
 *   1. Check if the string is already sorted. If yes, return 0 operations.
 *   2. If the length of the string is 2 and it's not sorted, return -1 since a proper substring 
 *      has length 1 and cannot change the relative order.
 *   3. Find the minimum (`mn`) and maximum (`mx`) characters in the string.
 *   4. If the first character is `mn` or the last character is `mx`, we can achieve it in 1 operation 
 *      by sorting the remaining suffix or prefix.
 *   5. If any character in the proper prefix/suffix region (`s[1..n-2]`) is equal to `mn` or `mx`, 
 *      it can be done in 2 operations.
 *   6. Otherwise, it requires 3 operations.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) to traverse the string and find character bounds.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int minOperations(String s) {
        boolean isSorted = true;
        char[] cs = s.toCharArray();
        int n = cs.length;
        char mn = cs[0], mx = cs[0];

        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }

        if (isSorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }

        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }

        return 3;
    }
}
