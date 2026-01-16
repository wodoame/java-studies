package leetcode.linkedlist;

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

    /**
     * Rotate the linked list to the right by k places.
     *
     * Algorithm:
     * 1. Handle edge cases (empty list, size 1, k=0)
     * 2. Normalize k using k = k % size (since rotating by size gives same list)
     * 3. Find the new tail position (size - k - 1)
     * 4. The node after new tail becomes the new head
     * 5. Connect old tail to old head to complete rotation
     * 6. Break the link at the new tail
     *
     * Example: 1→2→3→4→5, k = 2
     * - size = 5, k = 2 % 5 = 2
     * - new tail index = 5 - 2 - 1 = 2 (node 3)
     * - new head = node 4
     * - Connect: 5→1, break: 3→null
     * - Result: 4→5→1→2→3
     *
     * Time Complexity: O(n) - one pass through the list
     * Space Complexity: O(1) - only pointers used
     *
     * @param k number of places to rotate right
     */
    public void rotate(int k) {
        // Edge cases
        if (head == null || size <= 1 || k == 0) return;

        // Normalize k (rotating by size gives same list)
        k = k % size;
        if (k == 0) return; // No rotation needed

        // Find the new tail: it's at position (size - k - 1)
        // The node after it will become the new head
        int newTailIndex = size - k - 1;
        ListNode newTail = head;

        for (int i = 0; i < newTailIndex; i++) {
            newTail = newTail.next;
        }

        // The node after newTail becomes the new head
        ListNode newHead = newTail.next;

        // Connect old tail to old head (make it circular temporarily)
        tail.next = head;

        // Break the link at new tail
        newTail.next = null;

        // Update head and tail
        head = newHead;
        tail = newTail;
    }

    /**
     * Helper method to print the linked list for debugging
     */
    public void print() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print("→");
            }
            current = current.next;
        }
        System.out.println();
    }
}
