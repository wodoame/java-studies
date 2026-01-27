package leetcode.linkedlist;

public class AddTwoNumbers {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode head = ans;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            ans.val = sum % 10;
            l1 = l1.next;
            l2 = l2.next;
            ans.next = new ListNode(carry);
            ans = ans.next;
        }
        ListNode dummy = null;
        if (l1 == null & l2 != null) dummy = l2;
        if (l1 != null) dummy = l1;

        while (dummy != null) {
            int sum = dummy.val + carry;
            ans.val = sum % 10;
            carry = sum / 10;
            dummy = dummy.next;
            ans.next = new ListNode(carry);
            ans = ans.next;
        }

        return head;
    }
}
