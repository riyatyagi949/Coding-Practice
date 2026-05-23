/**
 * PROBLEM STATEMENT: 1752. Check if Array Is Sorted and Rotated
 * --------------------------------------------------------------------------------
 * Given an array 'nums', return true if it was originally sorted in non-decreasing 
 * order and then rotated some number of positions (including zero).
 * 
 * NOTE: An array rotated by x positions means B[i] = A[(i + x) % A.length].
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single-Pass Comparison
 * --------------------------------------------------------------------------------
 * 1. Logic:
 *    - In a fully sorted non-decreasing array, there are zero points where 
 *      nums[i] > nums[i+1].
 *    - If we rotate a sorted array, we introduce exactly one point (the pivot) 
 *      where the value "drops" (nums[i] > nums[i+1]).
 *    - Because the array is circular, we must also compare the last element 
 *      with the first element.
 *    - If we find more than one such "drop" in the circular comparison, the array 
 *      could not have been sorted and then rotated.
 * 
 * 2. Algorithm:
 *    - Iterate through the array and count how many times nums[i] > nums[(i + 1) % n].
 *    - If count <= 1, return true. Otherwise, return false.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We perform a single pass through the array of size N.
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space for the counter.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;
        
        // Traverse the array and check for "drops"
        for (int i = 0; i < n; i++) {
            // Compare current element with the next (using modulo for circularity)
            // nums[(i + 1) % n] handles the wraparound from last to first element
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
            
            // If more than one drop exists, it's not a sorted rotated array
            if (count > 1) {
                return false;
            }
        }
        
        // If 0 drops (already sorted) or 1 drop (rotated), it's valid
        return true;
    }
}
