/*
 * ==========================================
 * PROBLEM STATEMENT: Check Level Anagrams in Binary Trees
 * ==========================================
 * Given the roots of two binary trees `root1` and `root2`, check whether the nodes at every 
 * corresponding level of the two trees are anagrams of each other.
 * Two levels are considered anagrams if they contain the same node values with the same frequencies, 
 * regardless of their order.
 * 
 * Constraints:
 * 1 <= size of binary tree <= 10^5
 * 1 <= node.data <= 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Parallel Level-Order Traversal with Hash Frequency Check)
 * ==========================================
 * - Approach:
 *   1. Parallel BFS: Use two separate queues (`q1` and `q2`) to perform simultaneous level-order traversals 
 *      on both binary trees.
 *   2. Size Check: At each level, verify that both queues have the exact same number of nodes. 
 *      If not, the tree structures differ at this level, so they cannot be anagrams.
 *   3. Frequency Count Map: For each pair of corresponding nodes from `root1` and `root2` at the current level:
 *      - Increment the frequency for the node value from `root1`.
 *      - Decrement the frequency for the node value from `root2`.
 *   4. Anagram Validation: After processing all nodes at the current level, check the frequency map. 
 *      If all values in the map are `0`, the node values at this level form an anagram. If any non-zero count 
 *      exists, return `false`.
 * 
 * - Complexity:
 *   - Time Complexity: O(N), where `N` is the total number of nodes across both binary trees, 
 *     since every node is inserted and removed from the queues once, and hash map updates operate in O(1) average time per node.
 *   - Space Complexity: O(W), where `W` is the maximum width of the binary trees, required for storing levels in the queues and frequency map.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean areAnagrams(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();

        q1.offer(root1);
        q2.offer(root2);

        // Step 1: Perform parallel level-order traversal on both trees
        while (!q1.isEmpty() && !q2.isEmpty()) {
            // If the number of nodes at the current level differs, they aren't anagrams
            if (q1.size() != q2.size()) {
                return false;
            }

            int size = q1.size();
            HashMap<Integer, Integer> freq = new HashMap<>();

            // Step 2: Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                Node node1 = q1.poll();
                Node node2 = q2.poll();

                // Increment for tree 1, decrement for tree 2
                freq.put(node1.data, freq.getOrDefault(node1.data, 0) + 1);
                freq.put(node2.data, freq.getOrDefault(node2.data, 0) - 1);

                // Add children to queues for the next level
                if (node1.left != null) q1.offer(node1.left);
                if (node1.right != null) q1.offer(node1.right);

                if (node2.left != null) q2.offer(node2.left);
                if (node2.right != null) q2.offer(node2.right);
            }

            // Step 3: Verify if all frequencies balance out to zero for this level
            for (int count : freq.values()) {
                if (count != 0) {
                    return false;
                }
            }
        }

        // Ensure both trees are fully and equally traversed
        return q1.isEmpty() && q2.isEmpty();
    }
}
