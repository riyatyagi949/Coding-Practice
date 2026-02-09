/**
 * PROBLEM STATEMENT: Find Kth Rotation
 * --------------------------------------------------------------------------------
 * Given an increasing sorted rotated array 'arr' of distinct integers. 
 * The array is right-rotated k times. Find the value of k.
 * * Logic of Rotation:
 * Original: [1, 2, 3, 4, 5]
 * 1st Rotation (Right): [5, 1, 2, 3, 4] -> k = 1
 * 2nd Rotation (Right): [4, 5, 1, 2, 3] -> k = 2
 * * Observation: 
 * The value of 'k' (the number of right rotations) is exactly equal to the 
 * index of the minimum element in the array.
 * * Examples:
 * Input: arr = [5, 1, 2, 3, 4] -> Output: 1 (Index of 1 is 1)
 * Input: arr = [1, 2, 3, 4, 5] -> Output: 0 (Index of 1 is 0)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Modified Binary Search
 * --------------------------------------------------------------------------------
 * 1. Goal: Find the index of the minimum element in a sorted rotated array.
 * 2. Binary Search Strategy:
 * - If arr[low] <= arr[high], the range is already sorted. The minimum is at 'low'.
 * - Calculate 'mid'.
 * - Check if 'mid' is the pivot (minimum):
 * - It must be smaller than its next and previous elements (handled with modulo).
 * - If the left half [low...mid] is sorted (arr[low] <= arr[mid]), the pivot must 
 * be in the right half. Set low = mid + 1.
 * - Otherwise, the pivot is in the left half. Set high = mid - 1.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log N)
 * - We use binary search to halve the search space in each iteration.
 * - N is up to 10^5, so log(10^5) is ~17 iterations.
 * * Space Complexity: O(1)
 * - We only use a constant amount of space for pointers (low, mid, high).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-
class Solution {
    public int findKRotation(int arr[]) {
        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            if (arr[low] <= arr[high]) {
                return low;
            }

            int mid = low + (high - low) / 2;
            int next = (mid + 1) % n;
            int prev = (mid - 1 + n) % n;

            if (arr[mid] <= arr[next] && arr[mid] <= arr[prev]) {
                return mid;
            }
            if (arr[mid] >= arr[low]) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return 0;
    }
}



