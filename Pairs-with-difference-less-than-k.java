// Problem Statement:
// Given an array arr[] of positive integers and an integer k, find the total number of pairs 
// of elements that have an absolute difference strictly less than k.
// Note: Pair (i, j) is considered the same as (j, i).
// Constraints: 1 <= arr.size() <= 10^5, 0 <= k <= 10^5, 1 <= arr[i] <= 10^5.

// Optimal Solution in Java:
// Runtime: O(N log N) - due to sorting the array.
// Space Complexity: O(1) or O(log N) depending on the sorting algorithm's auxiliary space.

import java.util.Arrays;

class Solution {
    public static int countPairs(int[] arr, int k) {
        // If k is less than or equal to 0, no pair can have a difference strictly less than k.
        if (k <= 0) return 0;

        // Sort the array to use the two-pointer technique efficiently.
        Arrays.sort(arr);

        int n = arr.length;
        int left = 0;
        int right = 1;
        int count = 0;

        // Use a two-pointer sliding window approach
        while (right < n) {
            // If the difference between arr[right] and arr[left] is less than k,
            // then all elements from index `left` to `right - 1` form a valid pair with `arr[right]`.
            if (arr[right] - arr[left] < k) {
                count += (right - left);
                right++;
            } 
            // Otherwise, increment `left` to reduce the difference.
            else {
                left++;
                // Ensure `right` is always ahead of `left`.
                if (left == right) {
                    right++;
                }
            }
        }
        
        return count;
    }
}
