/**
 * PROBLEM STATEMENT: 762. Prime Number of Set Bits in Binary Representation
 * --------------------------------------------------------------------------------
 * Given two integers 'left' and 'right', return the count of numbers in the 
 * inclusive range [left, right] having a prime number of set bits in their 
 * binary representation.
 * * Recall:
 * 1. Set bits: The number of 1's in the binary form of an integer.
 * 2. Prime numbers: A number greater than 1 that has no positive divisors 
 * other than 1 and itself.
 * * Example:
 * Input: left = 6 (110), right = 10 (1010)
 * 6 -> 2 bits (Prime)
 * 7 -> 3 bits (Prime)
 * 8 -> 1 bit (Not Prime)
 * 9 -> 2 bits (Prime)
 * 10 -> 2 bits (Prime)
 * Output: 4
 * * Constraints:
 * 1 <= left <= right <= 10^6
 * 0 <= right - left <= 10^4
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Iterative Bit Counting with Prime Check
 * --------------------------------------------------------------------------------
 * 1. Range Iteration:
 * Since the difference (right - left) is at most 10,000, we can safely iterate 
 * through every number in the range.
 * * 2. Counting Set Bits:
 * We use the built-in `Integer.bitCount(n)` method, which implements a very 
 * efficient algorithm (often a single CPU instruction or a small number of 
 * bitwise operations) to count set bits.
 * * 3. Prime Check:
 * Since the maximum value of 'right' is 10^6, the maximum number of set bits is 
 * small (2^20 > 10^6, so max bits < 20).
 * Primes less than 20: {2, 3, 5, 7, 11, 13, 17, 19}.
 * We can use a helper function or a bitmask/set to check if the count is prime.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(D) where D = (right - left + 1)
 * - We iterate D times. 
 * - Integer.bitCount() and the prime check for numbers < 32 are O(1).
 * Space Complexity: O(1)
 * - No extra data structures are used regardless of the range size.
 * --------------------------------------------------------------------------------
 */

// Optimal Solution in Java - 

class Solution {
  public int countPrimeSetBits(int left, int right) {
        int count = 0;
        
        for(int num = left; num <= right; num++) {
            int setBits = Integer.bitCount(num);
             if(isPrime(setBits)) {
                count++;
            }
        }
      return count;
    }
    private boolean isPrime(int n) {
         if(n < 2) 
         return false;
        
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0)
            return false;
        }
         return true;
    }
}

