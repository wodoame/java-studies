package leetcode.linkedlist;

public class ReverseNodesInKGroup {
    static void main() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = reverseKGroup(node1, 2);
        printList(head);
    }

    private static void printList(ListNode head) {
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
    int i = 0;
    int j = 0;
    ListNode current = head;
    ListNode start = head;
    ListNode newHead = head;
    ListNode prevEnd = null;
    while(current != null){
        ListNode nextStart = current.next;
        if(j - i + 1 == k){
            if(i == 0){
                newHead = current;
            }
           current.next = null; // end this sublist
           reverseList(start);
           if(prevEnd != null)prevEnd.next = current;
           prevEnd = start;
           start.next = nextStart;
           start = nextStart;
           i = j + 1;
        }
        current = nextStart;
        j++;
    }
     return newHead;
 }
 public static void reverseList(ListNode head) {
     ListNode prev = null;
     var currentNode = head;
     while(currentNode != null){
         var temp = currentNode.next;
         currentNode.next = prev;
         prev = currentNode;
         currentNode = temp;
     }
    }

 }