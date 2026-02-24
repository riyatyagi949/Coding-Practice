/**
 * PROBLEM STATEMENT: 1022. Sum of Root To Leaf Binary Numbers
 * --------------------------------------------------------------------------------
 * You are given the root of a binary tree where each node has a value 0 or 1. 
 * Each root-to-leaf path represents a binary number starting with the most 
 * significant bit.
 * * * Example:
 * If the path is 1 -> 0 -> 1, this represents the binary number 101, which is 5.
 * * * Task:
 * Return the sum of all numbers represented by every path from the root to a leaf.
 * The answer is guaranteed to fit in a 32-bit integer.
 * * * Constraints:
 * - The number of nodes is in the range [1, 1000].
 * - Node.val is either 0 or 1.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Depth-First Search (DFS) / Pre-order Traversal
 * --------------------------------------------------------------------------------
 * 1. Recursive Traversal:
 * We traverse the tree from root to leaves. We pass down the "current value" 
 * accumulated from parent nodes to their children.
 * * 2. Binary Number Construction:
 * As we move from a parent to a child, the binary number shifts left by 1 
 * (multiplied by 2) and the current node's value is added (bitwise OR).
 * Formula: `currentValue = (parentValue << 1) | currentNode.val`
 * * 3. Base Cases:
 * - If we reach a NULL node: Return 0 (doesn't contribute to the sum).
 * - If we reach a LEAF node (left and right children are null): Return the 
 * current accumulated value.
 * * 4. Summation:
 * The total sum is the sum of results from the left subtree and the right subtree.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We visit every node in the tree exactly once.
 * Space Complexity: O(H)
 * - H is the height of the tree. This space is used by the recursion stack.
 * - In the worst case (skewed tree), H = N. In the best case (balanced tree), H = log N.
 * --------------------------------------------------------------------------------
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode node, int current) {
        if(node == null)
         return 0;
        
        current = (current << 1) | node.val;
        
        if(node.left == null && node.right == null) {
            return current;
        }
        return dfs(node.left, current) + dfs(node.right, current);
    }
}
