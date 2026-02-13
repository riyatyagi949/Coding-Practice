/**
 * PROBLEM STATEMENT: 3714. Longest Balanced Substring II
 * --------------------------------------------------------------------------------
 * You are given a string s consisting only of the characters 'a', 'b', and 'c'.
 * A substring of s is called balanced if all distinct characters in the substring 
 * appear the same number of times.
 * * Return the length of the longest balanced substring of s.
 * * Example 1:
 * Input: s = "abbac"
 * Output: 4
 * Explanation: "abba" is balanced (a: 2, b: 2).
 * * Example 2:
 * Input: s = "aabcc"
 * Output: 3
 * Explanation: "abc" is balanced (a: 1, b: 1, c: 1).
 * * Constraints:
 * 1 <= s.length <= 10^5
 * s contains only 'a', 'b', and 'c'.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Prefix Sums and Difference Hashing
 * --------------------------------------------------------------------------------
 * A substring is balanced if:
 * 1. It contains 1 distinct character (any contiguous block of same characters).
 * 2. It contains 2 distinct characters (e.g., 'a' and 'b') with count(a) == count(b),
 * and count of the third character is 0.
 * 3. It contains 3 distinct characters (a, b, c) with count(a) == count(b) == count(c).
 * * Complexity:
 * We handle Case 1 by scanning for streaks.
 * We handle Case 2 by using a "first-occurrence" map of (countX - countY) while ensuring 
 * the third character is absent in that window.
 * We handle Case 3 using a hash map to store the first occurrence of the pair 
 * of differences: (countA - countB, countA - countC).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the string. 
 * We perform a few linear scans and map lookups.
 * Space Complexity: O(N) to store the hash map and auxiliary arrays.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.*;

class Solution {
  static class Pair {
        int d1, d2;

        Pair(int d1, int d2) {
            this.d1 = d1;
            this.d2 = d2;
        }
         @Override
        public boolean equals(Object o) {
            if (this == o) 
            return true;

            if (!(o instanceof Pair)) 
            return false;

            Pair p = (Pair) o;
            return d1 == p.d1 && d2 == p.d2;
        }
         @Override
        public int hashCode() {
            return 31 * d1 + d2;
        }
    }
    public int longestBalanced(String s) {

        char[] c = s.toCharArray();
        int n = c.length;

        int res = 0;
        int cur = 1;

        for (int i = 1; i < n; i++) {
            if (c[i] == c[i - 1]) {
                cur++;
            } 
            else {
                res = Math.max(res, cur);
                cur = 1;
            }
        }
        res = Math.max(res, cur);

        res = Math.max(res, find2(c, 'a', 'b'));
        res = Math.max(res, find2(c, 'a', 'c'));
        res = Math.max(res, find2(c, 'b', 'c'));

        int ca = 0, cb = 0, cc = 0;

        Map<Pair, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {

            if (c[i] == 'a') ca++;
            else if (c[i] == 'b') cb++;
            else cc++;

            if(ca == cb && ca == cc)
                res = Math.max(res, ca+cb+cc);

            Pair key = new Pair(ca - cb, ca - cc);

            Integer prev = mp.get(key);
            if (prev != null) {
                res = Math.max(res, i - prev);
            }
             else {
                mp.put(key, i);
            }
        }
         return res;
    }
    private int find2(char[] c, char x, char y) {

        int n = c.length;
        int max_len = 0;

        int[] first = new int[2 * n + 1];
        Arrays.fill(first, -2);

        int clear_idx = -1;
        int diff = n;

        first[diff] = -1;

        for (int i = 0; i < n; i++) {

            if (c[i] != x && c[i] != y) {

                clear_idx = i;
                diff = n;
                first[diff] = clear_idx;

            } 
            else {

                if (c[i] == x) diff++;
                else diff--;

                if (first[diff] < clear_idx) {
                    first[diff] = i;
                } 
                else {
                    max_len = Math.max(max_len, i - first[diff]);
                }
            }
        }
         return max_len;
    }
}

