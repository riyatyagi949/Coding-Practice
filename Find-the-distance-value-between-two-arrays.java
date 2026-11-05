/*
 * ==========================================
 * PROBLEM STATEMENT: Find the Distance Value Between Two Arrays
 * ==========================================
 * Given two integer arrays `arr1` and `arr2`, and an integer `d`, return the distance value between the two arrays.
 * The distance value is defined as the number of elements `arr1[i]` such that there is **not any** element 
 * `arr2[j]` where `|arr1[i] - arr2[j]| <= d`.
 * 
 * Constraints:
 * 1 <= arr1.length, arr2.length <= 500
 * -1000 <= arr1[i], arr2[j] <= 1000
 * 0 <= d <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sorting + Binary Search)
 * ==========================================
 * - Approach:
 *   1. Sort `arr2` so we can efficiently check distances using binary search.
 *   2. For each element `x` in `arr1`, we want to check if any element in `arr2` falls within the range `[x - d, x + d]`.
 *   3. Use binary search (`Arrays.binarySearch` or lower bound) to find the insertion position of `x - d` in `arr2`.
 *   4. Check the element at that position (and its predecessor if valid) to see if it is `<= x + d`. 
 *      If we find any element in `arr2` within the range `[x - d, x + d]`, then `x` violates the condition.
 *   5. If no such element exists in `arr2`, increment our distance value count.
 * 
 * - Complexity:
 *   - Time Complexity: O(m log m + n log m), where `n` is the length of `arr1` and `m` is the length of `arr2`.
 *   - Space Complexity: O(log m) or O(m) depending on the sorting implementation footprint.
 */

import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int x : arr1) {
            if (!hasNearbyElement(arr2, x, d)) {
                distanceValue++;
            }
        }

        return distanceValue;
    }

    private boolean hasNearbyElement(int[] arr2, int target, int d) {
        int low = 0;
        int high = arr2.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (Math.abs(arr2[mid] - target) <= d) {
                return true;
            } else if (arr2[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Also check elements around the closest insertion point to ensure absolute accuracy
        if (low < arr2.length && Math.abs(arr2[low] - target) <= d) return true;
        if (high >= 0 && Math.abs(arr2[high] - target) <= d) return true;

        return false;
    }
}
