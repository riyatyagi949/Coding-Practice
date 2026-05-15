/**
 * PROBLEM STATEMENT: 153. Find Minimum in Rotated Sorted Array
 * --------------------------------------------------------------------------------
 * Suppose an array of length n sorted in ascending order is rotated between 1 
 * and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 * - [4,5,6,7,0,1,2] if it was rotated 4 times.
 * - [0,1,2,4,5,6,7] if it was rotated 7 times.
 * 
 * Given the sorted rotated array 'nums' of unique elements, return the minimum 
 * element of this array. You must write an algorithm that runs in O(log n) time.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BINARY SEARCH
 * --------------------------------------------------------------------------------
 * 1. The Core Idea:
 * In a rotated sorted array, we have two sorted subarrays. The minimum element 
 * is the "inflection point" where the value suddenly drops.
 * 
 * 2. Logic:
 * - We compare the middle element (mid) with the last element (high).
 * - If nums[mid] > nums[high]: The minimum must be to the right of mid, 
 *   because the right side contains the "drop." We set low = mid + 1.
 * - If nums[mid] <= nums[high]: The right side is correctly sorted, so the 
 *   minimum is either at mid or to the left of mid. We set high = mid.
 * 
 * 3. Result:
 * When low == high, the search space has converged to the minimum element.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log n)
 * - We divide the search space in half with every iteration.
 * Space Complexity: O(1)
 * - We use a constant amount of space for the pointers 'low', 'high', and 'mid'.
 * --------------------------------------------------------------------------------
 */
// Code----------------------------

class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } 
            else {
                high = mid;
            }
        }
        return nums[low];
    }
}

