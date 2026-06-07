/**
 * PROBLEM STATEMENT: 2196. Create Binary Tree From Descriptions
 * --------------------------------------------------------------------------------
 * Given an array of descriptions [parent, child, isLeft], construct a binary tree.
 * - If isLeft == 1, child is the left child of parent.
 * - If isLeft == 0, child is the right child of parent.
 * - All values are unique.
 * - The root is the unique node that is never a child.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Hash Map + Hash Set
 * --------------------------------------------------------------------------------
 * 1. Mapping: Use a Map<Integer, TreeNode> to store all created nodes so we can
 * access them by value in O(1) time.
 * 2. Root Identification: Use a Set<Integer> to keep track of all nodes that 
 * have a parent. The node in the descriptions that is never in this set is 
 * our root.
 * 3. Construction: Iterate through descriptions, create nodes if they don't 
 * exist in the map, assign parent-child pointers, and add the child to the 
 * 'children' set.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the number of descriptions. We iterate 
 * through the descriptions array twice.
 * Space Complexity: O(N), to store the map of nodes and the set of children.
 * --------------------------------------------------------------------------------
 */

/* * Definition for a binary tree node.
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

import java.util.*;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {

            int parentVal = d[0];
            int childVal = d[1];
            int isLeft = d[2];

            map.putIfAbsent(parentVal, new TreeNode(parentVal));
            map.putIfAbsent(childVal, new TreeNode(childVal));

            TreeNode parent = map.get(parentVal);
            TreeNode child = map.get(childVal);

            if (isLeft == 1) {
                parent.left = child;
            } 
            else {
                parent.right = child;
            }
            children.add(childVal);
        }

        for (int[] d : descriptions) {
            int parentVal = d[0];

            if (!children.contains(parentVal)) {
                return map.get(parentVal);
            }
        }
        return null;
    }
}

