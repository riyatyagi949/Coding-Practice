/**
 * PROBLEM STATEMENT: 67. Add Binary
 * --------------------------------------------------------------------------------
 * Given two binary strings 'a' and 'b', return their sum as a binary string.
 * * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * * Constraints:
 * - 1 <= a.length, b.length <= 10^4
 * - 'a' and 'b' consist only of '0' or '1' characters.
 * - Each string does not contain leading zeros except for the zero itself.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single Pass Simulation
 * --------------------------------------------------------------------------------
 * 1. Two Pointers: 
 * We use two pointers (i and j) starting from the end of both strings to 
 * simulate manual addition from the least significant bit (right to left).
 * * 2. Carry Logic:
 * At each step, we calculate the sum of the digits at the current positions 
 * plus any carry from the previous step.
 * - sum = bitA + bitB + carry
 * - New digit to append = sum % 2 (result is 0 or 1)
 * - New carry = sum / 2 (carry is 1 if sum was 2 or 3, else 0)
 * * 3. Loop Condition:
 * We continue as long as there are digits left in 'a' OR digits left in 'b' 
 * OR there is a remaining 'carry' of 1.
 * * 4. Result Construction:
 * Using a StringBuilder to append bits is efficient. Since we append from 
 * right to left, we must reverse the final string before returning.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(max(N, M))
 * - We iterate through the strings once, where N and M are the lengths of 
 * strings 'a' and 'b' respectively.
 * * Space Complexity: O(max(N, M))
 * - The StringBuilder stores the result, which at most has a length of 
 * max(N, M) + 1.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        
        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;
            
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
             if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            result.append(sum % 2);
            carry = sum / 2;
        }
         return result.reverse().toString();
    }
}


