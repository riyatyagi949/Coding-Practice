/**
 * PROBLEM STATEMENT: 33. Search in Rotated Sorted Array
 * --------------------------------------------------------------------------------
 * Given an integer array 'nums' sorted in ascending order that has been 
 * rotated at an unknown pivot index, and an integer 'target'.
 * Return the index of 'target' if it exists, otherwise return -1.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Binary Search
 * --------------------------------------------------------------------------------
 * 1. Logic: In a rotated sorted array, if we pick a midpoint, at least one 
 *    half (either [low, mid] or [mid, high]) must be sorted.
 * 2. Strategy:
 *    - Check if the left half [low, mid] is sorted:
 *        - If target is within this range, search here.
 *        - Else, search the right half.
 *    - Otherwise, the right half [mid, high] must be sorted:
 *        - If target is within this range, search here.
 *        - Else, search the left half.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log N)
 * - The search space is halved in every iteration of the binary search.
 * Space Complexity: O(1)
 * - Only a few pointers (low, high, mid) are used; no extra data structures.
 * --------------------------------------------------------------------------------
 */
// Code -------------------------

class Solution {

    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while(low <= high) {

            int mid = low + (high - low) / 2;

            if(nums[mid] == target) {
                return mid;
            }
            if(nums[low] <= nums[mid]) {

                if(target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {

                if(target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}

