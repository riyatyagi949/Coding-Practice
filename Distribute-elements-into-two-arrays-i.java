/*
 * ==========================================
 * PROBLEM STATEMENT: Distribute Elements Into Two Arrays I
 * ==========================================
 * You are given a 1-indexed array of distinct integers nums of length n.
 * You need to distribute all the elements of nums between two arrays arr1 and arr2 using n operations:
 * - 1st operation: append nums[1] to arr1.
 * - 2nd operation: append nums[2] to arr2.
 * - For the i-th operation (3rd to n-th):
 *   - If the last element of arr1 is greater than the last element of arr2, append nums[i] to arr1.
 *   - Otherwise, append nums[i] to arr2.
 * Return the array result formed by concatenating arr1 and arr2.
 * 
 * Constraints:
 * 3 <= n <= 50
 * 1 <= nums[i] <= 100
 * All elements in nums are distinct.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Simulation with ArrayLists)
 * ==========================================
 * - Approach:
 *   1. We can simulate the process directly by maintaining two separate lists (`arr1` and `arr2`).
 *   2. Add the first element (`nums[0]`) to `arr1` and the second element (`nums[1]`) to `arr2`.
 *   3. Iterate from the third element onwards (`i = 2` to `n - 1`), comparing the last element of `arr1` 
 *      with the last element of `arr2`. Append the current number to the appropriate list based on the condition.
 *   4. Finally, combine `arr1` and `arr2` into a single result array and return it.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), as we iterate through the input array of size n once.
 *   - Space Complexity: O(n) to store elements in `arr1`, `arr2`, and the final result array.
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        // Use lists to dynamically track elements in arr1 and arr2
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        // Base operations: first element to arr1, second element to arr2
        arr1.add(nums[0]);
        arr2.add(nums[1]);

        // Simulate the distribution for remaining elements
        for (int i = 2; i < n; i++) {
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);

            if (last1 > last2) {
                arr1.add(nums[i]);
            } 
            else {
                arr2.add(nums[i]);
            }
        }
       
      // Combine arr1 and arr2 into the result array
        int[] result = new int[n];
        int idx = 0;

        for (int num : arr1) {
            result[idx++] = num;
        }
        for (int num : arr2) {
            result[idx++] = num;
        }
        return result;
    }
}
