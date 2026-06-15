/**
 * PROBLEM STATEMENT: 2095. Delete the Middle Node of a Linked List
 * --------------------------------------------------------------------------------
 * Given the head of a linked list, delete the middle node and return the head of 
 * the modified list. The middle node of a linked list of size n is the 
 * floor(n / 2)-th node from the start using 0-based indexing.
 * * Example:
 * Input: head = [1, 3, 4, 7, 1, 2, 6], n = 7
 * Middle Index: floor(7/2) = 3 (Node with value 7)
 * Output: [1, 3, 4, 1, 2, 6]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Two-Pointer (Slow/Fast) Approach
 * --------------------------------------------------------------------------------
 * 1. Use two pointers, 'slow' and 'fast', both starting at the head.
 * 2. Maintain a 'prev' pointer to track the node immediately preceding 'slow'.
 * 3. Traverse the list: 'fast' moves two steps, 'slow' moves one step.
 * 4. When 'fast' reaches the end, 'slow' will be at the middle node.
 * 5. Update 'prev.next' to 'slow.next' to bypass/delete the middle node.
 * 6. Special Case: If the list has only one node, return null.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the number of nodes in the linked list. 
 * We traverse the list exactly once.
 * Space Complexity: O(1), as we only use a few pointer variables.
 * --------------------------------------------------------------------------------
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // Handle edge case: If the list has only one node, removing it leaves the list empty
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null; // Keeps track of the node before 'slow'

        // Use slow/fast pointers to find the middle
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 'slow' is now at the middle node, 'prev' is the node before it.
        // Delete the middle node by skipping 'slow'.
        prev.next = slow.next;

        return head;
    }
}
