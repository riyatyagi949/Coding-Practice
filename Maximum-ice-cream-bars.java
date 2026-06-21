/**
 * PROBLEM STATEMENT: 1833. Maximum Ice Cream Bars
 * --------------------------------------------------------------------------------
 * You are given an array 'costs' where costs[i] is the price of the ith ice cream 
 * bar, and an integer 'coins' representing your total budget.
 * Return the maximum number of ice cream bars you can buy.
 *
 * * Key Constraint:
 * You must solve the problem using Counting Sort.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Counting Sort (Greedy)
 * --------------------------------------------------------------------------------
 * 1. Since costs are limited (1 <= costs[i] <= 10^5), we can use a frequency 
 * array (bucket sort approach) to count occurrences of each price.
 * 2. By iterating through the frequency array from the smallest price to the 
 * largest, we effectively simulate a sorted order of costs without the O(N log N) 
 * cost of comparison-based sorting.
 * 3. We greedily subtract each price from 'coins' as long as we can afford it.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N + M), where N is the number of ice cream bars and M 
 * is the maximum cost (10^5). We iterate through the array once and then 
 * through the frequency buckets once.
 * Space Complexity: O(M), to store the frequency counts of prices.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Find the maximum cost to define the size of the frequency array
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Frequency array to store count of each cost
        int[] count = new int[maxCost + 1];
        for (int cost : costs) {
            count[cost]++;
        }

        int iceCreamCount = 0;
        // Greedily buy the cheapest ice cream bars first
        for (int price = 1; price <= maxCost; price++) {
            // While we have ice cream bars of this price
            while (count[price] > 0 && coins >= price) {
                coins -= price;
                iceCreamCount++;
                count[price]--;
            }
            
            // Optimization: if we can't afford the current price, 
            // and we know all remaining ice creams are more expensive,
            // we can stop early (though strictly speaking the inner while 
            // handles this logic).
            if (coins < price) {
                break;
            }
        }

        return iceCreamCount;
    }
}
