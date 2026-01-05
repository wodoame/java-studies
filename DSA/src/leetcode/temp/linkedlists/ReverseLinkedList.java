package leetcode.temp.linkedlists;

import leetcode.ListNode;

// leetcode 206
public class ReverseLinkedList {
public ListNode reverseList(ListNode head) {
     ListNode prev = null;
     var currentNode = head;
     while(currentNode != null){
         var temp = currentNode.next;
         currentNode.next = prev;
         prev = currentNode;
         currentNode = temp;
     }
     return prev;
    }
}
