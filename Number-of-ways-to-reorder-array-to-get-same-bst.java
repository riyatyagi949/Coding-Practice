/*
 * ==========================================
 * PROBLEM STATEMENT: Number of Ways to Reorder Array to Get Same BST
 * ==========================================
 * Given an array `nums` that represents a permutation of integers from 1 to n, 
 * return the number of ways to reorder `nums` such that the BST formed is identical 
 * to the original BST formed from `nums`. 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Divide and Conquer + Combinatorics)
 * ==========================================
 * - Approach:
 *   1. The first element of the array always becomes the root of the BST.
 *   2. All elements smaller than the root go into the left subtree, and all elements larger go into the right subtree.
 *   3. The relative order of elements within the left subtree and right subtree must be preserved.
 *   4. To merge the left subtree sequence (size `L`) and right subtree sequence (size `R`) while 
 *      maintaining their internal relative orders, we can choose `L` positions out of `L + R` total positions 
 *      for the left elements using combinations: $\binom{L + R}{L}$.
 *   5. We recursively calculate the number of ways for the left and right subtrees, multiply them together 
 *      with the combination count, and take modulo $10^9 + 7$.
 *   6. Finally, subtract 1 from the total ways of the root array because the original order itself shouldn't be counted 
 *      as a reordering.
 * 
 * - Complexity:
 *   - Time Complexity: O(n^2) due to pascal's triangle precomputation and recursive splitting.
 *   - Space Complexity: O(n^2) to store the combination table and recursion stack.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];

        // Precompute combinations using Pascal's triangle
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return (int) (countWays(list) - 1 + MOD) % MOD;
    }

    private long countWays(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> leftTree = new ArrayList<>();
        List<Integer> rightTree = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            int val = nums.get(i);
            if (val < root) {
                leftTree.add(val);
            } else {
                rightTree.add(val);
            }
        }

        long leftWays = countWays(leftTree);
        long rightWays = countWays(rightTree);

        int leftSize = leftTree.size();
        int rightSize = rightTree.size();

        // Ways to interleave left and right elements * ways to form left * ways to form right
        long ways = comb[leftSize + rightSize][leftSize];
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}
