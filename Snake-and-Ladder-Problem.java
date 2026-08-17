/*
 * ==========================================
 * PROBLEM STATEMENT: Snake and Ladder Problem
 * ==========================================
 * Given an integer n such that there is an n x n Snakes and Ladders board with cells numbered 
 * from 1 to n * n, find the minimum number of dice throws required to reach cell n * n starting from cell 1. 
 * 
 * You are given two arrays:
 * - lad[], where each pair (lad[2*i], lad[2*i + 1]) represents the start and end of a ladder.
 * - sn[], where each pair (sn[2*i], sn[2*i + 1]) represents the start and end of a snake.
 * 
 * If you land on the start cell of a snake or ladder, you must immediately move to its corresponding end cell.
 * You have complete control over the outcome of each dice throw (values 1 to 6). 
 * If it is impossible to reach cell n * n, return -1.
 * 
 * Constraints:
 * 1 <= n <= 10^3
 * 1 <= lad.size(), sn.size(), lad[i], sn[i] <= n^2
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Breadth-First Search - BFS)
 * ==========================================
 * - Approach:
 *   1. Board Representation: We can model the board as a directed graph where each cell can transition 
 *      to 6 subsequent cells (via dice values 1 to 6). We use a `jump` array of size N + 1 (where N = n * n) 
 *      to handle snakes and ladders in O(1) time. Initially, `jump[i] = i`, and we update `jump[start] = end` 
 *      for all snakes and ladders.
 *   2. Breadth-First Search (BFS): Since we need the *minimum* number of dice throws, BFS is the ideal choice 
 *      as it guarantees the shortest path in an unweighted graph.
 *   3. Queue State: We maintain a queue storing pairs of `[currentCell, throwsCount]`. We start at cell 1 with 0 throws.
 *   4. Visited Array: A `visited` boolean array ensures we don't revisit cells, avoiding infinite loops and redundant work.
 * 
 * - Complexity:
 *   - Time Complexity: O(n^2), because each cell on the board is visited and processed at most once.
 *   - Space Complexity: O(n^2) for storing the `jump` array, `visited` array, and the BFS queue.
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minThrows(int n, int[] lad, int[] sn) {
        int N = n * n;
        int[] jump = new int[N + 1];

        // Initialize jump array where each cell points to itself by default
        for (int i = 0; i <= N; i++) {
            jump[i] = i;
        }
        
        // Map ladder starts to their ends
        for (int i = 0; i < lad.length; i += 2) {
            jump[lad[i]] = lad[i + 1];
        }
        
        // Map snake starts to their ends
        for (int i = 0; i < sn.length; i += 2) {
            jump[sn[i]] = sn[i + 1];
        }

        boolean[] visited = new boolean[N + 1];
        Queue<int[]> q = new LinkedList<>();

        // Start BFS from cell 1 with 0 dice throws
        q.offer(new int[]{1, 0});
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int cell = curr[0];
            int throwsCount = curr[1];

            // If we reached the destination cell, return the total throws
            if (cell == N) {
                return throwsCount;
            }

            // Try all 6 possible dice outcomes
            for (int dice = 1; dice <= 6; dice++) {
                int next = cell + dice;

                if (next > N) {
                    break; // Exceeds board size
                }

                // If landing on a snake or ladder, take the jump
                next = jump[next];

                // If the destination cell hasn't been visited, mark it and push to queue
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, throwsCount + 1});
                }
            }
        }

        // If destination cannot be reached
        return -1;
    }
}
