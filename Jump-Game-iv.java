/**
 * PROBLEM: 1345. Jump Game IV
 * 
 * Optimal Solution: Breadth-First Search (BFS)
 * 1. Graph Construction: Pre-process the array to map each value to a list 
 *    of indices where it appears.
 * 2. BFS Execution:
 *    - Starting at index 0, explore neighbors (i+1, i-1, and teleportation).
 *    - Teleportation check: If arr[i] == arr[j], treat these as connected edges.
 *    - Optimization: After processing all indices for a value 'v', remove 'v' 
 *      from the map to avoid re-visiting the same group of indices.
 * 
 * Time Complexity: O(N)
 * - Each index is visited at most once.
 * - Each value in the map is processed only once due to map removal.
 * Space Complexity: O(N)
 * - To store the adjacency map and the visited array.
 */

import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        if (n == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int idx = q.poll();

                if (idx == n - 1) return steps;

                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.offer(idx + 1);
                }

                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    q.offer(idx - 1);
                }

                if (map.containsKey(arr[idx])) {
                    for (int next : map.get(arr[idx])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            q.offer(next);
                        }
                    }

                    map.remove(arr[idx]);
                }
            }

            steps++;
        }

        return -1;
    }
}


    }
}
