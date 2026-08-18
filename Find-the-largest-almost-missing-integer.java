/*
 * ==========================================
 * PROBLEM STATEMENT: Find the Largest Almost Missing Integer
 * ==========================================
 * You are given an integer array nums and an integer k.
 * An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums.
 * Return the largest almost missing integer from nums. If no such integer exists, return -1.
 * 
 * Constraints:
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 50
 * 1 <= k <= nums.length
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Frequency Counting over Subarrays)
 * ==========================================
 * - Approach:
 *   1. We iterate through all contiguous subarrays of length `k` (starting from index `i = 0` to `nums.length - k`).
 *   2. For each subarray, we keep track of which numbers are present using a boolean array.
 *   3. We maintain a global `count` array of size 51 (since `nums[i] <= 50`) to count how many distinct subarrays each number appears in.
 *   4. After processing all subarrays, we iterate backwards from 50 down to 0 to find the largest number whose occurrence count is exactly 1.
 *   5. If found, return that number; otherwise, return -1.
 * 
 * - Complexity:
 *   - Time Complexity: O((N - k + 1) * k), which is bounded by O(N^2) (since N <= 50, this executes instantaneously).
 *   - Space Complexity: O(1) auxiliary space (using fixed-size arrays of size 51).
 */

class Solution {
    public int largestInteger(int[] nums, int k) {
        // Frequency array to count how many subarrays each number appears in (values 0 to 50)
        int[] count = new int[51];

        // Iterate over all possible starting positions of subarrays of size k
        for (int i = 0; i <= nums.length - k; i++) {
            boolean[] present = new boolean[51];

            // Mark numbers present in the current subarray of size k
            for (int j = i; j < i + k; j++) {
                present[nums[j]] = true;
            }

            // Increment count for each unique number found in this subarray
            for (int x = 0; x <= 50; x++) {
                if (present[x]) {
                    count[x]++;
                }
            }
        }

        // Search from the largest possible number down to 0 for an almost missing integer (count == 1)
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        // If no such integer exists, return -1
        return -1;
    }
}
