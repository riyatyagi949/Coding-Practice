// # Minimum Absolute Distance Between Mirror Pairs

// ## Problem
// Given an integer array `nums`, find the minimum distance between indices (i, j) such that:
// - i < j
// - reverse(nums[i]) == nums[j]

// Return the minimum distance, or -1 if no such pair exists.

// ## Approach
// - Use a HashMap to store number → index
// - For each element:
//   - Compute its reverse
//   - Check if reverse already exists in map
//   - Update minimum distance
// - Store current number in map

// ## Complexity
// - Time: O(n)
// - Space: O(n)

// Code ------------------------

import java.util.HashMap;

class Solution {
  private int reverseNumber(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
public int minMirrorPairDistance(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int reversed = reverseNumber(nums[i]);

            if (map.containsKey(reversed)) {
                minDist = Math.min(minDist, i - map.get(reversed));
            }

            map.put(nums[i], i);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
