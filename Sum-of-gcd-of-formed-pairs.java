/**
 * PROBLEM STATEMENT: 3867. Sum of GCD of Formed Pairs
 * --------------------------------------------------------------------------------
 * Given an integer array nums of length n:
 * 1. Construct prefixGcd where prefixGcd[i] = gcd(nums[i], max(nums[0...i])).
 * 2. Sort the resulting prefixGcd array in non-decreasing order.
 * 3. Pair the smallest and largest unpaired elements repeatedly.
 * 4. Compute the gcd of each pair and return the total sum.
 * 5. If n is odd, the middle element is ignored.
 *
 * Example: nums = [3, 6, 2, 8]
 * prefixGcd:
 * i=0: max=3, gcd(3,3)=3
 * i=1: max=6, gcd(6,6)=6
 * i=2: max=6, gcd(2,6)=2
 * i=3: max=8, gcd(8,8)=8
 * Array: [3, 6, 2, 8] -> Sorted: [2, 3, 6, 8]
 * Pairs: gcd(2, 8)=2, gcd(3, 6)=3 -> Sum: 2 + 3 = 5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION:
 * --------------------------------------------------------------------------------
 * 1. Use a single pass to compute prefixGcd using a running 'max' variable.
 * 2. Use Arrays.sort() for O(N log N) sorting.
 * 3. Use two pointers (left=0, right=n-1) to pair smallest and largest elements.
 * 4. Sum the results of gcd(prefixGcd[left], prefixGcd[right]).
 *
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N) due to sorting. The GCD computations take O(N * log(min(a,b))).
 * Space Complexity: O(N) to store the prefixGcd array.
 */

import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];

        int mx = 0;

        // Construct prefixGcd array: O(N)
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }

        // Sort: O(N log N)
        Arrays.sort(prefixGcd);

        long ans = 0;

        // Use two pointers to pair smallest and largest: O(N)
        int left = 0;
        int right = n - 1;

        while (left < right) {
            ans += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return ans;
    }

    // Helper to calculate GCD of two numbers: O(log(min(a,b)))
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
