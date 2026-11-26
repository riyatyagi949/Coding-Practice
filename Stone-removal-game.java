/*
 * ==========================================
 * PROBLEM STATEMENT: Stone Removal Game
 * ==========================================
 * Alice and Bob take turns removing stones from a pile of `n` stones, with Alice going first.
 * Alice removes `10` stones on turn 1, and each subsequent turn removes `1` fewer stone
 * (`10, 9, 8, ...`). The player who cannot make a move loses.
 * Given a positive integer `n`, return `true` if Alice wins and `false` otherwise.
 *
 * Constraints:
 * 1 <= n <= 50
 *
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Turn Simulation)
 * ==========================================
 * - Approach:
 *   1. Start with `take = 10` stones for the first move and `steps = 0` completed turns.
 *   2. While `n >= take` and `take > 0`, subtract `take` from `n`, decrement `take`,
 *      and increment `steps`.
 *   3. Alice takes odd-numbered turns (`1, 3, 5, ...`) and Bob takes even-numbered turns
 *      (`2, 4, 6, ...`). Since the last player to make a valid move wins, Alice wins if and
 *      only if `steps` is odd.
 *
 * - Complexity:
 *   - Time Complexity: O(1) — at most `10` iterations regardless of `n` (`take` decreases from `10` down to `1`).
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        int steps = 0;

        while (n >= take && take > 0) {
            n -= take;
            take--;
            steps++;
        }

        return steps % 2 == 1;
    }
}
