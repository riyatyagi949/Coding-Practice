/**
 * PROBLEM STATEMENT: 3653. XOR After Range Multiplication Queries I
 * --------------------------------------------------------------------------------
 * You are given an integer array nums of length n and a 2D integer array queries 
 * where queries[i] = [li, ri, ki, vi].
 * * For each query, perform these operations:
 * 1. Start at index idx = li.
 * 2. While idx <= ri:
 * Update nums[idx] = (nums[idx] * vi) % (10^9 + 7)
 * Increment idx by ki.
 * * After processing all queries, return the bitwise XOR of all elements in nums.
 * * Example:
 * Input: nums = [2,3,1,5,4], queries = [[1,4,2,3],[0,2,1,2]]
 * Output: 31
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DIRECT SIMULATION
 * --------------------------------------------------------------------------------
 * 1. Constraints Analysis:
 * - n (array length) is up to 1,000.
 * - q (number of queries) is up to 1,000.
 * - Total operations roughly = q * (n/k). In the worst case (k=1), this is 
 * 1,000 * 1,000 = 1,000,000.
 * - 10^6 operations is well within the time limit for Java (usually ~10^8 per sec).
 * * 2. Arithmetic:
 * - Use 'long' for multiplication to prevent overflow before the modulo operation.
 * - The modulo is 10^9 + 7.
 * * 3. Final Step:
 * - Iterate through the modified 'nums' array and calculate the XOR sum.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(Q * (N/K)) 
 * - In the worst case (K=1), it is O(Q * N).
 * - With N=1000 and Q=1000, this is 10^6 operations.
 * Space Complexity: O(1) 
 * - We modify the input array in place and use constant extra space for variables.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long MOD = 1000000007L;

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int)((nums[idx] * 1L * v) % MOD);
            }
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}


