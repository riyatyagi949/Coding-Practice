/*
 * ==========================================
 * PROBLEM STATEMENT: Process Tasks Using Servers
 * ==========================================
 * Given `servers` (weights) and `tasks` (processing times), assign each task sequentially 
 * to a free server. If multiple servers are free, choose the one with the smallest weight, 
 * then smallest index. If no servers are free, wait until the earliest server becomes free.
 * Return an array `ans` of length `m` where `ans[j]` is the server index assigned to task `j`.
 * 
 * Constraints:
 * 1 <= n, m <= 2 * 10^5
 * 1 <= servers[i], tasks[j] <= 2 * 10^5
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Double Priority Queues)
 * ==========================================
 * - Approach:
 *   1. Use two Priority Queues:
 *      - `free`: Stores available servers, prioritized by weight (ascending), then index (ascending).
 *      - `busy`: Stores currently working servers, prioritized by their release time (ascending), 
 *        then weight, then index.
 *   2. For each task at second `j`:
 *      - Advance time to at least `j`.
 *      - Free up any servers from the `busy` queue whose release time `<= time`.
 *      - If no servers are free (`free.isEmpty()`), fast-forward time directly to the earliest 
 *        release time from the `busy` queue and pop all servers becoming free at that time.
 *      - Assign the task to the top server from `free`, record its assigned index, and push it 
 *        into `busy` with its new release time (`time + tasks[j]`).
 * 
 * - Complexity:
 *   - Time Complexity: O((n + m) log n), since each server is pushed and popped from heaps efficiently.
 *   - Space Complexity: O(n + m) to store heaps and the answer array.
 */

import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // free queue: prioritizes by weight (ascending), then server index (ascending)
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // busy queue: prioritizes by release time (ascending), then weight, then index
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Long.compare(a[0], b[0]);
            if (a[1] != b[1])
                return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }
        
        long time = 0;

        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);

            // Move servers from busy to free if their task is completed by current time
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();
                free.offer(new int[]{ (int) server[1], (int) server[2] });
            }

            // If no free servers are available, jump time to when the next server becomes free
            if (free.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();
                    free.offer(new int[]{ (int) server[1], (int) server[2] });
                }
            }

            int[] server = free.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            long freeTime = time + tasks[j];

            busy.offer(new long[]{freeTime, weight, index});
        }
        
        return ans;
    }
}
