package algorithm.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @ClassName LeastSubBST
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/12 17:04
 * @Version 1.0
 **/
public class LeastSubBST {

    int pre = 1 << 31 >> 1, res = (1 << 31) - 1;

    public int getMinimumDifference(TreeNode root) {
        mid(root);
        return res;
    }

    public void mid(TreeNode root) {
        if (root == null) {
            return;
        }
        mid(root.left);
        res = Math.min(res, root.val - pre);
        pre = root.val;
        mid(root.right);
    }

    public int dfs1(TreeNode root, int res) {
        if (root == null) {
            return res;
        }
        int left = root.left == null ? res : root.val - root.left.val,
                right = root.right == null ? res : root.right.val - root.val;
        res = Math.min(Math.min(left, right), res);
        return Math.min(dfs1(root.left, res), dfs1(root.right, res));
    }

    @Test
    public void test() {
        System.out.println(1 << 31 >> 1);
    }
}
