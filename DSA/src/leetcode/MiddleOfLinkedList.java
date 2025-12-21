package leetcode;

// leetcode: 876
public class MiddleOfLinkedList {
    static void main() {
        var head = new ListNode(1);
        var second = new ListNode(2);
        var third = new ListNode(3);
        var fourth = new ListNode(4);
        var fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        var result  = middleNode(head);
        System.out.println(result.val);
    }

     static ListNode middleNode(ListNode head) {
        var fast = head;
        var slow = head;
        while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
        }
         return slow;
     }
}
