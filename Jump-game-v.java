/**
 * PROBLEM STATEMENT: 1340. Jump Game V
 * --------------------------------------------------------------------------------
 * Given an array of integers and a jump distance 'd', determine the maximum number 
 * of indices you can visit, following the rules:
 * - Jump range: [i-d, i+d].
 * - Constraint: You can only jump to a smaller value (arr[i] > arr[j]).
 * - Obstacle: All elements between index i and j must be smaller than arr[i].
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DFS + Memoization
 * --------------------------------------------------------------------------------
 * 1. Logic:
 * This is a classic DAG (Directed Acyclic Graph) problem. Since we can only jump 
 * to smaller values, there are no cycles. 
 * 2. Memoization:
 * Create a 'dp' array where dp[i] stores the maximum jumps starting from index i.
 * This avoids redundant calculations, turning an exponential problem into O(N * D).
 * 3. Search:
 * For each index, explore all valid jumps to the left and right. Stop early if
 * a neighbor is >= current index value (as the jump rule is broken).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * D)
 * - We compute each index exactly once. For each index, we check up to 'd' 
 *   elements in both directions.
 * Space Complexity: O(N)
 * - O(N) for the 'dp' array and the recursion stack.
 * --------------------------------------------------------------------------------
 */
// Code ----------

class Solution {
     public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(arr, d, i, dp));
        }
        return ans;
    }
    private int dfs(int[] arr, int d, int i, int[] dp) {

        if (dp[i] != 0) {
            return dp[i];
        }

        int max = 1;

        for (int j = i + 1; j <= Math.min(i + d, arr.length - 1); j++) {

            if (arr[j] >= arr[i]) {
                break;
            }

            max = Math.max(max, 1 + dfs(arr, d, j, dp));
        }

        for (int j = i - 1; j >= Math.max(i - d, 0); j--) {

            if (arr[j] >= arr[i]) {
                break;
            }

            max = Math.max(max, 1 + dfs(arr, d, j, dp));
        }

        dp[i] = max;

        return max;
    }
}

