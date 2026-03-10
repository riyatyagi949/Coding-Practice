/**
 * PROBLEM STATEMENT: Subarrays with First Element Minimum
 * --------------------------------------------------------------------------------
 * Given an integer array arr[], count the number of subarrays where the first 
 * element is the minimum element of that subarray.
 * * Note: A subarray is valid if its first element is <= all other elements 
 * in that subarray.
 * * Examples:
 * Input: arr[] = [1, 2, 1]
 * Output: 5
 * Explanation: Valid subarrays are [1], [1, 2], [1, 2, 1], [2], [1]. 
 * Note that [2, 1] is invalid because the first element (2) is not the minimum.
 * * Constraints:
 * 1 <= arr.size() <= 5 * 10^4
 * 1 <= arr[i] <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Monotonic Stack (Next Smaller Element Pattern)
 * --------------------------------------------------------------------------------
 * 1. Core Logic:
 * For each index 'i', the element arr[i] can be the first element (and minimum)
 * of a subarray starting at 'i' and ending at some index 'j'.
 * This remains true as long as all elements from arr[i+1] to arr[j] are 
 * greater than or equal to arr[i].
 * * 2. Finding the Range:
 * We need to find the "Next Smaller Element" (NSE) to the right of index 'i'. 
 * Let the index of the next smaller element be 'k'. 
 * - If such 'k' exists, subarrays starting at 'i' can end at any index 
 * from 'i' to 'k-1'. Total count for this start index = (k - i).
 * - If no smaller element exists to the right, subarrays can end at any 
 * index from 'i' to 'n-1'. Total count for this start index = (n - i).
 * * 3. Algorithm:
 * - Use a Monotonic Increasing Stack to store indices.
 * - Iterate through the array. If the current element arr[i] is smaller than 
 * arr[stack.peek()], it means 'i' is the Next Smaller Element for the 
 * index at the top of the stack.
 * - Pop the index, calculate the distance (i - popped_index), and add to total.
 * - After the loop, any indices remaining in the stack have no smaller element 
 * to their right. Calculate distance as (n - popped_index).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - Each element is pushed onto the stack exactly once and popped exactly once.
 * - The while loop, in total across all iterations, performs N pops.
 * Space Complexity: O(N)
 * - In the worst case (a strictly increasing array), the stack will store N indices.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

import java.util.Stack;

class Solution {
    public int countSubarrays(int[] arr) {
        int n = arr.length;
        long count = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int idx = stack.pop();
                count += i - idx;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int idx = stack.pop();
            count += n - idx;
        }

        return (int)count;
    }
}

