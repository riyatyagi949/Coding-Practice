/*
 * ==========================================
 * PROBLEM STATEMENT: Product of the Last K Numbers
 * ==========================================
 * Design a `ProductOfNumbers` class supporting:
 * - `ProductOfNumbers()`: Initializes an empty stream.
 * - `void add(int num)`: Appends `num` to the stream.
 * - `int getProduct(int k)`: Returns the product of the last `k` numbers in the stream
 *   (guaranteed at least `k` numbers exist; products fit in a 32-bit signed integer).
 *
 * Constraints:
 * 0 <= num <= 100
 * 1 <= k <= 4 * 10^4
 * At most 4 * 10^4 calls to `add` and `getProduct`.
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prefix Products with Zero-Reset)
 * ==========================================
 * - Approach:
 *   1. Store running prefix products of non-zero elements in `prefix`, starting with `[1]`.
 *   2. When `add(num)` is called with `num == 0`: any window spanning across a zero has
 *      product `0`, so reset `prefix` to `[1]`. This cleanly avoids division by zero.
 *   3. Otherwise, append `last * num` to `prefix`.
 *   4. For `getProduct(k)`:
 *      - Let `n = prefix.size()`. Because `prefix` holds `1` plus the count of elements
 *        since the last zero, `k >= n` means the last `k` elements include a zero, so return `0`.
 *      - Otherwise, all `k` elements lie after the last zero, so the product is
 *        `prefix.get(n - 1) / prefix.get(n - 1 - k)`.
 *
 * - Complexity:
 *   - Time Complexity: O(1) amortized for `add` and O(1) worst-case for `getProduct`.
 *   - Space Complexity: O(N) where `N` is the number of elements since the last zero (up to `4 * 10^4`).
 */

import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            prefix.clear();
            prefix.add(1);
        } else {
            int last = prefix.get(prefix.size() - 1);
            prefix.add(last * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();
        if (k >= n) {
            return 0;
        }
        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}
