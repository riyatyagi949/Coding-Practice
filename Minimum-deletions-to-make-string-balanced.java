/**
 * PROBLEM STATEMENT: 1653. Minimum Deletions to Make String Balanced
 * --------------------------------------------------------------------------------
 * You are given a string 's' consisting only of characters 'a' and 'b'.
 * A string is balanced if there is no pair of indices (i, j) such that i < j, 
 * s[i] = 'b', and s[j] = 'a'. In other words, all 'a's must come before all 'b's.
 * * Task: Return the minimum number of deletions needed to make 's' balanced.
 * * Example 1:
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: Deleting characters at index 2 and 6 results in "aaabbb".
 * * Example 2:
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: Deleting the first two 'b's results in "aaaaabb".
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: One-Pass Dynamic Programming (Stack Concept)
 * --------------------------------------------------------------------------------
 * 1. The Core Idea:
 * We iterate through the string and keep track of how many 'b's we have seen 
 * so far. A "violation" occurs whenever we encounter an 'a' AFTER we have 
 * already seen some 'b's.
 * * 2. Decision at 'a':
 * When we see an 'a' and we have 'b's stored:
 * - Option A: Delete this 'a' to maintain the balance. This costs 1 deletion.
 * - Option B: Keep this 'a', which means we MUST have deleted all previous 'b's 
 * that came before it.
 * * 3. State Transition:
 * Let 'dp' be the minimum deletions needed for the prefix ending at current index.
 * - If s[i] == 'b': No immediate violation. Increment 'bCount'. dp remains same.
 * - If s[i] == 'a': 
 * We can either delete this 'a' (dp + 1) or keep this 'a' and rely on previous 
 * deletions of 'b's (bCount). 
 * Therefore: dp = min(dp + 1, bCount).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the string exactly once.
 * Space Complexity: O(1)
 * - We only use two integer variables (countB and deletions) regardless of string size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

class Solution {
   public int minimumDeletions(String s) {
        int n = s.length();
        int bCount = 0;
        int deletions = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == 'b') {
               bCount++;
            } 
            else {
               deletions = Math.min(deletions + 1, bCount);
            }
        }
      return deletions;
    }
}
