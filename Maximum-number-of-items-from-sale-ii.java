/*
 * ==========================================
 * PROBLEM STATEMENT: Maximum Number of Items From Sale II
 * ==========================================
 * Given `items` where `items[i] = [factor_i, price_i]` and an integer `budget`:
 * - Unlimited copies of each item exist; total purchase cost must be at most `budget`.
 * - Each purchased copy of item `i` can grant at most one free copy of another item `j`
 *   such that `j != i` and `factor_i` divides `factor_j`.
 * - For each ordered pair `(i, j)`, a free copy of `j` from purchases of `i` can be
 *   received at most once regardless of how many copies of `i` are bought, while different
 *   purchased item types `i` can each grant a free copy of `j`.
 * Return the maximum total number of item copies (purchased + free) obtainable within `budget`.
 *
 * Constraints:
 * 1 <= items.length <= 10^5
 * items[i] = [factor_i, price_i]
 * 1 <= factor_i <= items.length
 * 1 <= price_i <= 10^9
 * 1 <= budget <= 10^9
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Harmonic Divisor Count + Greedy Boost)
 * ==========================================
 * - Approach:
 *   1. Count how many items have each `factor` (`countFactor`), and find `minPrice`
 *      and `maxFactor`.
 *   2. For every `factor`, sum `countFactor[j]` over multiples `j = factor, 2*factor, ...`
 *      and subtract `1` (`gain[factor]`) to get the number of other items `j` with
 *      `factor` dividing `factor_j`.
 *   3. Buying 1 copy of an item with `gain[factor] > 0` gives `1` purchased item plus
 *      `1` free item (up to `gain[factor]` distinct free items across copies of that
 *      item type), i.e., `2` items for `price`. Any candidate with `price > 2 * minPrice`
 *      cannot beat buying `>= 2` copies of the cheapest item (`minPrice`).
 *   4. Sort eligible boosted items by `price` ascending, greedily buy up to
 *      `min(available, remainingBudget / price)` copies (`+2` items each), then spend any
 *      leftover budget on the cheapest item (`remainingBudget / minPrice` items).
 *
 * - Complexity:
 *   - Time Complexity: O(n log n + M log M), where `n = items.length` and `M = maxFactor <= n`
 *     (harmonic sum over multiples is `O(M log M)`).
 *   - Space Complexity: O(n) for frequency/gain arrays and the boosted candidate list.
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] countFactor = new int[n + 1];
        long minPrice = Long.MAX_VALUE;
        int maxFactor = 0;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];

            countFactor[factor]++;
            if (price < minPrice) {
                minPrice = price;
            }
            if (factor > maxFactor) {
                maxFactor = factor;
            }
        }

        // gain[f] = count of items with factor divisible by f, excluding one self-match
        int[] gain = new int[maxFactor + 1];
        for (int factor = 1; factor <= maxFactor; factor++) {
            for (int j = factor; j <= maxFactor; j += factor) {
                gain[factor] += countFactor[j];
            }
            gain[factor]--;
        }

        List<long[]> boosted = new ArrayList<>();
        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];

            if (gain[factor] > 0 && price <= 2L * minPrice) {
                boosted.add(new long[]{price, gain[factor]});
            }
        }

        boosted.sort((a, b) -> Long.compare(a[0], b[0]));

        long ans = 0;
        long remainingBudget = budget;

        for (long[] entry : boosted) {
            long price = entry[0];
            long available = entry[1];
            long count = Math.min(available, remainingBudget / price);

            ans += count * 2L;
            remainingBudget -= count * price;
        }

        ans += remainingBudget / minPrice;
        return (int) ans;
    }
}
