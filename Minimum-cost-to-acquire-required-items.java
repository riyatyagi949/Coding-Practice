/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Cost to Acquire Required Items
 * ==========================================
 * Given five integers `cost1`, `cost2`, `costBoth`, `need1`, and `need2`.
 * - Item type 1 costs `cost1` and satisfies 1 unit of requirement 1.
 * - Item type 2 costs `cost2` and satisfies 1 unit of requirement 2.
 * - Item type 3 costs `costBoth` and satisfies 1 unit of *both* requirements.
 * Return the minimum possible total cost to achieve at least `need1` and `need2`.
 * 
 * Constraints:
 * 1 <= cost1, cost2, costBoth <= 10^6
 * 0 <= need1, need2 <= 10^9
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Greedy Optimization)
 * ==========================================
 * - Approach:
 *   1. Check if buying individual items (`cost1 + cost2`) is cheaper than buying a combined item (`costBoth`). 
 *      If so, we can simply buy `need1` of item 1 and `need2` of item 2 without using any combined items.
 *   2. Otherwise, utilize as many type 3 (combined) items as possible up to `min(need1, need2)` since 
 *      it's cheaper than buying both individual items separately.
 *   3. For whichever requirement is remaining (`need1` or `need2`), greedily choose the minimum cost between 
 *      buying the individual item (`cost1` or `cost2`) and buying an extra type 3 item (`costBoth`).
 * 
 * - Complexity:
 *   - Time Complexity: O(1) constant time since we only perform basic arithmetic comparisons and operations.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // If buying individual items is cheaper than buying a combined item
        if (cost1 + cost2 <= costBoth) {
            return 1L * cost1 * need1 + 1L * cost2 * need2;
        }
        
        // Use combined items for the overlapping requirement
        int both = Math.min(need1, need2);
        long total = 1L * both * costBoth;

        need1 -= both;
        need2 -= both;

        // Satisfy any leftover requirement 1 using the cheaper option
        if (need1 > 0) {
            total += 1L * need1 * Math.min(cost1, costBoth);
        }
        // Satisfy any leftover requirement 2 using the cheaper option
        else if (need2 > 0) {
            total += 1L * need2 * Math.min(cost2, costBoth);
        }
        
        return total;
    }
}
