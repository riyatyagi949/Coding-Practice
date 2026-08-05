// Problem Statement:
// You are maintaining a project with n methods (0 to n-1) and given invocations [ai, bi] (ai invokes bi).
// Method k has a bug. Method k and any method invoked by it directly or indirectly are suspicious.
// A group of methods can only be removed if no method outside the group invokes any method within it.
// Return an array containing all the remaining methods after removing suspicious methods, or all methods if removal isn't possible.
// Constraints: 1 <= n <= 10^5, 0 <= k <= n - 1, 0 <= invocations.length <= 2 * 10^5.

// Optimal Solution in Java:
// Runtime: O(N + E) - where N is the number of methods and E is the number of invocations.
// Space Complexity: O(N + E) - for storing the graph and the visited/suspicious tracking arrays.

import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build the directed graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Step 1: Identify all suspicious methods reachable from method k (directly or indirectly)
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        suspicious[k] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph[curr]) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }

        // Step 2: Check if any non-suspicious method invokes any suspicious method
        for (int[] edge : invocations) {
            int u = edge[0], v = edge[1];
            if (!suspicious[u] && suspicious[v]) {
                // If an outside method invokes a suspicious method, we cannot remove any method.
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }

        // Step 3: Otherwise, remove all suspicious methods and return the remaining ones
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
