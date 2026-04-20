/**
 * PROBLEM STATEMENT: Count Derangements
 * --------------------------------------------------------------------------------
 * A Derangement is a permutation of n elements such that no element appears 
 * in its original position. 
 * - For n = 1: No derangement possible (0).
 * - For n = 2: Only [2, 1] is a derangement (1).
 * - For n = 3: [2, 3, 1] and [3, 1, 2] are derangements (2).
 * * Goal: Given a number n, find the total number of derangements of n elements.
 * * Note: The result will fit in a 32-bit integer for the given constraints (n <= 12).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DYNAMIC PROGRAMMING (RECURRENCE RELATION)
 * --------------------------------------------------------------------------------
 * Let D(n) be the number of derangements for n elements.
 * Suppose we want to place the first element '1'. It can be placed in any of the 
 * (n - 1) positions (from 2 to n).
 * * Let's say we place '1' in position 'i'. Now there are two possibilities for 'i':
 * 1. Element 'i' is placed in position '1':
 * - The two elements (1 and i) are swapped.
 * - We now need to derange the remaining (n - 2) elements.
 * - Result: D(n - 2)
 * * 2. Element 'i' is NOT placed in position '1':
 * - This is equivalent to deranging (n - 1) elements (treating position 1 
 * as the "forbidden" spot for element i).
 * - Result: D(n - 1)
 * * Total Recurrence: D(n) = (n - 1) * [D(n - 1) + D(n - 2)]
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We iterate from 3 up to n once.
 * Space Complexity: O(1)
 * - Instead of an array, we use two variables (prev1, prev2) to track state.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

class Solution {
    public int derangeCount(int n) {
        if (n == 1)
          return 0;
        if (n == 2) 
          return 1;
        
        int prev2 = 0, prev1 = 1;
        
        for (int i = 3; i <= n; i++) {
            int curr = (i - 1) * (prev1 + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
       return prev1;
    }
}

