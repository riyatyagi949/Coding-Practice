/**
 * PROBLEM STATEMENT: 2615. Sum of Distances
 * --------------------------------------------------------------------------------
 * You are given a 0-indexed integer array nums. There exists an array arr of 
 * length nums.length, where arr[i] is the sum of |i - j| over all j such 
 * that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to 0.
 * * Example:
 * Input: nums = [1,3,1,1,2]
 * Output: [5,0,3,4,0]
 * Explanation:
 * i = 0: |0-2| + |0-3| = 5
 * i = 2: |2-0| + |2-3| = 3
 * i = 3: |3-0| + |3-2| = 4
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Prefix and Suffix Sums (Grouped by Value)
 * --------------------------------------------------------------------------------
 * 1. The naive O(N^2) solution would be to compare every index with every other
 * index, but with N=10^5, this is too slow.
 * 2. We can decompose the sum |i - j| into two parts for each index i:
 * - Left side: For all j < i where nums[j] == nums[i], the sum is (i - j).
 * Sum = (count_left * i) - (sum_of_indices_left)
 * - Right side: For all j > i where nums[j] == nums[i], the sum is (j - i).
 * Sum = (sum_of_indices_right) - (count_right * i)
 * 3. We use HashMaps to track the running 'count' and 'sum' of indices for 
 * each unique number encountered.
 * 4. Two passes:
 * - Forward pass: Calculate distances from identical elements to the left.
 * - Backward pass: Calculate distances from identical elements to the right.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array twice (forward and backward). HashMap operations are O(1) avg.
 * Space Complexity: O(N)
 * - We store the result array and use HashMaps to store counts/sums for unique values.
 * --------------------------------------------------------------------------------
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];

        Map<Integer, Long> indexSum = new HashMap<>();
        Map<Integer, Long> indexCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum  = indexSum.getOrDefault(nums[i], 0L);

            arr[i] += freq * i - sum;

            indexCount.put(nums[i], freq + 1);
            indexSum.put(nums[i], sum + i);
        }

        indexSum.clear();
        indexCount.clear();

        for (int i = n - 1; i >= 0; i--) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum  = indexSum.getOrDefault(nums[i], 0L);

            arr[i] += sum - freq * i;

            indexCount.put(nums[i], freq + 1);
            indexSum.put(nums[i], sum + i);
        }

        return arr;
    }
}

