/**
 * PROBLEM STATEMENT: 396. Rotate Function
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums' of length n.
 * Assume 'arr_k' to be an array obtained by rotating 'nums' by k positions 
 * clockwise. We define the rotation function F on nums as follows:
 * 
 * F(k) = 0 * arr_k[0] + 1 * arr_k[1] + ... + (n - 1) * arr_k[n - 1]
 * 
 * Return the maximum value of F(0), F(1), ..., F(n-1).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: LINEAR SCAN WITH MATHEMATICAL DERIVATION
 * --------------------------------------------------------------------------------
 * A brute-force calculation of every F(k) would take O(n^2), which is too slow.
 * Instead, we observe the relationship between F(k) and F(k-1).
 * 
 * Let S be the sum of all elements in 'nums'.
 * F(0) = 0*nums[0] + 1*nums[1] + ... + (n-1)*nums[n-1]
 * F(1) = 0*nums[n-1] + 1*nums[0] + 2*nums[1] + ... + (n-1)*nums[n-2]
 * 
 * Subtracting F(0) from F(1):
 * F(1) - F(0) = nums[0] + nums[1] + ... + nums[n-2] - (n-1)*nums[n-1]
 * Adding and subtracting nums[n-1] to the right side:
 * F(1) - F(0) = S - n * nums[n-1]
 * 
 * General Formula:
 * $$F(k) = F(k-1) + S - n \cdot nums[n-k]$$
 * 
 * 1. Calculate the total sum 'S' and the initial value F(0).
 * 2. Use the recurrence relation to find F(1) through F(n-1) in O(1) each.
 * 3. Track the maximum value found.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We iterate through the array once to find S and F(0).
 * - We iterate n-1 times to find subsequent rotation values.
 * Space Complexity: O(1)
 * - We only use a few long variables to store sums and temporary values.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java--------------

class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int F = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            F += i * nums[i];
        }
        int result = F;

        for (int k = 0; k < n; k++) {
            int newF = F + sum - n * nums[n - 1 - k];
            result = Math.max(result, newF);
            F = newF;
        }
         return result;
    }
}

