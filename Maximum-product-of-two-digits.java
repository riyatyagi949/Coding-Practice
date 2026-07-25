// Problem Statement:
// You are given a positive integer n (where 10 <= n <= 10^9). 
// Return the maximum product of any two digits in n.
// You may use the same digit twice if it appears more than once in n.

// Optimal Solution in Java:
// Runtime: 0 ms
// Time Complexity: O(log10(n)) - since we process each digit of the number.
// Space Complexity: O(1) - only a few variables are used.

class Solution {
    public int maxProduct(int n) {
        // Variables to store the two largest digits found in the number
        int first = 0, second = 0;

        // Loop through each digit of the integer from right to left
        while (n > 0) {
            int digit = n % 10;

            // Update the top two largest digits dynamically
            if (digit >= first) {
                second = first;
                first = digit;
            } 
            else if (digit > second) {
                second = digit;
            }

            // Remove the last digit
            n /= 10;
        }
        
        // Return the product of the two largest digits
        return first * second;
    }
}
