/**
 * PROBLEM STATEMENT: 1674. Minimum Moves to Make Array Complementary
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums' of even length 'n' and an integer 'limit'.
 * In one move, you can replace any integer from 'nums' with another integer 
 * between 1 and 'limit', inclusive.
 * * The array 'nums' is complementary if for all indices i (0-indexed),
 * nums[i] + nums[n - 1 - i] equals the same number.
 * * Return the minimum number of moves required to make nums complementary.
 * * Constraints:
 * - n == nums.length
 * - 2 <= n <= 10^5 (n is even)
 * - 1 <= nums[i] <= limit <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Difference Array Technique (Prefix Sum over Ranges)
 * --------------------------------------------------------------------------------
 
 * Instead of checking every possible target sum from 2 to 2 * limit for each pair,
 * we can determine the range of moves required for a specific pair (a, b) to reach
 * any target sum 'S', where S lies in [2, 2 * limit].
 * * Let A = min(a, b) and B = max(a, b).
 
 * For a given pair (a, b), if we want to change their sum to S:
 
 * * 1. 0 moves required: If S == a + b.
 * 2. 1 move required: We can change either 'a' or 'b' to a value in [1, limit].
 * - The minimum sum we can achieve by replacing one number is A + 1 (replacing B with 1).
 * - The maximum sum we can achieve by replacing one number is B + limit (replacing A with limit).
 * - So, for target sum S in [A + 1, B + limit], we need 1 move (except when S == a + b, which is 0 moves).
 
 * 3. 2 moves required: For any sum outside the 1-move boundary, i.e.,
 * S in [2, A] or S in [B + limit + 1, 2 * limit].
 * * We use a difference array 'diff' to efficiently apply these range updates:
 * - Initialize all target sums in [2, 2 * limit] to +2 moves.
 * - Transition to 1 move in range [A + 1, B + limit] by subtracting 1.
 * - Transition to 0 moves at exact point (a + b) by subtracting another 1.
 * - Revert the 0-move effect at (a + b + 1) by adding 1 back.
 * - Revert the 1-move effect at (B + limit + 1) by adding 1 back.
 * * Finally, we compute the prefix sum of the difference array to find the 
 * absolute number of moves for each target sum, taking the minimum value.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N + Limit)
 * - Processing n / 2 pairs takes O(N) time.
 * - Finding the minimum moves by iterating through the range [2, 2 * limit] takes O(Limit).
 * * Space Complexity: O(Limit)
 * - The difference array 'diff' requires a size of 2 * limit + 2.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;

        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {

            int a = nums[i];
            int b = nums[n - 1 - i];

            int low = Math.min(a, b) + 1;
            int high = Math.max(a, b) + limit;

            int sum = a + b;

            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            diff[low] -= 1;
            diff[high + 1] += 1;

            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }
        int ans = Integer.MAX_VALUE;
        int moves = 0;

        for (int s = 2; s <= 2 * limit; s++) {

            moves += diff[s];

            ans = Math.min(ans, moves);
        }

        return ans;
    }
}

