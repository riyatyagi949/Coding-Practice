/*
 * ==========================================
 * PROBLEM STATEMENT: Count Zero Request Servers
 * ==========================================
 * Given `n` servers, a 2D array `logs` where `logs[i] = [server_id, time]`, an integer `x`, 
 * and an array `queries`. For each query `queries[i]`, find the number of servers that did not 
 * receive any requests during the time interval `[queries[i] - x, queries[i]]`.
 * 
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= logs.length <= 10^5
 * 1 <= queries.length <= 10^5
 * 1 <= logs[i][1], queries[i] <= 10^6
 * 1 <= x <= 10^5
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Sorting + Two Pointers / Sliding Window)
 * ==========================================
 * - Approach:
 *   1. Offline Query Processing: Sort the `logs` array by their timestamps in ascending order.
 *   2. To handle queries efficiently without redundant searches, store each query along with its original index 
 *      and sort the queries by their time values.
 *   3. Maintain a sliding window using two pointers (`i` and `j`) over the sorted logs to include all requests 
 *      falling within the time range `[curtime - x, curtime]` for each query.
 *   4. Use a frequency map (`mp`) to track active servers and their request counts within the current window.
 *   5. For each query, the number of servers with *zero requests* is simply `n - mp.size()`.
 * 
 * - Complexity:
 *   - Time Complexity: O(m log m + q log q), where `m` is the number of logs and `q` is the number of queries.
 *   - Space Complexity: O(m + q + n) for sorting indices, maps, and tracking arrays.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;
        // Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        
        int q = queries.length;
        int[][] timeIndex = new int[q][2];

        for (int i = 0; i < q; i++) {
            timeIndex[i][0] = queries[i];
            timeIndex[i][1] = i;
        }
        // Sort queries by time to process them in chronological order
        Arrays.sort(timeIndex, (a, b) -> a[0] - b[0]);
        
        Map<Integer, Integer> mp = new HashMap<>();  
        int[] result = new int[q];
        
        int i = 0, j = 0;
        for (int[] pair : timeIndex) {
            int curtime = pair[0];
            int ind = pair[1];
            
            int start = curtime - x;
            int end = curtime;
            
            // Expand the window to include all logs up to 'end'
            while (j < m && logs[j][1] <= end) {
                int serverId = logs[j][0];
                mp.put(serverId, mp.getOrDefault(serverId, 0) + 1);
                j++;
            }
            
            // Shrink the window from the left to remove logs older than 'start'
            while (i < m && logs[i][1] < start) {
                int serverId = logs[i][0];
                mp.put(serverId, mp.get(serverId) - 1);
                if (mp.get(serverId) == 0) {
                    mp.remove(serverId);
                }
                i++;
            }
            
            // Servers with zero requests = total servers - active servers in the current window
            result[ind] = n - mp.size();
        }
        
        return result;
    }
}
