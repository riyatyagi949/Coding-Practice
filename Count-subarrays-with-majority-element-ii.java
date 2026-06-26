/**
 * PROBLEM STATEMENT: 3739. Count Subarrays With Majority Element II
 * --------------------------------------------------------------------------------
 * Given an array 'nums' and an integer 'target', return the number of subarrays 
 * where 'target' is the majority element.
 * A majority element in a subarray of length L appears strictly more than L/2 times.
 * * Key Constraints:
 * 1 <= nums.length <= 10^5 (Unlike version I, O(N^2) is no longer acceptable).
 * 1 <= nums[i], target <= 10^9
 * * Logic:
 * Let 'x' be the count of 'target' and 'y' be the count of non-target elements.
 * Majority condition: x > y => x > (length - x) => 2*x - length > 0.
 * Mapping target to +1 and non-target to -1, the condition is sum(subarray) > 0.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Fenwick Tree (Binary Indexed Tree)
 * --------------------------------------------------------------------------------
 * 1. Transform the array: if nums[i] == target, val = 1; else val = -1.
 * 2. Calculate the running prefix sum. Subarray [j, i] has sum > 0 if 
 * PrefixSum[i] - PrefixSum[j-1] > 0, which means PrefixSum[j-1] < PrefixSum[i].
 * 3. As we iterate through the array, we need to count how many previous 
 * prefix sums are strictly less than the current prefix sum.
 * 4. We use a Fenwick Tree (Binary Indexed Tree) to perform range sum queries 
 * on prefix sum counts in O(log N) time.
 * 5. Because prefix sums can be negative, we add an 'offset' to map them to 
 * positive indices for the Fenwick tree.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N), where N is the length of the array, due to 
 * N updates and queries on the Fenwick Tree.
 * Space Complexity: O(N) to store the Fenwick tree.
 * --------------------------------------------------------------------------------
 */

class Solution {
    // Inner class representing the Binary Indexed Tree (Fenwick Tree)
    class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        // Add 1 to the frequency count of the prefix sum at idx
        void update(int idx) {
            while (idx <= n) {
                bit[idx]++;
                idx += idx & -idx;
            }
        }

        // Query the cumulative frequency of prefix sums up to idx
        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // Offset ensures all prefix sums are mapped to positive indices
        int offset = n + 2;
        Fenwick ft = new Fenwick(2 * n + 5);

        long ans = 0;
        int prefix = 0;

        // Initial state: prefix sum 0 occurs once before any elements
        ft.update(prefix + offset);

        for (int x : nums) {
            // Update current prefix sum
            if (x == target)
                prefix++;
            else
                prefix--;

            // Query how many previous prefix sums were strictly smaller than current 'prefix'
            ans += ft.query(prefix + offset - 1);

            // Add current prefix sum frequency to the tree
            ft.update(prefix + offset);
        }
        return ans;
    }
}
