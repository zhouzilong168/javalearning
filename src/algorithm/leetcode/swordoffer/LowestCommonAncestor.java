package algorithm.leetcode.swordoffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LowestCommonAncestor
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/23 21:27
 * @Version 1.0
 **/
public class LowestCommonAncestor {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
//        if (left == null && right == null) return null; // 1.
        if (left == null) return right; // 3.
        if (right == null) return left; // 4.
        return root; // 2. if(left != null and right != null)
    }

    private List<TreeNode> plist = new ArrayList<>(), qlist = new ArrayList<>();
    private boolean pflag = false, qflag = false;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        for (int i = plist.size() - 1; i >= 0; i--) {
            for (int j = qlist.size() - 1; j >= 0; j--) {
                if (plist.get(i) == qlist.get(j)) {
                    return plist.get(i);
                }
            }
        }
        return null;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        if (!pflag && root.val == p.val) {
            plist.add(root);
            pflag = true;
        }
        if (!qflag && root.val == q.val) {
            qlist.add(root);
            qflag = true;
        }
        if (pflag && qflag) {
            return true;
        }
        if (!pflag) {
            plist.add(root);
        }
        if (!qflag) {
            qlist.add(root);
        }
        if (dfs(root.left, p, q) || dfs(root.right, p, q)) {
            return true;
        }
        if (!pflag) {
            plist.remove(root);
        }
        if (!qflag) {
            qlist.remove(root);
        }
        return false;
    }

    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            int t = p.val;
            p.val = q.val;
            q.val = t;
        }
        while (true) {
            if (p.val == root.val || q.val == root.val) {
                break;
            }
            if (p.val > root.val) {
                root = root.right;
            } else if (q.val < root.val) {
                root = root.left;
            } else {
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
//        System.out.println(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(1)).val);
    }
}
