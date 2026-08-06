// Problem Statement:
// You are given two integers n and t. 
// Return the smallest number greater than or equal to n such that the product of its digits is divisible by t.
// Constraints: 1 <= n <= 100, 1 <= t <= 10.

// Optimal Solution in Java:
// Runtime: 0 ms
// Time Complexity: O((N - initial_N + 1) * log10(N)) - extremely fast since the constraints on n are very small (1 <= n <= 100), meaning the loop will execute at most a few times.
// Space Complexity: O(1) - constant space used for calculation.

class Solution {
    public int smallestNumber(int n, int t) {
        // Continuously check numbers starting from n upwards
        while (true) {
            if (digitProduct(n) % t == 0) {
                return n;
            }
            n++;
        }
    }
  // Helper method to compute the product of the digits of a number
    private int digitProduct(int num) {
        int product = 1;

        while (num > 0) {
            product *= (num % 10);
            num /= 10;
        }
        return product;
    }
}
