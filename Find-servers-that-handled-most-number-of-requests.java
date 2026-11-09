/*
 * ==========================================
 * PROBLEM STATEMENT: Find Servers That Handled Most Number of Requests
 * ==========================================
 * You have `k` servers numbered from `0` to `k-1`. Requests arrive sequentially. 
 * The `i`-th request tries to use server `(i % k)` or the next available server (wrapping around). 
 * If all servers are busy, the request is dropped. 
 * Find the busiest server(s) that handled the maximum number of successful requests.
 * 
 * Constraints:
 * 1 <= k <= 10^5
 * 1 <= arrival.length, load.length <= 10^5
 * arrival is strictly increasing.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (TreeSet + PriorityQueue)
 * ==========================================
 * - Approach:
 *   1. Use a `TreeSet<Integer>` (`availableServers`) to keep track of all currently free server IDs. 
 *      This allows us to quickly find the next available server using `.ceiling(target)` in O(log k) time.
 *   2. Use a `PriorityQueue<int[]>` (`busyServers`) to track servers currently handling requests, 
 *      prioritized by their completion time (`[releaseTime, serverId]`).
 *   3. For each request at index `i`:
 *      - Free up all servers whose request completion time `<= arrival[i]` and add them back to the `availableServers` TreeSet.
 *      - If no servers are available, the request is dropped.
 *      - Otherwise, search for the target server `i % k` using `availableServers.ceiling(target)`. 
 *        If `null` is returned, wrap around to the first available server using `availableServers.first()`.
 *      - Assign the request, remove the server from the TreeSet, add it to the busy priority queue with its new release time, 
 *        and increment its request count.
 *   4. Finally, find the maximum requests handled and return all servers that match this count.
 * 
 * - Complexity:
 *   - Time Complexity: O(n log k), where `n` is the number of requests and `k` is the number of servers, 
 *     since TreeSet and PriorityQueue operations take logarithmic time.
 *   - Space Complexity: O(n + k) to store server states, heaps, and counts.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TreeSet to maintain currently available servers in sorted order
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i); 
        }
        
        // PriorityQueue to track busy servers ordered by release time
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> 
            a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
        );

        int[] countRequests = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];

            // Free up servers that have completed their tasks by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                int server = busyServers.poll()[1];
                availableServers.add(server);
            }
            
            // If all servers are busy, drop the request
            if (availableServers.isEmpty()) {
                continue; 
            }
            
            int target = i % k;
            Integer targetServer = availableServers.ceiling(target);

            // If no server >= target is available, wrap around to the first available server
            if (targetServer == null) { 
                targetServer = availableServers.first();
            }
            
            availableServers.remove(targetServer); 
            busyServers.offer(new int[]{time + load[i], targetServer});
            countRequests[targetServer]++;
        }
        
        int maxNumberOfRequests = 0;
        for (int c : countRequests) {
            maxNumberOfRequests = Math.max(maxNumberOfRequests, c); 
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (countRequests[i] == maxNumberOfRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
