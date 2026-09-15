/*
 * ==========================================
 * PROBLEM STATEMENT: Visit Leaves with Budget
 * ==========================================
 * Given a binary tree and an integer `k`, where you start from the root at level 1. 
 * The cost of visiting a leaf node is equal to the level of that leaf node. You can visit 
 * any number of leaf nodes, but the total cost must not exceed `k`. 
 * Return the maximum number of leaf nodes that can be visited within the given budget.
 * 
 * Constraints:
 * 1 <= size of binary tree <= 10^5
 * 1 <= k <= 10^4
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Breadth-First Search & Greedy Selection)
 * ==========================================
 * - Approach:
 *   1. Level-Order Traversal (BFS): Use a queue to traverse the binary tree level by level while tracking 
 *      the current level (cost) starting from 1.
 *   2. Identify Leaves: For each node popped from the queue, check if it is a leaf node 
 *      (both `left` and `right` children are `null`). If it is, record its cost (the current level) 
 *      in a list of costs.
 *   3. Greedy Selection: Sort the collected leaf costs in ascending order so we can prioritize visiting 
 *      the cheapest leaves first.
 *   4. Budget Allocation: Iterate through the sorted leaf costs and accumulate them as long as the 
 *      running total remains within the budget `k`. Count how many leaves can be successfully visited.
 * 
 * - Complexity:
 *   - Time Complexity: O(N log L), where `N` is the total number of nodes in the binary tree and `L` 
 *     is the number of leaf nodes (due to sorting the leaf costs).
 *   - Space Complexity: O(N) auxiliary space to store the queue and the list of leaf costs.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int getCount(Node root, int k) {
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> costs = new ArrayList<>();

        queue.offer(root);
        int level = 1;

        // Step 1: Traverse the tree using BFS to collect the cost (level) of every leaf node
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                // Check if the current node is a leaf
                if (curr.left == null && curr.right == null) {
                    costs.add(level);
                }

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            level++;
        }
        
        // Step 2: Sort leaf costs in ascending order to pick the cheapest ones first
        Collections.sort(costs);

        int count = 0;
        int totalCost = 0;

        // Step 3: Greedily visit as many leaves as the budget allows
        for (int cost : costs) {
            if (totalCost + cost > k) {
                break;
            }
            totalCost += cost;
            count++;
        }
        
        return count;
    }
}
