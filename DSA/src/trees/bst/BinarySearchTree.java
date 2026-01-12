package trees.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree {
    public Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Inorder traversal: Left -> Root -> Right
    public void inorderTraverse(Consumer<Integer> action) {
        inorderTraverseRec(root, action);
    }

    private void inorderTraverseRec(Node node, Consumer<Integer> action) {
        if (node != null) {
            inorderTraverseRec(node.left, action);
            action.accept(node.data);
            inorderTraverseRec(node.right, action);
        }
    }

    // Preorder traversal: Root -> Left -> Right
    public void preorderTraverse(Consumer<Integer> action) {
        preorderTraverseRec(root, action);
    }

    private void preorderTraverseRec(Node node, Consumer<Integer> action) {
        if (node != null) {
            action.accept(node.data);
            preorderTraverseRec(node.left, action);
            preorderTraverseRec(node.right, action);
        }
    }

    // Postorder traversal: Left -> Right -> Root
    public void postorderTraverse(Consumer<Integer> action) {
        postorderTraverseRec(root, action);
    }

    private void postorderTraverseRec(Node node, Consumer<Integer> action) {
        if (node != null) {
            postorderTraverseRec(node.left, action);
            postorderTraverseRec(node.right, action);
            action.accept(node.data);
        }
    }

    // Level-order traversal (Breadth-First): Level by level from top to bottom
    public void levelOrderTraverse(Consumer<Integer> action) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            action.accept(current.data);

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
