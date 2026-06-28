/**
 * PROBLEM STATEMENT:
 * Given an array 'arr' of positive integers, we want to modify it such that:
 * 1. The first element (after sorting) is 1.
 * 2. The absolute difference between any two adjacent elements is <= 1.
 * We can decrease any element or rearrange the array.
 * Goal: Return the maximum possible value of the largest element in the array.
 *
 * OPTIMAL SOLUTION:
 * 1. Sorting: Sort the array first so we can build the sequence incrementally.
 * 2. Logic:
 * - Force the first element to be 1.
 * - Iterate through the sorted array. If the current element is greater 
 * than the previous element + 1, cap it to (previous element + 1).
 * - Since we want the largest possible value, we don't decrease elements 
 * more than necessary.
 * 3. Complexity:
 * - Time: O(N log N) due to sorting.
 * - Space: O(1) or O(N) depending on the sorting algorithm implementation.
 */

import java.util.Arrays;

public class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Step 1: Sort the array
        Arrays.sort(arr);
        
        // Step 2: Set the first element to 1
        arr[0] = 1;
        
        // Step 3: Iterate and enforce the condition: abs(arr[i] - arr[i-1]) <= 1
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1] + 1) {
                // Reduce the current element to the maximum allowed value
                arr[i] = arr[i - 1] + 1;
            }
        }
        
        // The last element will now hold the maximum possible value
        return arr[arr.length - 1];
    }
}
