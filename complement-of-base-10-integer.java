/**
 * PROBLEM STATEMENT: 1009. Complement of Base 10 Integer
 * --------------------------------------------------------------------------------
 * The complement of an integer is the integer you get when you flip all the 0's 
 * to 1's and all the 1's to 0's in its binary representation.
 * * Example: 
 * - Input: n = 5 (Binary: "101")
 * - Output: 2 (Binary: "010")
 * * Constraints:
 * 0 <= n < 10^9
 * * Note: This is the same logic as LeetCode 476.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Bitmasking with XOR
 * --------------------------------------------------------------------------------
 * 1. The Logic:
 * Flipping bits is equivalent to performing an XOR operation with a mask 
 * consisting of all 1s. 
 * For example: 101 (5) XOR 111 (7) = 010 (2).
 * * 2. Creating the Mask:
 * We need a mask that has the same number of bits as 'n', but all set to 1.
 * - If n = 5 (101), mask = 111.
 * - If n = 10 (1010), mask = 1111.
 * * 3. Handling the Edge Case:
 * If n = 0, the binary is "0", so the complement is "1".
 * * 4. Algorithm:
 * - Start with `mask = 0`.
 * - While a temporary copy of `n` is greater than 0:
 * - Shift `mask` left by 1 and OR it with 1 (`mask = (mask << 1) | 1`).
 * - Shift `n_copy` right by 1.
 * - Return `n ^ mask`.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log N)
 * - The number of iterations in the while loop is equal to the number of bits 
 * in the binary representation of N.
 * - For an integer up to 10^9, this is at most 30 iterations.
 * * Space Complexity: O(1)
 * - Only a few integer variables are used regardless of the input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0)
         return 1;
        
        int mask = 0;
        int temp = n;
        
        while(temp > 0){
            mask = (mask << 1) | 1;
            temp >>= 1;
        }
        return n ^ mask;
    }
}
