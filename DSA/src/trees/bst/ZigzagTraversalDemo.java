package trees.bst;

import java.util.List;

public class ZigzagTraversalDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        // Build a sample tree:
        //        50
        //       /  \
        //      30   70
        //     / \   / \
        //    20 40 60 80
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        System.out.println("Tree structure:");
        System.out.println("        50");
        System.out.println("       /  \\");
        System.out.println("      30   70");
        System.out.println("     / \\   / \\");
        System.out.println("    20 40 60 80");
        System.out.println();
        
        // Method 1: Using zigzagLevelOrder() - returns List<List<Integer>>
        System.out.println("=== Zigzag Level Order Traversal (List format) ===");
        List<List<Integer>> result = bst.zigzagLevelOrder();
        System.out.println("Result: " + result);
        System.out.println("\nLevel breakdown:");
        for (int i = 0; i < result.size(); i++) {
            String direction = (i % 2 == 0) ? "left-to-right" : "right-to-left";
            System.out.println("Level " + i + " (" + direction + "): " + result.get(i));
        }
        System.out.println();
    }
}

