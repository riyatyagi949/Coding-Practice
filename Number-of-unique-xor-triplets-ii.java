/*
 * PROBLEM STATEMENT:
 * You are given an integer array 'nums'. 
 * A XOR triplet is defined as the XOR of three elements nums[i] ^ nums[j] ^ nums[k] 
 * where indices satisfy 0 <= i <= j <= k < n.
 * Return the number of unique XOR triplet values from all possible triplets.
 * 
 * OPTIMAL SOLUTION APPROACH:
 * 1. The array 'nums' is arbitrary (not necessarily a permutation), and its length 
 *    can be up to 1500, meaning an $O(n^3)$ approach is too slow ($1500^3 \approx 3.37 \times 10^9$ operations).
 * 2. We can optimize this by precalculating all possible pair XOR sums or using frequency maps.
 * 3. Alternatively, since values are bounded (up to 1500 or standard ranges), we can compute 
 *    the pair frequencies or use bitsets/boolean arrays to track reachable XOR values efficiently in $O(n^2)$ time.
 * 
 * Time Complexity: O(N^2) where N is the length of nums.
 * Space Complexity: O(MAX_VAL) or O(N^2) for tracking reachable XOR sums.
 */

import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] pair = new boolean[MAX];
        boolean[] ans = new boolean[MAX];

        int n = nums.length;

        for (int x : nums) {
            ans[x] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }
        for (int x = 0; x < MAX; x++) {
            if (!pair[x]) 
            continue;

            for (int v : nums) {
                ans[x ^ v] = true;
            }
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }
       return count;
    }
}
