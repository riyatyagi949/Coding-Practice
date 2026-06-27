/**
 * PROBLEM STATEMENT: 3020. Find the Maximum Number of Elements in Subset
 * --------------------------------------------------------------------------------
 * You are given an array of positive integers 'nums'. You need to select a subset 
 * that can be arranged in the pattern [x, x^2, x^4, ..., x^(k/2), x^k, x^(k/2), ..., x^4, x^2, x].
 * This pattern represents a sequence that increases by squaring the base until a peak 
 * element x^k is reached, then decreases by taking the square root back to x.
 * Note: x can be 1, in which case the pattern is [1, 1, ..., 1].
 * Return the maximum number of elements in such a subset.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Frequency Mapping & Chain Simulation
 * --------------------------------------------------------------------------------
 * 1. Count occurrences of each number using a HashMap.
 * 2. Handle 1s separately: If there are 'c' ones, we can include all of them if 'c' is 
 * odd, or 'c-1' if 'c' is even, to maintain the symmetric pattern.
 * 3. For any other number 'x > 1':
 * - Treat 'x' as the starting base.
 * - Simulate the squaring chain (x, x^2, x^4, ...). 
 * - To build the pattern [x, x^2, ..., x^k, ..., x^2, x], we need at least 
 * two of every element in the chain except the peak element (x^k), which 
 * only requires one.
 * - Traverse while the count of the current value is >= 2.
 * - The length of such a subset is (number of steps * 2) + 1.
 * 4. Update the answer based on the maximum length found for any base 'x'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * log(log(max_val))), where N is the length of nums. 
 * Since squaring grows exponentially, the chain length for any x > 1 is very small.
 * Space Complexity: O(N) to store the frequency map of the input array.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Handle the base case for 1: [1, 1, 1, ..., 1]
        int ones = count.getOrDefault(1, 0);
        int ans = (ones % 2 == 1) ? ones : (ones > 0 ? ones - 1 : 1);

        // Iterate through unique numbers to find the longest chain
        for (int num : count.keySet()) {
            if (num == 1) continue;

            long x = num;
            int length = 0;

            // Follow the squaring chain: x -> x^2 -> x^4 -> ...
            // We need 2 of each intermediate value to form the symmetric wings
            while (count.getOrDefault((int) x, 0) >= 2) {
                length += 2;
                // Avoid overflow: stop if x*x exceeds the range
                if (x > 100000) break; 
                x *= x;
            }

            // Check if the peak element exists
            if (count.containsKey((int) x)) {
                // Peak exists, current length is valid: (wings) + 1 (peak)
                ans = Math.max(ans, length + 1);
            } else {
                // Peak doesn't exist, we must step back one wing
                // Result is length - 1 (last squared value was not the peak)
                ans = Math.max(ans, length - 1);
            }
        }

        return ans;
    }
}
