/**
 * PROBLEM STATEMENT: 1464. Maximum Product of Two Elements in an Array
 * --------------------------------------------------------------------------------
 * Given the array of integers nums, you will choose two different indices i and j 
 * of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single-Pass Scan (Tracking Top 2 Largest Elements)
 * --------------------------------------------------------------------------------
 * 1. Since all elements are positive (1 <= nums[i] <= 10^3), the maximum product 
 *    will always come from the two largest numbers in the array.
 * 2. Instead of sorting the array O(N log N), we can find the largest (max1) 
 *    and second largest (max2) numbers in a single O(N) pass.
 * 3. Return the result of (max1 - 1) * (max2 - 1).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the nums array. We traverse 
 * the array once.
 * Space Complexity: O(1) constant extra space used.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public int maxProduct(int[] nums) {
        // Track the largest and second-largest values
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        // Single pass to update maximums
        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            }
            else if (num > max2) {
                max2 = num;
            }
        }

        // Return the product after subtracting 1 from each
        return (max1 - 1) * (max2 - 1);
    }
}
