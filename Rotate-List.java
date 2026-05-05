/**
 * PROBLEM STATEMENT: 61. Rotate List
 * --------------------------------------------------------------------------------
 * Given the head of a linked list, rotate the list to the right by k places.
 * 
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * 
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: CIRCULAR LINKED LIST APPROACH
 * --------------------------------------------------------------------------------
 * 1. Edge Case Handling: If the list is empty, has one node, or k is 0, 
 *    return the head immediately.
 * 2. Calculate Length: Traverse the list to find its length (n) and the 
 *    last node.
 * 3. Normalize k: The effective rotation is k % n. If k % n == 0, 
 *    no rotation is needed.
 * 4. Create a Cycle: Connect the last node's next pointer to the current 
 *    head, making it a circular list.
 * 5. Find New Tail: The new tail will be at position (n - (k % n) - 1) 
 *    from the original head.
 * 6. Break the Cycle: The new head is tail.next. Set tail.next to null 
 *    to restore the linear structure.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: $O(n)$
 * - We traverse the list once to find the length and once more (at most) 
 *   to find the new tail.
 * Space Complexity: $O(1)$
 * - We only use a few pointer variables regardless of the list size.
 * --------------------------------------------------------------------------------
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        temp.next = head;
        k = k % length;
        k = length - k;

        while (k-- > 0) {
            temp = temp.next;
        }

        head = temp.next;
        temp.next = null;

        return head;
    }
}

