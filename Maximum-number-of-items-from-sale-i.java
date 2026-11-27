/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Number of Items From Sale I
 * ==========================================
 * Given `items` where `items[i] = [factor_i, price_i]` and an integer `budget`:
 * - Unlimited copies of each item exist; total purchase cost must be at most `budget`.
 * - Buying at least one copy of item `i` grants one free copy of every item `j != i`
 *   such that `factor_i` divides `factor_j` (additional copies of `i` give no extra freebies;
 *   duplicate freebies from different purchased item types all count).
 * Return the maximum total number of item copies (purchased + free) obtainable within `budget`.
 *
 * Constraints:
 * 1 <= items.length <= 1000
 * 1 <= factor_i, price_i <= 1500
 * 1 <= budget <= 1500
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Divisor Bonus + 0/1 Knapsack + Remaining Budget Greedy)
 * ==========================================
 * - Approach:
 *   1. For each item `i`, count `copies[i]`, the number of other items `j != i` whose
 *      `factor_j` is divisible by `items[i][0]`. Track `minPrice` across all items.
 *   2. Buying item `i` at least once gives `1` purchased copy plus `copies[i]` free copies
 *      (`1 + copies[i]` total items for `price_i`).
 *   3. Use 0/1 DP (`t[i][b]`) over item types `0..i-1` with budget `b` to choose which items
 *      get at least one purchased copy (`1 + copies[i] + t[i - 1][b - price]`), while
 *      initializing `t[0][b] = b / minPrice` to represent spending leftover budget on
 *      additional copies of the cheapest item (which yield `+1` copy each without
 *      double-counting divisor bonuses).
 *
 * - Complexity:
 *   - Time Complexity: O(n^2 + n * budget) for divisor counting and DP table filling.
 *   - Space Complexity: O(n * budget) for the DP table and `O(n)` for `copies`.
 */

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] copies = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
            int copy = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    copy++;
                }
            }

            copies[i] = copy;
        }

        int[][] t = new int[n + 1][budget + 1];

        // Any remaining budget can buy extra copies of the cheapest item (1 item per minPrice)
        for (int b = 0; b <= budget; b++) {
            t[0][b] = b / minPrice;
        }

        for (int i = 1; i <= n; i++) {
            int price = items[i - 1][1];
            int bonus = 1 + copies[i - 1];

            for (int b = 0; b <= budget; b++) {
                int skip = t[i - 1][b];
                int take = (price <= b) ? bonus + t[i - 1][b - price] : 0;
                t[i][b] = Math.max(take, skip);
            }
        }

        return t[n][budget];
    }
}
