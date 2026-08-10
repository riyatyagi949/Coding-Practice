/*
 * ==========================================
 * PROBLEM STATEMENT: High Effort vs Low Effort
 * ==========================================
 * Given two integer arrays h[] and l[], where h[i] and l[i] denote the number of tasks 
 * that can be completed on the i-th day by performing a high-effort task and a low-effort task, respectively.
 * 
 * For each day, you may choose exactly one of the following options:
 * 1. Perform no task.
 * 2. Perform a low-effort task.
 * 3. Perform a high-effort task, which can only be performed on the first day or if no task was performed on the previous day.
 * 
 * Return the maximum total number of tasks that can be completed over all days.
 * 
 * Constraints:
 * 1 <= h.size() <= 10^5
 * 0 <= h[i] <= 10^3
 * 1 <= l.size() <= 10^5
 * 0 <= l[i] <= 10^3
 * l.size() = h.size()
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming - Space Optimized)
 * ==========================================
 * - Approach: We maintain three states for the previous day:
 *   1. prevNo: Maximum tasks up to the previous day ending with "no task".
 *   2. prevLow: Maximum tasks up to the previous day ending with a "low-effort task".
 *   3. prevHigh: Maximum tasks up to the previous day ending with a "high-effort task".
 * 
 * - State Transitions for the current day i:
 *   - 'no': If we perform no task today, we could have come from any state on the previous day. 
 *     Thus, no = max(prevNo, prevLow, prevHigh).
 *   - 'low': If we perform a low-effort task today, there are no special restrictions on the previous day. 
 *     Thus, low = max(prevNo, prevLow, prevHigh) + l[i].
 *   - 'high': If we perform a high-effort task today, the previous day *must* have been a "no task" state. 
 *     Thus, high = prevNo + h[i].
 * 
 * - Complexity:
 *   - Time Complexity: O(n), as we iterate through the arrays of size n once.
 *   - Space Complexity: O(1), since we only use a constant amount of variables to track the states.
 */

class Solution {
    public int maxTask(int[] h, int[] l) {
        int n = h.length;

        // Base states for the first day (index 0)
        int prevNo = 0;
        int prevLow = l[0];
        int prevHigh = h[0];

        // Iterate through the remaining days
        for (int i = 1; i < n; i++) {
            // Best possible state from the previous day can transition into 'no' or 'low' today
            int bestPrev = Math.max(prevNo, Math.max(prevLow, prevHigh));

            int no = bestPrev;
            int low = bestPrev + l[i];
            
            // High-effort task can only be performed if no task was performed on the previous day
            int high = prevNo + h[i];

            // Update states for the next iteration
            prevNo = no;
            prevLow = low;
            prevHigh = high;
        }

        // Return the maximum tasks possible across all ending states on the last day
        return Math.max(prevNo, Math.max(prevLow, prevHigh));
    }
}
