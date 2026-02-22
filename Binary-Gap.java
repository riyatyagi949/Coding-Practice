/**
 * PROBLEM STATEMENT: 868. Binary Gap
 * --------------------------------------------------------------------------------
 * Given a positive integer n, find and return the longest distance between any 
 * two adjacent 1's in the binary representation of n. 
 * * Adjacent 1's: Two 1's are adjacent if there are only 0's separating them.
 * * Distance: The absolute difference between their bit positions.
 * * If no two adjacent 1's exist (e.g., only one '1' in binary), return 0.
 * * Example:
 * Input: n = 22 (Binary: "10110")
 * Positions of 1s: Index 1, 2, and 4.
 * Distance between index 1 and 2 = 1.
 * Distance between index 2 and 4 = 2.
 * Output: 2
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single Pass Bit Manipulation
 * --------------------------------------------------------------------------------
 * 1. Tracking Positions:
 * We iterate through the bits of the number from right to left (least 
 * significant to most significant).
 * * 2. Variables:
 * - 'last': Stores the position of the previously encountered '1'. Initialized 
 * to -1 to signify no '1' has been found yet.
 * - 'maxGap': Stores the maximum distance found between any two consecutive '1's.
 * - 'position': Tracks the current bit index (starting from 0).
 * * 3. The Logic:
 * - Check if the current bit is 1 using `(n & 1)`.
 * - If it's a 1 and `last` is not -1, calculate the gap: `current_position - last`.
 * - Update `maxGap` if the new gap is larger.
 * - Update `last` to the `current_position`.
 * - Shift 'n' right by 1 bit (`n >> 1`) and increment the position.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log N)
 * - We iterate through the bits of N. The number of bits in N is floor(log2(N)) + 1.
 * - For a 32-bit integer, this is at most 31 iterations.
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space for integer variables.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int binaryGap(int n) {
        int last = -1;
        int maxGap = 0;
        int position = 0;

        while (n > 0) {
         if ((n & 1) == 1) {
         if (last != -1) {
            maxGap = Math.max(maxGap, position - last);
         }
         last = position;
     }
      n = n >> 1;
      position++;
        }
         return maxGap;
    }
}

