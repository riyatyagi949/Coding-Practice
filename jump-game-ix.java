/**
 * PROBLEM STATEMENT: 3660. Jump Game IX
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums'. You can jump between indices based on:
 * 1. Forward Jump: From i to j (j > i) if nums[j] < nums[i].
 * 2. Backward Jump: From i to j (j < i) if nums[j] > nums[i].
 * * For each index i, find the maximum value in 'nums' reachable by any sequence 
 * of valid jumps. Return an array 'ans' where ans[i] is this maximum value.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: PREFIX MAX AND SUFFIX MIN
 * --------------------------------------------------------------------------------
 * 1. Reachability Logic:
 * - Jumping backward (Rule 2) allows you to reach the maximum value to your left.
 *   This is tracked by 'pre[i]' (prefix maximum).
 * - Jumping forward (Rule 1) is possible if there is ANY value to your right 
 *   smaller than your current value.
 * - If you can jump forward to an index 'j', you can then jump backward from 'j' 
 *    to reach the prefix maximum at 'j' (pre[j]). 
 * * 2. Key Observation:
 * If the maximum value seen so far (pre[i]) is greater than the minimum value 
 * remaining to the right (suf[i+1]), it means a forward jump is possible. 
 * If a forward jump is possible, index i can inherit the maximum reachable value 
 * of index i+1.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We perform three linear passes (Prefix Max, Suffix Min, and Result calculation).
 * Space Complexity: O(N)
 * - We use three arrays of size N to store intermediate and final results.
 * --------------------------------------------------------------------------------
 */
// Code -----------------------
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] pre = new int[n];
        int[] suf = new int[n];
        int[] res = new int[n];

        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = Math.max(pre[i - 1], nums[i]);
        }
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.min(suf[i + 1], nums[i]);
        }

        res[n - 1] = pre[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (pre[i] > suf[i + 1]) {
                res[i] = res[i + 1];
            }
            else {
                res[i] = pre[i];
            }
        }

        return res;
    }
}
