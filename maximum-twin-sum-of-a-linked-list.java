/**
 * PROBLEM STATEMENT: 2130. Maximum Twin Sum of a Linked List
 * --------------------------------------------------------------------------------
 * In a linked list of size n (n is even), the i-th node is the twin of the 
 * (n-1-i)-th node for 0 <= i <= (n/2) - 1. The twin sum is the sum of a node 
 * and its twin. Return the maximum twin sum of the linked list.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Two-Pointer + List Reversal
 * --------------------------------------------------------------------------------
 * 1. Find the midpoint of the linked list using a slow and fast pointer approach.
 * 2. Reverse the second half of the linked list starting from the midpoint.
 * 3. Use two pointers: 'first' at the head of the original list and 'second' at 
 * the head of the reversed second half.
 * 4. Iterate through both pointers, calculating the sum of their values, and 
 * track the maximum sum encountered.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the number of nodes in the linked list.
 * - Finding the midpoint: O(N)
 * - Reversing the second half: O(N)
 * - Calculating twin sums: O(N)
 * Space Complexity: O(1), as we perform the reversal and traversal in-place 
 * using a constant amount of extra space.
 * --------------------------------------------------------------------------------
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public int pairSum(ListNode head) {
        // 1. Use slow/fast pointers to find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half of the list (starting from 'slow')
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // 3. Calculate max twin sum by traversing the first half and reversed second half
        int maxSum = 0;
        ListNode first = head;
        ListNode second = prev; // 'prev' is the new head of the reversed second half

        while (second != null) {
            maxSum = Math.max(maxSum, first.val + second.val);
            first = first.next;
            second = second.next;
        }

        return maxSum;
    }
}
