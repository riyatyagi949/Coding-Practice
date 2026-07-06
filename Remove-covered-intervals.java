/**
 * PROBLEM STATEMENT: 1288. Remove Covered Intervals
 * --------------------------------------------------------------------------------
 * Given an array of intervals [li, ri), remove all intervals covered by another.
 * Interval [a, b) is covered by [c, d) if and only if c <= a and b <= d.
 * Return the number of remaining intervals.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Sorting + Greedy Sweep
 * --------------------------------------------------------------------------------
 * 1. Sort the intervals primarily by start time (ascending).
 * 2. If start times are equal, sort by end time (descending). This is crucial
 * because it ensures that if multiple intervals start at the same time,
 * the longest one comes first and "covers" the others.
 * 3. Iterate through the sorted intervals and maintain the maximum end time 
 * (maxEnd) observed so far.
 * 4. For each interval [currStart, currEnd]:
 * - If currEnd <= maxEnd, it means this interval is covered by a previous
 * interval, so we increment a counter or skip it.
 * - If currEnd > maxEnd, it is not covered; update maxEnd.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N) due to the sorting step. The subsequent linear 
 * scan takes O(N).
 * Space Complexity: O(log N) or O(N) depending on the sorting implementation 
 * used by the language's library.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
             else {
                return Integer.compare(b[1], a[1]);
            }
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                maxEnd = interval[1];
            } 
            else {
                count++;
            }
        }
         return intervals.length - count;
    }
}
