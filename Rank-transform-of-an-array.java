/**
 * PROBLEM STATEMENT: 1331. Rank Transform of an Array
 * --------------------------------------------------------------------------------
 * Given an array of integers arr, replace each element with its rank.
 * - Rank starts from 1.
 * - The larger the element, the larger the rank.
 * - Equal elements must have the same rank.
 * - Rank should be as small as possible (1, 2, 3...).
 *
 * Example:
 * Input: arr = [40, 10, 20, 30]
 * Sorted Unique: [10, 20, 30, 40]
 * Ranks: 10->1, 20->2, 30->3, 40->4
 * Output: [4, 1, 2, 3]
 *
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION:
 * --------------------------------------------------------------------------------
 * 1. Create a sorted copy of the unique elements from the original array.
 * 2. Use a HashMap to map each unique value to its corresponding rank (index + 1).
 * 3. Iterate through the original array and replace each element with its mapped rank.
 *
 * Time Complexity: O(N log N) due to sorting, where N is the length of arr.
 * Space Complexity: O(N) to store the sorted unique elements and the map.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) return new int[0];
        
        // Step 1: Clone the array and sort it to find unique elements and their relative order
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Step 2: Store ranks in a Map
        // We use a Map to handle duplicate elements automatically
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : sortedArr) {
            // Only assign a rank if the number hasn't been seen before
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }
        
        // Step 3: Replace original elements with their ranks
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}
