/**
 * PROBLEM: 2770. Maximum Number of Jumps to Reach the Last Index
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Dynamic Programming (Bottom-Up)
 * --------------------------------------------------------------------------------
 * 1. State Definition:
 *    Let dp[i] be the maximum number of jumps required to reach index 'i' 
 *    starting from index 0.
 * 
 * 2. Base Case:
 *    dp[0] = 0 (We start at index 0 with zero jumps).
 *    All other dp[i] are initialized to -1 (indicating they are currently unreachable).
 * 
 * 3. Transitions:
 *    For each index 'i' from 1 to n-1:
 *      We look at all previous indices 'j' (where 0 <= j < i).
 *      If index 'j' is reachable (dp[j] != -1) AND the jump condition is met:
 *         abs(nums[i] - nums[j]) <= target
 *      Then, dp[i] = max(dp[i], dp[j] + 1).
 * 
 * 4. Result:
 *    The answer is stored in dp[n-1].
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - We have two nested loops. The outer loop runs N times, and the inner 
 *   loop runs up to N times. Total operations roughly N*(N-1)/2.
 * 
 * Space Complexity: O(N)
 * - We use a single DP array of size N to store the maximum jumps for each index.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        
        // dp[i] stores the max jumps to reach index i from index 0
        int[] dp = new int[n];
        
        // Initialize with -1 to represent "unreachable"
        Arrays.fill(dp, -1);
        
        // Starting point
        dp[0] = 0;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If index j was reachable and the jump to i is valid
                if (dp[j] != -1 && Math.abs((long)nums[i] - nums[j]) <= target) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[n - 1];
    }
}
