/**
 * PROBLEM STATEMENT: 1545. Find Kth Bit in Nth Binary String
 * --------------------------------------------------------------------------------
 * Binary string Sn is formed as follows:
 * - S1 = "0"
 * - Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1
 * * Given n and k, return the kth bit in Sn.
 * * Examples:
 * Input: n = 3, k = 1 -> Output: "0" (S3 = "0111001")
 * Input: n = 4, k = 11 -> Output: "1" (S4 = "011100110110001")
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: RECURSIVE DECOMPOSITION (DIVIDE & CONQUER)
 * --------------------------------------------------------------------------------
 * Instead of constructing the string (which grows exponentially to 2^20 - 1), 
 * we observe the structure of Sn: [Left Part (Sn-1)] + [Middle "1"] + [Right Part].
 * * The length of Sn is always (2^n - 1).
 * 1. If k is exactly the middle element: The middle is always "1" for n > 1.
 * 2. If k is in the left part (k < mid): The bit is the same as the kth bit in Sn-1.
 * 3. If k is in the right part (k > mid): 
 * - The right part is reverse(invert(Sn-1)).
 * - Because it is reversed, index k in Sn corresponds to index (Length - k + 1) 
 * in the original Sn-1.
 * - Because it is inverted, we must flip the bit returned from the recursion.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - In each recursive step, we reduce n by 1. 
 * - We perform constant time operations (arithmetic) at each level.
 * - Total steps: n levels.
 * * Space Complexity: O(N)
 * - Due to the recursion stack depth of n.
 * --------------------------------------------------------------------------------
 */

// Optimal Solution in Java - 

class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) return '0';
        
        int length = (1 << n) - 1;
        int mid = (length / 2) + 1;
        
        if (k == mid) 
          return '1';
        
        if (k < mid) {
            return findKthBit(n - 1, k);
        }
         else {
            int mirror = length - k + 1;
            char bit = findKthBit(n - 1, mirror);
            
            return bit == '0' ? '1' : '0'; 
        }
    }
}

