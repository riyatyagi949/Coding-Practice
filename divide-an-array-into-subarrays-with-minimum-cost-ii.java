/**
 * PROBLEM STATEMENT: 3013. Divide an Array Into Subarrays With Minimum Cost II
 * --------------------------------------------------------------------------------
 * You are given a 0-indexed array 'nums' of length n, and integers k and dist.
 * The cost of an array is its first element. 
 * You need to divide nums into k disjoint contiguous subarrays such that:
 * 1. The first subarray starts at index 0 (cost = nums[0]).
 * 2. If the start indices of the subarrays are 0, i1, i2, ..., ik-1:
 * Condition: ik-1 - i1 <= dist.
 * * Return the minimum possible sum of the costs of these k subarrays.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW WITH TWO TREESETS
 * --------------------------------------------------------------------------------
 * Logic:
 * 1. The first subarray always starts at index 0, so nums[0] is always part of the cost.
 * 2. We need to pick k-1 more starting indices from the remaining part of the array.
 * 3. Let the indices be i1, i2, ..., ik-1. The condition is ik-1 - i1 <= dist.
 * 4. This means all k-1 indices must fall within a window of size 'dist + 1'.
 * 5. We slide a window of size 'dist + 1' across the array starting from index 1.
 * 6. For each window, we need to find the sum of the (k-1) smallest elements.
 * * Efficiency:
 * To find the sum of (k-1) smallest elements in a sliding window efficiently:
 * - Use two Sorted Sets (TreeSets in Java) to maintain the (k-1) smallest elements 
 * (kMinimum) and the rest of the elements in the current window (remaining).
 * - kMinimum: Stores the (k-1) smallest elements currently in the window.
 * - remaining: Stores all other elements currently in the window.
 * - We maintain a running 'sum' of the elements in kMinimum.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log(dist))
 * - We iterate through the array once (N).
 * - Each insertion and deletion in the TreeSet takes O(log(window_size)), 
 * where window_size is at most 'dist + 1'.
 * * Space Complexity: O(dist)
 * - We store at most 'dist + 1' elements in our sets at any given time.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

import java.util.TreeSet;

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        TreeSet<int[]> kMinimum = new TreeSet<>((a, b) -> {
            if (a[0] != b[0])
            return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        TreeSet<int[]> remaining = new TreeSet<>((a, b) -> {
            if (a[0] != b[0])
             return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
         long sum = 0; 
          int i = 1;

        while (i < n && i - dist < 1) 
        {
            int[] cur = new int[]{nums[i], i};
            kMinimum.add(cur);
            sum += nums[i];

            if (kMinimum.size() > k - 1)
             {
                int[] largest = kMinimum.pollLast();
                sum -= largest[0];
                remaining.add(largest);
            }
            i++;
        }
        long result = Long.MAX_VALUE;

        while (i < n) {
            int[] cur = new int[]{nums[i], i};
            kMinimum.add(cur);
            sum += nums[i];

            if (kMinimum.size() > k - 1) {
                int[] largest = kMinimum.pollLast();
                sum -= largest[0];
                remaining.add(largest);
            }

            result = Math.min(result, sum);

            int remIdx = i - dist;
            int[] toRemove = new int[]{nums[remIdx], remIdx};

            if (kMinimum.remove(toRemove)) {
                sum -= nums[remIdx];

                if (!remaining.isEmpty()) {
                    int[] promote = remaining.pollFirst();
                    kMinimum.add(promote);
                    sum += promote[0];
                }
            }
             else {
                remaining.remove(toRemove);
            }

            i++;
        }
         return nums[0] + result;
    }
}

