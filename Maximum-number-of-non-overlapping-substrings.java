/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Number of Non-Overlapping Substrings
 * ==========================================
 * Given a string `s` of lowercase letters, find the maximum number of non-empty substrings that meet:
 * 1. The substrings do not overlap.
 * 2. A substring containing character `c` must also contain all occurrences of `c`.
 * Return the maximum number of such substrings. If there are multiple solutions with the same 
 * number of substrings, return the one with the minimum total length.
 * 
 * Constraints:
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Interval Merging & Greedy Selection)
 * ==========================================
 * - Approach:
 *   1. Track First and Last Indices: Compute the first and last occurrence for each of the 26 lowercase characters.
 *   2. Build Valid Intervals: For each character, expand its interval `[l, r]` (from its first to last occurrence) 
 *      by checking all characters within the range. If any character inside has an earlier first occurrence than `l`, 
 *      then this character's interval is invalid and should be discarded. Otherwise, extend `r` to encompass the 
 *      last occurrence of any newly included character.
 *   3. Greedy Interval Scheduling: Sort the valid intervals by their end points (`r`). 
 *      Greedily select intervals that do not overlap with the previously chosen interval (`l > previousEnd`).
 * 
 * - Complexity:
 *   - Time Complexity: O(N + 26 * N), which simplifies to O(N) since the alphabet size is constant (26).
 *   - Space Complexity: O(N) to store intervals and the resultant substrings.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Step 1: Record the first and last positions of each character
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Determine valid substring intervals for each character
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) {
                continue;
            }
            
            int l = first[c];
            int r = last[c];
            boolean valid = true;

            // Expand the interval if characters inside require a wider boundary
            for (int i = l; i <= r; i++) {
                int current = s.charAt(i) - 'a';
                
                // If a character appears before 'l', this initial window is invalid
                if (first[current] < l) {
                    valid = false;
                    break;
                }
                r = Math.max(r, last[current]);
            }
            
            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Step 3: Sort intervals by their end points (greedy interval scheduling)
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();
        int previousEnd = -1;

        // Step 4: Pick non-overlapping intervals greedily
        for (int[] interval : intervals) {
            int l = interval[0];
            int r = interval[1];

            if (l > previousEnd) {
                ans.add(s.substring(l, r + 1));
                previousEnd = r;
            }
        }

        return ans;
    }
}
