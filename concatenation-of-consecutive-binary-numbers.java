/**
 * PROBLEM STATEMENT: 1680. Concatenation of Consecutive Binary Numbers
 * --------------------------------------------------------------------------------
 * Given an integer n, return the decimal value of the binary string formed by 
 * concatenating the binary representations of 1 to n in order, modulo 10^9 + 7.
 *
 * * Example:
 * Input: n = 3
 * Output: 27
 * Explanation: Binary of 1 is "1", 2 is "10", 3 is "11".
 * Concatenation: "1" + "10" + "11" = "11011".
 * Binary "11011" in decimal is 27.
 *
 * * Constraints:
 * 1 <= n <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Bit Manipulation & Modular Arithmetic
 * --------------------------------------------------------------------------------
 * 1. The Core Idea:
 * To concatenate a new number 'i' to the current result 'ans', we need to shift 
 * 'ans' to the left by the number of bits in 'i', and then add 'i'.
 * Formula: ans = ((ans << bitLengthOf(i)) + i) % MOD
 *
 * 2. Finding bitLengthOf(i) efficiently:
 * Instead of calculating the bit length for every 'i' using logarithms or 
 * bit-counting functions, we notice that the bit length only increases by 1 
 * whenever 'i' is a power of 2 (e.g., 1, 2, 4, 8, 16...).
 * We can detect a power of 2 using the bitwise trick: (i & (i - 1)) == 0.
 *
 * 3. Modular Arithmetic:
 * Since the concatenated number grows extremely fast, we must apply the 
 * modulo (10^9 + 7) at every step to prevent overflow, even when using 'long'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate from 1 to n exactly once.
 * - Each bitwise operation and addition inside the loop takes O(1) time.
 *
 * Space Complexity: O(1)
 * - We only use a few variables (ans, mod, bitLength) regardless of the input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
   public int concatenatedBinary(int n) {
        long ans = 0;
        int mod = 1_000_000_007;
        int bitLength = 0;
        
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }
           ans = ((ans << bitLength) + i) % mod;
        }
         return (int) ans;
    }
}
