// Problem Statement:
// You are given an integer array nums. Two players take turns picking a number from either end of the array.
// Player 1 starts first. Both players play optimally. 
// Return true if Player 1 can win the game (or tie, as a tie counts as a win for Player 1), otherwise return false.
// Constraints: 1 <= nums.length <= 20, 0 <= nums[i] <= 10^7.

// Optimal Solution in Java:
// Runtime: 0 ms
// Time Complexity: O(n^2) - due to filling the 2D DP table of size n * n.
// Space Complexity: O(n^2) - to store the DP table.

class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Iterate over all possible lengths of the subarray, from 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
              
                int pickLeft = nums[i] - dp[i + 1][j];
                int pickRight = nums[j] - dp[i][j - 1];

                dp[i][j] = Math.max(pickLeft, pickRight);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
