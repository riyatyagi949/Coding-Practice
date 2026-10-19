/*
 * ==========================================
 * PROBLEM STATEMENT: Find Target Indices After Sorting Array
 * ==========================================
 * You are given a 0-indexed integer array `nums` and a target element `target`.
 * A target index is an index `i` such that `nums[i] == target`.
 * Return a list of the target indices of `nums` after sorting `nums` in non-decreasing order. 
 * The returned list must be sorted in increasing order.
 * 
 * Constraints:
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Counting Without Full Sorting)
 * ==========================================
 * - Approach:
 *   1. Instead of actually sorting the array (which takes O(n log n)), we can count two things:
 *      - How many elements in `nums` are strictly less than `target`. Let's call this `smallerCount`.
 *      - How many elements are exactly equal to `target`. Let's call this `targetCount`.
 *   2. Once we have these counts, all occurrences of `target` in the sorted array will occupy 
 *      the index range from `smallerCount` to `smallerCount + targetCount - 1`.
 *   3. We can directly generate this range of indices into a list and return it.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), where `n` is the length of `nums`, since we traverse the array twice (or once with a single pass count).
 *   - Space Complexity: O(1) auxiliary space (excluding the output list).
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smallerCount = 0;
        int targetCount = 0;

        // Count elements smaller than target and elements equal to target
        for (int num : nums) {
            if (num < target) {
                smallerCount++;
            } else if (num == target) {
                targetCount++;
            }
        }

        List<Integer> result = new ArrayList<>();

        // Generate the indices for all occurrences of target in the sorted array
        for (int i = 0; i < targetCount; i++) {
            result.add(smallerCount + i);
        }

        return result;
    }
}
