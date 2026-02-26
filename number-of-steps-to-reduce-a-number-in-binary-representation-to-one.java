/**
 * PROBLEM STATEMENT: 1404. Number of Steps to Reduce a Number in Binary Representation to One
 * --------------------------------------------------------------------------------
 * Given the binary representation of an integer as a string 's', return the 
 * number of steps to reduce it to 1 under the following rules:
 * 1. If the current number is even, divide it by 2.
 * 2. If the current number is odd, add 1 to it.
 *
 * * Example 1:
 * Input: s = "1101" (Decimal 13)
 * Output: 6
 * Explanation: 13(O)->14(E)->7(O)->8(E)->4(E)->2(E)->1. Total 6 steps.
 *
 * * Constraints:
 * - 1 <= s.length <= 500
 * - s[0] == '1'
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single-Pass Greedy with Carry
 * --------------------------------------------------------------------------------
 * Instead of simulating the string manipulation (which is expensive), we can 
 * process the string from right to left (least significant bit to most).
 * * Logic:
 * 1. We start from the end of the string (index s.length - 1) up to index 1.
 * 2. We maintain a 'carry' variable to handle the "Add 1" operations.
 * 3. For each bit:
 * - If (bit + carry) == 0: The number is even. Dividing by 2 is just 1 step 
 * (shifting). Carry remains 0.
 * - If (bit + carry) == 1: The number is odd. Adding 1 makes it even, and 
 * then we divide by 2. This takes 2 steps. Carry becomes 1.
 * - If (bit + carry) == 2: The number was 1 and we had a carry of 1. It acts 
 * like an even number (1+1=10 in binary). It takes 1 step (division) 
 * and carry remains 1.
 * 4. Final step: After the loop, if carry is 1, it means the MSB (which is 
 * always '1') became '10' (2), requiring one last division to reach '1'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the string once from right to left.
 * Space Complexity: O(1)
 * - We only use a few integer variables regardless of the input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        for (int i = s.length() - 1; i > 0; i--) {
            int bit = (s.charAt(i) - '0') + carry;

            if (bit == 1) {
                steps += 2; 
                carry = 1;   
            } 
            else {
                steps += 1;  
            }
        }
        return steps + carry;  
    }
}

