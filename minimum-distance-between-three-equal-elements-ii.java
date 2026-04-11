/**
 * PROBLEM STATEMENT: 3741. Minimum Distance Between Three Equal Elements II
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums'. A tuple (i, j, k) of 3 distinct indices 
 * is "good" if nums[i] == nums[j] == nums[k].
 * * The distance of a good tuple is defined as:
 * dist = abs(i - j) + abs(j - k) + abs(k - i)
 * * Return the minimum possible distance of a good tuple. 
 * If no good tuples exist, return -1.
 * * Constraints:
 * - 1 <= n == nums.length <= 10^5
 * - 1 <= nums[i] <= n
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: ONE-PASS WITH STATE TRACKING
 * --------------------------------------------------------------------------------
 * 1. Mathematical Simplification:
 * For three indices in sorted order i < j < k where nums[i] = nums[j] = nums[k]:
 * Distance = (j - i) + (k - j) + (k - i)
 * = (k - i) + (k - i) = 2 * (k - i)
 * * 2. Goal:
 * To minimize 2 * (k - i), we need the smallest possible index range [i, k] that 
 * contains at least three occurrences of the same number. 
 * * 3. Strategy for 10^5 Constraints:
 * Instead of storing all indices for every number (which could be memory intensive),
 * we only need the two most recent indices seen for any given number to calculate 
 * the distance when the third one appears.
 * - Maintain two arrays: 'first' and 'second'.
 * - 'first[val]' stores the index of the element seen two occurrences ago.
 * - 'second[val]' stores the index of the element seen one occurrence ago.
 * - When we see 'val' at current index 'i':
 * - If 'first[val]' is not empty, we have a triplet.
 * - Distance = 2 * (i - first[val]).
 * - Update: first[val] = second[val], second[val] = i.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array exactly once.
 * Space Complexity: O(N)
 * - We use two arrays of size N+1 to track the last two indices for each possible value.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        
        int[] first = new int[n + 1];
        int[] second = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            first[i] = -1;
            second[i] = -1;
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];

            if (first[val] != -1 && second[val] != -1) {
                int dist = 2 * (i - first[val]);
                ans = Math.min(ans, dist);
            }
            first[val] = second[val];
            second[val] = i;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

