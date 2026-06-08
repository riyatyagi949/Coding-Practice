/**
 * PROBLEM STATEMENT: 2161. Partition Array According to Given Pivot
 * --------------------------------------------------------------------------------
 * Given an integer array 'nums' and an integer 'pivot', rearrange the array such 
 * that:
 * 1. Every element less than pivot appears before every element greater than pivot.
 * 2. Every element equal to pivot appears in between those two groups.
 * 3. The relative order of elements less than pivot and elements greater than 
 * pivot is maintained.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Three-Pass Linear Scan
 * --------------------------------------------------------------------------------
 * 1. Since we must maintain relative order (stable partition), we can simply
 * iterate through the input array three times:
 * - First pass: Collect all elements < pivot.
 * - Second pass: Collect all elements == pivot.
 * - Third pass: Collect all elements > pivot.
 * 2. By filling a new result array using these passes, we naturally satisfy all 
 * three conditions, including the requirement for stable ordering.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We perform three linear passes over the input array, resulting in O(3N), 
 * which simplifies to O(N).
 * Space Complexity: O(N)
 * - We create a new array 'ans' of size N to store the rearranged elements.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int idx = 0;

        // Pass 1: Add all elements smaller than pivot
        for (int num : nums) {
            if (num < pivot) {
                ans[idx++] = num;
            }
        }

        // Pass 2: Add all elements equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                ans[idx++] = num;
            }
        }

        // Pass 3: Add all elements greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                ans[idx++] = num;
            }
        }

        return ans;
    }
}
