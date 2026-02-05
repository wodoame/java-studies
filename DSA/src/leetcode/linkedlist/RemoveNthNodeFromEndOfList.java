package leetcode.linkedlist;

// leetcode 19
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        ListNode fast = head;
        ListNode slow = dummy;
        dummy.next = head;
        int i = -1;
        int j = 0;
        while(j - i < n){
            fast = fast.next;
            j++;
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
            i++;
            j++;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
