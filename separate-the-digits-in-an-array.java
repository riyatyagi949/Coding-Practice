/**
 * PROBLEM STATEMENT: 2553. Separate the Digits in an Array
 * --------------------------------------------------------------------------------
 * Given an array of positive integers 'nums', return an array 'answer' that 
 * consists of the digits of each integer in 'nums' after separating them in the 
 * same order they appear in 'nums'.
 * 
 * To separate the digits of an integer is to get all the digits it has in the 
 * same order. For example, for 10921, the separation is [1,0,9,2,1].
 * 
 * Constraints:
 * - 1 <= nums.length <= 1000
 * - 1 <= nums[i] <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: LINEAR SCAN AND DIGIT EXTRACTION
 * --------------------------------------------------------------------------------
 * 1. Digit Extraction: Since we need digits in the original order, we can either:
 *    a) Convert each number to a String and iterate through characters.
 *    b) Use a Stack/Recursion to peel digits from right-to-left (using % 10) 
 *       and then push them in correct order.
 * 2. Collection: Use a dynamic list (ArrayList) to store all digits as we 
 *    traverse the 'nums' array from left to right.
 * 3. Final Conversion: Convert the ArrayList back into a primitive int array.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * K)
 * - N is the length of the 'nums' array.
 * - K is the average number of digits in each number (maximum 6 since nums[i] <= 10^5).
 * - Total time is proportional to the total number of digits in the input.
 * 
 * Space Complexity: O(D)
 * - D is the total number of digits in all integers combined.
 * - We store all digits in a list before returning the final array.
 * --------------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> digitList = new ArrayList<>();
        
        for (int num : nums) {
            // We need to extract digits in order.
            // Using a temporary list/stack to handle right-to-left extraction.
            List<Integer> currentDigits = new ArrayList<>();
            while (num > 0) {
                currentDigits.add(num % 10);
                num /= 10;
            }
            
            // The currentDigits list is in reverse order, add to main list backwards
            for (int i = currentDigits.size() - 1; i >= 0; i--) {
                digitList.add(currentDigits.get(i));
            }
        }
        
        // Convert List<Integer> to int[]
        int[] result = new int[digitList.size()];
        for (int i = 0; i < digitList.size(); i++) {
            result[i] = digitList.get(i);
        }
        
        return result;
    }

  // ALTERNATIVE APPROACH (Shorter code using String conversion):
  class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        
        for (int num : nums) {
            String s = String.valueOf(num);
            
            for (char c : s.toCharArray()) {
                list.add(c - '0');
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}
     
