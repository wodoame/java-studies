package leetcode.temp.linkedlists;

public class MyLinkedList {
    ListNode head = null;
    ListNode tail = null;
    int size = 0;

    public MyLinkedList() {
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        ListNode currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.val;
    }

    public void addAtHead(int val) {
        ListNode newHead = new ListNode(val);
        newHead.next = head;
        head = newHead;
        if (size == 0) {
            tail = newHead;
        }
        size++;
    }

    public void addAtTail(int val) {
        ListNode newTail = new ListNode(val);
        if (size == 0) {
            head = newTail;
            tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            ListNode currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        if (index == 0) {
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            ListNode currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
            if (index == size - 1) {
                tail = currentNode;
            }
        }
        size--;
    }
}

class ListNode {
    public int val;
    public ListNode next;
    ListNode() {}
    public ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
