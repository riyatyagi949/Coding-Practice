/**
 * PROBLEM STATEMENT: 2144. Minimum Cost of Buying Candies With Discount
 * --------------------------------------------------------------------------------
 * You are given an array 'cost' where cost[i] is the price of the ith candy.
 * - Rule: For every two candies you buy, you get a third candy for free.
 * - Constraint: The free candy must have a cost <= the minimum cost of the 
 * two candies you bought.
 * - Goal: Minimize the total cost to acquire all candies.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY STRATEGY
 * --------------------------------------------------------------------------------
 * 1. Sorting: To minimize cost, we want to get the most expensive candies for free 
 * whenever possible. Sort the costs in descending order.
 * 2. Greedy Loop:
 * - Process candies in groups of three.
 * - In each group [max, second_max, third_max], buy the two most expensive
 * (max, second_max) and get the third (third_max) for free.
 * - This strategy ensures we always skip the cheapest available candy in 
 * every triplet, which is mathematically optimal for minimizing total spent.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Sorting the array takes O(N log N).
 * - Iterating through the array takes O(N).
 * Space Complexity: O(1) (or O(log N) depending on sort implementation)
 * - We only use a few extra variables for the running sum.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

//Approach-1 (Sorting + Greedy Skip Every 3rd)
//T.C : O(n log n)
//S.C : O(1)
class Solution {
    public int minimumCost(int[] cost) {
        int n = cost.length;
        Arrays.sort(cost);

        // Reverse the array to sort in descending order
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            int temp = cost[l];
            cost[l] = cost[r];
            cost[r] = temp;
        }

        int total = 0;

        for (int i = 0; i < n; i++) {
            if (i % 3 != 2)
                total += cost[i];
        }
        return total;
    }
}
