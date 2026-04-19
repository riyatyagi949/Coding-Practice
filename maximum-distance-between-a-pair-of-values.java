/**
 * PROBLEM STATEMENT: 1855. Maximum Distance Between a Pair of Values
 * --------------------------------------------------------------------------------
 * You are given two non-increasing 0-indexed integer arrays nums1 and nums2.
 * A pair of indices (i, j) is valid if:
 * 1. 0 <= i < nums1.length
 * 2. 0 <= j < nums2.length
 * 3. i <= j
 * 4. nums1[i] <= nums2[j]
 * * The distance of the pair is (j - i).
 * * Goal: Return the maximum distance of any valid pair (i, j). 
 * If no valid pairs exist, return 0.
 * * Example:
 * Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * Output: 2
 * Explanation: Valid pairs include (2,4) where nums1[2]=5 <= nums2[4]=5. 
 * Distance is 4 - 2 = 2.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Two-Pointer Approach
 * --------------------------------------------------------------------------------
 * 1. Property: Since both arrays are non-increasing, we can use two pointers 
 * (i for nums1 and j for nums2) to find the maximum distance in a single pass.
 * * 2. Logic:
 * - Start i = 0 and j = 0.
 * - If nums1[i] <= nums2[j]:
 * This is a valid pair (if i <= j, which is naturally maintained or handled).
 * Calculate distance (j - i) and update the result.
 * Increment j to see if we can find a further valid index for the current i.
 * - If nums1[i] > nums2[j]:
 * The current nums2[j] is too small for nums1[i]. Since nums2 is non-increasing,
 * any index after j will also be too small for the current i.
 * Increment i to find a smaller value in nums1 that might be <= nums2[j].
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N + M)
 * - N is the length of nums1 and M is the length of nums2.
 * - Each pointer (i and j) travels across its respective array at most once.
 * * Space Complexity: O(1)
 * - We only use a few integer variables for pointers and the result.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int i = 0;
        int j = 0;
        int result = 0;

        while ( i < m && j < n){
            if( nums1[i] > nums2[j]){
                i++;

            }
            else{
                result = Math.max(result , j - i);
                j++;
            }
        }
        return result;
        
    }
}


