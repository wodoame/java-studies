package leetcode.linkedlist;

public class RotateLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== Linked List Rotation Demo ===\n");
        
        // Test Case 1: Basic rotation (k = 2)
        System.out.println("Test Case 1: Input: 1→2→3→4→5, k = 2");
        MyLinkedList list1 = new MyLinkedList();
        list1.addAtTail(1);
        list1.addAtTail(2);
        list1.addAtTail(3);
        list1.addAtTail(4);
        list1.addAtTail(5);
        
        System.out.print("Before rotation: ");
        list1.print();
        
        list1.rotate(2);
        
        System.out.print("After rotation:  ");
        list1.print();
        System.out.println("Expected:        4→5→1→2→3");
        System.out.println();
        
        // Test Case 2: k greater than size (k = 7, same as k = 2)
        System.out.println("Test Case 2: Input: 1→2→3→4→5, k = 7 (7 % 5 = 2)");
        MyLinkedList list2 = new MyLinkedList();
        list2.addAtTail(1);
        list2.addAtTail(2);
        list2.addAtTail(3);
        list2.addAtTail(4);
        list2.addAtTail(5);
        
        System.out.print("Before rotation: ");
        list2.print();
        
        list2.rotate(7);
        
        System.out.print("After rotation:  ");
        list2.print();
        System.out.println("Expected:        4→5→1→2→3");
        System.out.println();
        
        // Test Case 3: k = 0 (no rotation)
        System.out.println("Test Case 3: Input: 1→2→3, k = 0");
        MyLinkedList list3 = new MyLinkedList();
        list3.addAtTail(1);
        list3.addAtTail(2);
        list3.addAtTail(3);
        
        System.out.print("Before rotation: ");
        list3.print();
        
        list3.rotate(0);
        
        System.out.print("After rotation:  ");
        list3.print();
        System.out.println("Expected:        1→2→3");
        System.out.println();
        
        // Test Case 4: k = size (full rotation, back to original)
        System.out.println("Test Case 4: Input: 1→2→3→4, k = 4 (full rotation)");
        MyLinkedList list4 = new MyLinkedList();
        list4.addAtTail(1);
        list4.addAtTail(2);
        list4.addAtTail(3);
        list4.addAtTail(4);
        
        System.out.print("Before rotation: ");
        list4.print();
        
        list4.rotate(4);
        
        System.out.print("After rotation:  ");
        list4.print();
        System.out.println("Expected:        1→2→3→4");
        System.out.println();
        
        // Test Case 5: Single element
        System.out.println("Test Case 5: Input: 1, k = 5");
        MyLinkedList list5 = new MyLinkedList();
        list5.addAtTail(1);
        
        System.out.print("Before rotation: ");
        list5.print();
        
        list5.rotate(5);
        
        System.out.print("After rotation:  ");
        list5.print();
        System.out.println("Expected:        1");
        System.out.println();
        
        // Test Case 6: Rotate by 1
        System.out.println("Test Case 6: Input: 1→2→3→4→5, k = 1");
        MyLinkedList list6 = new MyLinkedList();
        list6.addAtTail(1);
        list6.addAtTail(2);
        list6.addAtTail(3);
        list6.addAtTail(4);
        list6.addAtTail(5);
        
        System.out.print("Before rotation: ");
        list6.print();
        
        list6.rotate(1);
        
        System.out.print("After rotation:  ");
        list6.print();
        System.out.println("Expected:        5→1→2→3→4");
        System.out.println();
        
        // Test Case 7: Large rotation
        System.out.println("Test Case 7: Input: 1→2→3, k = 2000000000 (mod 3 = 2)");
        MyLinkedList list7 = new MyLinkedList();
        list7.addAtTail(1);
        list7.addAtTail(2);
        list7.addAtTail(3);
        
        System.out.print("Before rotation: ");
        list7.print();
        
        list7.rotate(2000000000);
        
        System.out.print("After rotation:  ");
        list7.print();
        System.out.println("Expected:        2→3→1");
        System.out.println();
        
        System.out.println("=== All tests completed! ===");
    }
}

