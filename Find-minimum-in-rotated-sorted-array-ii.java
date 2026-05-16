/**
 * PROBLEM STATEMENT: 154. Find Minimum in Rotated Sorted Array II
 * --------------------------------------------------------------------------------
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * Given the sorted rotated array 'nums' that MAY contain duplicates, return the minimum 
 * element of this array.
 * * Constraints:
 * - n == nums.length
 * - 1 <= n <= 5000
 * - -5000 <= nums[i] <= 5000
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: MODIFIED BINARY SEARCH
 * --------------------------------------------------------------------------------
 * The problem is a variation of finding the minimum in a rotated sorted array, 
 * but the presence of duplicates introduces an ambiguity. 
 * * We use a binary search approach with two pointers, 'low' and 'high':
 * 1. If nums[mid] > nums[high]:
 *    The left half is properly sorted, meaning the rotation inflection point (and 
 *    the minimum element) must be in the right half. Move low = mid + 1.
 * 2. If nums[mid] < nums[high]:
 *    The right half is properly sorted, meaning the minimum element is either at 
 *    'mid' or to its left. Move high = mid.
 * 3. If nums[mid] == nums[high]:
 *    We cannot determine which side contains the minimum element (e.g., [1, 0, 1, 1, 1] 
 *    vs [1, 1, 1, 0, 1]). Since nums[mid] is equal to nums[high], we can safely 
 *    decrement 'high' by 1 (high--) without losing the minimum value.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: 
 * - Average Case: O(log N) where N is the length of the array. The search space 
 *   is halved at each step.
 * - Worst Case: O(N) when all or almost all elements are duplicates (e.g., [1, 1, 1, 1]). 
 *   In this case, the algorithm constantly falls back to the `high--` step, degrading 
 *   to a linear scan.
 * * Space Complexity: O(1)
 * - The algorithm uses constant extra space as it modifies pointers in-place.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        int resultIdx = 0;

        while (l <= r) {
            while (l < r && nums[l] == nums[l + 1])
                l++;

            while (l < r && nums[r] == nums[r - 1])
                r--;

            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[resultIdx])
                resultIdx = mid;

            if (nums[mid] > nums[r]) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return nums[resultIdx];
    }
}
