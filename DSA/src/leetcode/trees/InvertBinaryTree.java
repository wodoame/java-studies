package leetcode.trees;

// leetcode 226
public class InvertBinaryTree {
    static void main() {
        System.out.println(invertTree(null));
    }
    public static TreeNode invertTree(TreeNode root) {
        if(root == null)return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        return root;
    }
}
