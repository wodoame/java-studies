package leetcode.trees;

import java.util.ArrayList;
import java.util.List;

// leetcode 102
public class BinaryTreeLevelOrderTraversal {
    // this is an interesting solution I learned from leetcode
    public List<List<Integer>> levelOrder(TreeNode root) {
       List<List<Integer>> ans = new ArrayList<>();
       dfs(root, 0, ans);
       return ans;
    }

    public void dfs(TreeNode node, int level, List<List<Integer>> ans){
        if(node == null)return;
        if(level == ans.size())ans.add(new ArrayList<>());
        ans.get(level).add(node.val);
        dfs(node.left, level + 1, ans);
        dfs(node.right, level + 1, ans);
    }
}

