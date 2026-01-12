package trees;

import trees.bst.BinarySearchTree;
import trees.bst.Node;

public class Main {
    static void main() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.root = treeExample1();
        bst.inorderTraverse(System.out::println);
    }
    static Node treeExample1(){
        Node root = new Node(50);
        Node node25  = new Node(25);
        Node node75  = new Node(75);
        root.left = node25;
        root.right = node75;

        Node node10  = new Node(10);
        Node node33  = new Node(33);
        node25.left = node10;
        node25.right = node33;

        Node node56  = new Node(56);
        Node node89  = new Node(89);
        node75.left = node56;
        node75.right = node89;
        return root;
    }
}
