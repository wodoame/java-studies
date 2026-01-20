package trees.bst;

import java.util.*;
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

//     *       3
//     *      / \
//     *     9  20
//             /  \
//     *      15   7
//     [3]
// current = 3, [9, 20]
    // current = 9 [20]
    // current = 20, [15, 7]
    // current = 15, [7]
    // current = 7, []

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

    /**
     * Zigzag level order traversal: Returns a list of lists where each inner list
     * represents a level, alternating between left-to-right and right-to-left order.
     *
     * Example:
     *       3
     *      / \
     *     9  20
     *       /  \
     *      15   7
     *
     * Output: [[3], [20, 9], [15, 7]]
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(w) where w is the maximum width of the tree
     */
    // [3]
    // current = 3, size = 1 queue = [] currentLevel = [3]
    // current = 3, queue = [9, 20] currentLevel = [3]
    // current = 9, size = 2 queue = [20] currentLevel = [9]
    // current = 20, size = 2 queue = [] currentLevel = [9, 20]
    // current = 20, size = 2 queue = [15, 7] currentLevel = [9, 20]
    // current = 20, size = 2 queue = [15, 7] currentLevel = []
    // current = 15, size = 2 queue = [7] currentLevel = [15]

    public List<List<Integer>> zigzagLevelOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                assert current != null;
                currentLevel.add(current.data);

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            // Reverse the level if we're going right to left
            if (!leftToRight) {
                Collections.reverse(currentLevel);
            }

            result.add(currentLevel);
            leftToRight = !leftToRight; // Alternate direction
        }

        return result;
    }

    public void bfs(){
        if(root == null)return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node current = queue.poll();
            System.out.println(current.data);
            if(current.left != null){
               queue.add(current.left);
            }

            if(current.right != null){
               queue.add(current.right);
            }
        }
    }
//   A way for grouping nodes by level using recursion
//    public void helper(TreeNode root, int level, List<List<Integer>> ans){
//        if(root == null) return;
//
//        if(level == ans.size()) {
//            ans.add(new ArrayList<>());
//        }
//
//        ans.get(level).add(root.val);
//
//        helper(root.left, level+1, ans);
//        helper(root.right, level+1, ans);
//
//        return;
//    }
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> ans = new ArrayList<>();
//        helper(root, 0, ans);
//        return ans;
//    }

    /**
     * Zigzag level order traversal with Consumer (consistent with other traverse methods).
     * Processes nodes in zigzag order: left-to-right, then right-to-left, alternating.
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(w) where w is the maximum width of the tree
     */
    public void zigzagLevelOrderTraverse(Consumer<Integer> action) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Collect all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                currentLevel.add(current.data);

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            // Process nodes in the appropriate direction
            if (leftToRight) {
                for (Integer value : currentLevel) {
                    action.accept(value);
                }
            } else {
                for (int i = currentLevel.size() - 1; i >= 0; i--) {
                    action.accept(currentLevel.get(i));
                }
            }

            leftToRight = !leftToRight; // Alternate direction
        }
    }
}
