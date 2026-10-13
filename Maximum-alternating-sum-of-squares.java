/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Alternating Sum of Squares
 * ==========================================
 * You are given an integer array `nums`. You may rearrange the elements in any order.
 * The alternating score of an array `arr` is defined as:
 * score = arr[0]^2 - arr[1]^2 + arr[2]^2 - arr[3]^2 + ...
 * Return an integer denoting the maximum possible alternating score of `nums` after rearranging its elements.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy + Sorting)
 * ==========================================
 * - Approach:
 *   1. Notice that the score formula alternates signs: positive for even indices (0, 2, 4...) and 
 *      negative for odd indices (1, 3, 5...).
 *   2. Since we square every number, its sign doesn't matter initially, but we want the *largest* 
 *      magnitudes to contribute positively (even positions) and the *smallest* magnitudes to contribute 
 *      negatively (odd positions) to maximize the total sum.
 *   3. First, take the absolute value of all numbers (or convert negative numbers to positive, since their squares are identical).
 *   4. Sort the array in descending order of their magnitudes.
 *   5. Iterate through the sorted array: add the square of elements at even positions (0, 2, 4...) 
 *      and subtract the square of elements at odd positions (1, 3, 5...).
 * 
 * - Complexity:
 *   - Time Complexity: O(n log n) due to sorting the array.
 *   - Space Complexity: O(1) or O(n) depending on the sorting implementation and whether we modify the input array in place.
 */

import java.util.Arrays;

class Solution {
    public long maxAlternatingSumOfSquares(int[] nums) {
        int n = nums.length;
        
        // Take absolute values since signs are squared anyway
        long[] absNums = new long[n];
        for (int i = 0; i < n; i++) {
            absNums[i] = Math.abs((long) nums[i]);
        }
        
        // Sort in ascending order
        Arrays.sort(absNums);
        
        long score = 0;
        int left = 0;
        int right = n - 1;
        
        // Greedily pair largest elements at positive positions and smallest at negative positions
        boolean positive = true;
        while (left <= right) {
            if (positive) {
                // Pick the largest remaining element for the positive term
                score += absNums[right] * absNums[right];
                right--;
            } 
            else {
                // Pick the smallest remaining element for the negative term
                score -= absNums[left] * absNums[left];
                left++;
            }
            positive = !positive;
        }
        
        return score;
    }
}
