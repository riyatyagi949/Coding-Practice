/**
 * PROBLEM STATEMENT:
 * Given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
 * return the minimum integer common to both arrays. If there is no common 
 * integer, return -1.
 * 
 * OPTIMAL SOLUTION:
 * Since both arrays are already sorted, we can use the Two-Pointer approach.
 * We initialize two pointers, i and j, at the start of nums1 and nums2 respectively.
 * - If nums1[i] == nums2[j], we have found the smallest common element.
 * - If nums1[i] < nums2[j], we increment i to look for a larger value in nums1 
 *   to match nums2[j].
 * - If nums1[i] > nums2[j], we increment j to look for a larger value in nums2 
 *   to match nums1[i].
 * This works because the arrays are sorted; once a value is passed, it cannot 
 * be a candidate for a match later.
 * 
 * TIME COMPLEXITY: O(m + n)
 * We traverse each array at most once, where m and n are the lengths of nums1 and nums2.
 * 
 * SPACE COMPLEXITY: O(1)
 * We only use a constant amount of extra space for the two pointers.
 */

class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int i = 0; // Pointer for nums1
        int j = 0; // Pointer for nums2

        // Traverse both arrays simultaneously
        while (i < m && j < n) {
            if (nums1[i] == nums2[j]) {
                // Found the common element
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                // Current value in nums1 is smaller, move pointer forward
                i++;
            } else {
                // Current value in nums2 is smaller, move pointer forward
                j++;
            }
        }
        
        // No common element found
        return -1;
    }
}
