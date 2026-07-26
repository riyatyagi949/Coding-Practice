/**
 * PROBLEM STATEMENT: 628. Maximum Product of Three Numbers
 * --------------------------------------------------------------------------------
 * Given an integer array nums, find three numbers whose product is maximum 
 * and return the maximum product.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single-Pass Scan (Tracking Top 3 Max & Top 2 Min)
 * --------------------------------------------------------------------------------
 * 1. The maximum product of three numbers can come from only two scenarios:
 *    a) Product of the 3 largest positive numbers: max1 * max2 * max3
 *    b) Product of the 2 smallest (most negative) numbers and the largest 
 *       positive number: min1 * min2 * max1 (since negative * negative = positive)
 * 2. Instead of sorting the array O(N log N), we can find these 5 key values 
 *    (max1, max2, max3, min1, min2) in a single linear pass O(N).
 * 3. Return the maximum of (max1 * max2 * max3) and (min1 * min2 * max1).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the nums array. We iterate 
 * through the array once.
 * Space Complexity: O(1) constant extra space used.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public int maximumProduct(int[] nums) {
        // Track the top 3 maximum values
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        // Track the top 2 minimum values (for large negative numbers)
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        // Single pass through the array to update maximums and minimums
        for (int num : nums) {
            // Update Top 3 Maximums
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } 
            else if (num > max2) {
                max3 = max2;
                max2 = num;
            } 
            else if (num > max3) {
                max3 = num;
            }

            // Update Top 2 Minimums
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } 
            else if (num < min2) {
                min2 = num;
            }
        }

        // Return max between (3 largest) OR (2 smallest negative * 1 largest)
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
