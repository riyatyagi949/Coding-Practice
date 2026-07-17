/*
 * PROBLEM STATEMENT:
 * Given an integer array 'nums' of length 'n', calculate the Greatest Common Divisor (GCD) 
 * for all possible pairs (nums[i], nums[j]) where 0 <= i < j < n.
 * Let 'gcdPairs' be the array of all these calculated GCD values, sorted in ascending order.
 * For each query in the 'queries' array, find the value at the index 'queries[i]' in 'gcdPairs'.
 * 
 * OPTIMAL SOLUTION APPROACH:
 * 1. Constraints analysis: Max value of nums[i] is 50,000. 'n' is up to 100,000. 
 *    Calculating all pairs (O(n^2)) is too slow. We must count the frequency of each GCD value.
 * 2. Count multiples: Let 'divisible[d]' be the number of elements in 'nums' that are multiples of 'd'.
 *    We can calculate this efficiently for all 'd' up to max(nums) in O(V log V) where V = max(nums).
 * 3. Inclusion-Exclusion Principle: The number of pairs with a GCD that is a multiple of 'd' 
 *    is given by: pairs = (divisible[d] * (divisible[d] - 1)) / 2.
 *    However, this includes pairs whose GCD is a multiple of 'd' (e.g., 2d, 3d, etc.).
 *    By iterating downwards from max(nums) to 1, we calculate 'exact[d]' (the number of pairs 
 *    whose GCD is exactly 'd') by subtracting the counts of multiples (2d, 3d...) from the total 
 *    pairs divisible by 'd'.
 * 4. Prefix Sums & Binary Search: Create a prefix sum array of 'exact' values. 
 *    'prefix[v]' represents the total number of pairs with GCD <= v.
 *    For each query, perform a binary search on the 'prefix' array to find the smallest 'v' 
 *    such that 'prefix[v] >= queries[i] + 1'.
 */

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        int[] freq = new int[max + 1];

        for (int x : nums)
            freq[x]++;

        int[] divisible = new int[max + 1];

        for (int d = 1; d <= max; d++) {
            for (int m = d; m <= max; m += d) {
                divisible[d] += freq[m];
            }
        }

        long[] exact = new long[max + 1];

        for (int d = max; d >= 1; d--) {

            long pairs = (long) divisible[d] * (divisible[d] - 1) / 2;

            exact[d] = pairs;

            for (int m = d * 2; m <= max; m += d)
                exact[d] -= exact[m];
        }

        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long k = queries[i] + 1;

            int l = 1, r = max;

            while (l < r) {
                int mid = (l + r) / 2;

                if (prefix[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}
