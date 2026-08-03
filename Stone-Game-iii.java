// Problem Statement:
// Alice and Bob take turns taking 1, 2, or 3 stones from the remaining stones in a row. 
// Each player's score is the sum of the values of the stones taken. Both players play optimally.
// Return "Alice" if Alice wins, "Bob" if Bob wins, or "Tie" if they end with the same score.
// Constraints: 1 <= stoneValue.length <= 5 * 10^4, -1000 <= stoneValue[i] <= 1000.

// Optimal Solution in Java:
// Runtime: 0 ms
// Time Complexity: O(n) - where n is the number of stones, since we iterate backwards from n-1 to 0 with an inner loop of at most 3 steps.
// Space Complexity: O(n) - for the DP array of size n + 1.

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] represents the maximum score difference the current player can achieve 
        // relative to the opponent starting from stone index i to the end of the array.
        int[] dp = new int[n + 1];

        // Process from right to left (bottom-up DP)
        for (int i = n - 1; i >= 0; i--) {
            int sum = 0;
            dp[i] = Integer.MIN_VALUE;

            // A player can take 1, 2, or 3 stones
            for (int k = 0; k < 3 && i + k < n; k++) {
                sum += stoneValue[i + k];
                // The current player takes 'sum' points, and the opponent gets the optimal score dp[i + k + 1] from the remainder.
                dp[i] = Math.max(dp[i], sum - dp[i + k + 1]);
            }
        }

        // dp[0] represents Alice's score advantage over Bob from the very beginning.
        if (dp[0] > 0) 
            return "Alice";

        if (dp[0] < 0)
            return "Bob";

        return "Tie";
    }
}
