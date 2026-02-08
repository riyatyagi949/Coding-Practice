/**
 * PROBLEM STATEMENT: 110. Balanced Binary Tree
 * --------------------------------------------------------------------------------
 * Given a binary tree, determine if it is height-balanced.
 * * A height-balanced binary tree is defined as:
 * - A binary tree in which the left and right subtrees of every node differ 
 * in height by no more than 1.
 * * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false (The depth of the left subtree is 3, right is 1. Difference > 1)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Bottom-Up Recursion (O(N))
 * --------------------------------------------------------------------------------
 * 1. The Naive Approach:
 * A top-down approach involves calling a 'height' function for every node. 
 * Since 'height' itself is O(N), calling it for N nodes results in O(N^2).
 * * 2. The Optimized Approach:
 * We use a bottom-up recursion that computes height and checks balance 
 * simultaneously. 
 * - If a subtree is balanced, the function returns its actual height.
 * - If a subtree is unbalanced, the function returns -1.
 * * 3. Logic:
 * - Base Case: A null node has height 0.
 * - Recursive Step:
 * a. Get height of left subtree. If it's -1, return -1.
 * b. Get height of right subtree. If it's -1, return -1.
 * c. Check the difference: If |left - right| > 1, return -1 (unbalanced).
 * d. Otherwise, return the actual height: max(left, right) + 1.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We visit each node exactly once. Every node's height is calculated once.
 * * Space Complexity: O(H)
 * - Where H is the height of the tree. This space is used by the recursion stack.
 * - In the worst case (skewed tree), H = N. In the best case (balanced tree), H = log N.
 * --------------------------------------------------------------------------------
 */
// Code -

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
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    private int height(TreeNode node) {
        if (node == null) 
        return 0;

        int left = height(node.left);
        if (left == -1) 
        return -1;

        int right = height(node.right);
        if (right == -1) 
        return -1;

        if (Math.abs(left - right) > 1) 
        return -1;

        return Math.max(left, right) + 1;
    }
}
