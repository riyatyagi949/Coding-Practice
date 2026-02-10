/**
 * PROBLEM STATEMENT: 3719. Longest Balanced Subarray I
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums'.
 * A subarray is called "balanced" if the number of DISTINCT even numbers in the 
 * subarray is equal to the number of DISTINCT odd numbers.
 * * Task: Return the length of the longest balanced subarray.
 * * Examples:
 * Input: nums = [2, 5, 4, 3] -> Output: 4
 * (Even distinct: {2, 4}, Odd distinct: {5, 3}. Count 2 == 2. Length 4)
 * * Input: nums = [1, 2, 3, 2] -> Output: 3
 * (Subarray [2, 3, 2] has Even distinct: {2}, Odd distinct: {3}. Count 1 == 1. Length 3)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Nested Iteration with Frequency Tracking
 * --------------------------------------------------------------------------------
 * 1. Strategy: 
 * - Given N <= 1500, an O(N^2) solution is acceptable.
 * - We iterate through all possible starting positions 'i'.
 * - For each 'i', we expand the subarray to 'j' and maintain the count of 
 * distinct even and odd numbers encountered so far.
 * * 2. Optimization:
 * - To track "distinct" elements efficiently without the overhead of a HashSet,
 * we can use a boolean array or a simple frequency array since nums[i] <= 10^5.
 * - However, to keep memory usage low for each outer loop iteration, we can
 * reset a visited array or use a local Set if the range of values was larger.
 * - Here, a frequency array is used for O(1) distinctness checks.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - Outer loop runs N times, inner loop runs N times.
 * - Distinct count updates and comparisons are O(1).
 * Space Complexity: O(MAX_VAL) 
 * - We use a boolean array of size 100,001 to track distinct elements in the 
 * current subarray window.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Code - 
class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            HashSet<Integer> even = new HashSet<>();
            HashSet<Integer> odd = new HashSet<>();

            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0) {
                    even.add(nums[j]);
                } 
                else {
                    odd.add(nums[j]);
                }
                if (even.size() == odd.size()) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}

