// Problem Statement:
// Given n friends, each can remain single or be paired up. 
// Each friend can be paired at most once. Find the total number of ways.
// Constraints: 1 <= n <= 18

// Optimal Solution in Java:
// The recurrence relation is: f(n) = f(n - 1) + (n - 1) * f(n - 2)
// - f(n - 1): The nth friend stays single.
// - (n - 1) * f(n - 2): The nth friend pairs with any of the (n - 1) other friends.
// Runtime: O(n)
// Space Complexity: O(1)

class Solution {
    public int countFriendsPairings(int n) {
        // Base cases: 
        // For n=1, only 1 way.
        // For n=2, 2 ways ({1}, {2} or {1, 2}).
        if (n <= 2) {
            return n;
        }

        // prev2 represents f(n-2), prev1 represents f(n-1)
        int prev2 = 1; // f(1)
        int prev1 = 2; // f(2)

        // Iterate from 3 to n to build the solution
        for (int i = 3; i <= n; i++) {
            // f(i) = f(i - 1) + (i - 1) * f(i - 2)
            int curr = prev1 + (i - 1) * prev2;
            
            // Update values for the next iteration
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}
