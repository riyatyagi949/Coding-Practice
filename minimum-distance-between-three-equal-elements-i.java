/**
 * PROBLEM STATEMENT: 3740. Minimum Distance Between Three Equal Elements I
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums'. A tuple (i, j, k) of 3 distinct indices 
 * is "good" if nums[i] == nums[j] == nums[k].
 * * The distance of a good tuple is defined as:
 * dist = abs(i - j) + abs(j - k) + abs(k - i)
 * * Return the minimum possible distance of a good tuple. 
 * If no good tuples exist, return -1.
 * * Constraints:
 * - 1 <= n == nums.length <= 100
 * - 1 <= nums[i] <= n
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW OVER INDEX LISTS
 * --------------------------------------------------------------------------------
 * 1. Mathematical Observation:
 * Let the three indices in sorted order be i < j < k.
 * The distance is: (j - i) + (k - j) + (k - i)
 * Simplifying this: (j - i + k - j) + (k - i) = (k - i) + (k - i) = 2 * (k - i)
 * * 2. Goal:
 * To minimize 2 * (k - i), we simply need to find the smallest range [i, k] 
 * that contains at least three occurrences of the same number.
 * * 3. Algorithm:
 * - Group indices for each unique number in 'nums' into sorted lists.
 * - For each list, if it has at least 3 indices, check every window of size 3.
 * - The distance for a window [idx[i], idx[i+1], idx[i+2]] is 2 * (idx[i+2] - idx[i]).
 * - Track the minimum such value across all numbers.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through the array once to group indices: O(N).
 * - We iterate through the lists of indices. Since the total number of indices 
 * across all lists is N, the sliding window part is O(N).
 * Space Complexity: O(N)
 * - Storing the indices of each element requires O(N) space.
 * --------------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        
        ArrayList<Integer>[] pos = new ArrayList[n + 1];
        
        for (int i = 0; i <= n; i++) {
            pos[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            pos[nums[i]].add(i);
        }
        
        for (int val = 1; val <= n; val++) {
            ArrayList<Integer> list = pos[val];
            
            if (list.size() < 3) 
            continue;
            
            for (int i = 0; i + 2 < list.size(); i++) {
                int first = list.get(i);
                int third = list.get(i + 2);
                
                ans = Math.min(ans, 2 * (third - first));
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

