/*
 * ==========================================
 * PROBLEM STATEMENT: Largest Sum Cycle
 * ==========================================
 * Given an array `edge[]` of size `n` representing a functional graph where each node has at most 
 * one outgoing edge. A cycle is a sequence of cells where following the outgoing edges returns to 
 * the starting cell. The sum of a cycle is the sum of the indices of all cells in that cycle.
 * Return the maximum cycle sum. If there is no cycle, return -1.
 * 
 * Constraints:
 * 2 <= edge.size() <= 10^5
 * -1 <= edge[i] < edge.size()
 * edge[i] != i
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (DFS / Traversal with State Marking)
 * ==========================================
 * - Approach:
 *   1. Use a `visited` array with three states:
 *      - `0`: Unvisited
 *      - `1`: Visited in the current traversal path (active recursion/path stack)
 *      - `2`: Fully processed (visited and checked, can be skipped in future traversals)
 *   2. For each unvisited node, perform a traversal along the directed edges:
 *      - Mark nodes as `1` and record their traversal timestamps or track the path.
 *      - If we encounter a node with state `1`, it means we have detected a cycle belonging to the current traversal.
 *      - Compute the sum of the indices of the nodes forming this cycle.
 *      - Update `maxSum` with the maximum cycle sum found so far.
 *   3. After processing a starting path, mark all nodes in that path as `2` (fully processed) 
 *      to avoid redundant traversals.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) since each node is visited a constant number of times across all traversals.
 *   - Space Complexity: O(n) for the `visited` array and traversal stack/state.
 */

class Solution {
    public int largestSumCycle(int[] edge) {
        int n = edge.length;
        int[] visited = new int[n];
        int maxSum = -1;

        for (int i = 0; i < n; i++) {
            if (visited[i] != 0) continue;

            int node = i;
            
            // Traverse the path while nodes are unvisited
            while (node != -1 && visited[node] == 0) {
                visited[node] = 1; // Mark as visiting in current path
                node = edge[node];
            }
            
            // If we hit a node currently marked as 1, we found a cycle in the current path
            if (node != -1 && visited[node] == 1) {
                int sum = 0;
                int curr = node;

                do {
                    sum += curr;
                    curr = edge[curr];
                } while (curr != node);

                maxSum = Math.max(maxSum, sum);
            }
            
            // Mark all nodes in the current traversal path as fully processed (state 2)
            node = i;
            while (node != -1 && visited[node] == 1) {
                visited[node] = 2;
                node = edge[node];
            }
        }
        
        return maxSum;
    }
}
