// #Leetcode - 3783 Mirror Distance of an Integer

// ##  Problem Statement

// Given an integer `n`, define its mirror distance as:

// abs(n - reverse(n))

// Where:
// - `reverse(n)` is the integer formed by reversing the digits of `n`
// - Leading zeros are ignored after reversing

// Return the mirror distance of `n`.

// ##  Examples

// ### Example 1
// Input: n = 25  
// Output: 27  

// Explanation:  
// reverse(25) = 52  
// abs(25 - 52) = 27  


// ### Example 2
// Input: n = 10  
// Output: 9  

// Explanation:  
// reverse(10) = 1  
// abs(10 - 1) = 9  

// ### Example 3
// Input: n = 7  
// Output: 0  

// Explanation:  
// reverse(7) = 7  
// abs(7 - 7) = 0  


// ##  Approach

// 1. Reverse the digits of the given number
// 2. Compute the absolute difference between the number and its reverse
// 3. Return the result

// ---

##  Java Solution

  class Solution {
    public int mirrorDistance(int n) {
        int original = n ;
        int reverse = 0;

        while(n > 0){
            int digit = n % 10;
            reverse = reverse * 10 + digit ;
            n /= 10;
        }
        return Math.abs(original - reverse);
        
    }
}



// ##  Complexity

// * Time Complexity: O(d), where d = number of digits in n
// * Space Complexity: O(1)

