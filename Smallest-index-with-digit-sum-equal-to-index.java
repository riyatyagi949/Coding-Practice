/*
 * ==========================================
 * PROBLEM STATEMENT: Smallest Index With Digit Sum Equal to Index
 * ==========================================
 * Given an integer array `nums`, return the smallest index `i` such that the sum of the digits 
 * of `nums[i]` is equal to `i`. If no such index exists, return -1.
 * 
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Linear Scan & Digit Sum Calculation)
 * ==========================================
 * - Approach:
 *   1. Iterate through each element in `nums` from index `0` to `n - 1`.
 *   2. For each element `nums[i]`, compute the sum of its digits:
 *      - Repeatedly extract the last digit (`num % 10`) and divide by 10 (`num / 10`) until the number becomes 0.
 *   3. Check if the computed digit sum equals the current index `i`.
 *   4. Since we iterate from left to right (0 upwards), the first index that satisfies the condition is guaranteed 
 *      to be the smallest index. Return `i` immediately.
 *   5. If the loop finishes without finding any matching index, return `-1`.
 * 
 * - Complexity:
 *   - Time Complexity: O(N * log10(M)), where `N` is the length of `nums` and `M` is the maximum value in `nums` 
 *     (since calculating the digit sum takes time proportional to the number of digits).
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int smallestIndexWithDigitSumEqualIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int digitSum = getDigitSum(nums[i]);
            
            if (digitSum == i) {
                return i;
            }
        }
         return -1;
    }
    
    // Helper function to calculate the sum of digits of a number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
