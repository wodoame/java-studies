package leetcode.linkedlist;

// leetcode 2
public class AddTwoNumbers {
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
            ans.next = (l1 == null && l2 == null && carry == 0)? null :new ListNode(carry);
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
            ans.next = (dummy == null && carry == 0)? null: new ListNode(carry);
            ans = ans.next;
        }
        return head;
    }

    // more elegant solution (but not necessarily faster)
    public ListNode addTwoNumbersElegant(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            int carry = 0;
            while(l1 != null || l2 == null || carry != 0){
                int sum = carry;
                if(l1 != null){
                   sum += l1.val;
                   l1 = l1.next;
                }

                if(l2 != null){
                    sum += l2.val;
                    l2 = l2.next;
                }

                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
            }
            return dummy.next;
    }
}
