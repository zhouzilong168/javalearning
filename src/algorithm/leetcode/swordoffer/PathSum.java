package algorithm.leetcode.swordoffer;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName PathSum
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/26 19:55
 * @Version 1.0
 **/
public class PathSum {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum,  new LinkedList<>());
        return res;
    }

    private static void dfs(TreeNode root, int sum, LinkedList<Integer> list) {
        if (root == null) { // null值返回，减少条件判断
            return;
        }
        if (root.left == null && root.right == null) {
            if (sum - root.val == 0) { // 边界直接处理
                List<Integer> tmp = new LinkedList<>(list);
                tmp.add(root.val);
                res.add(tmp);
            }
            return;
        }
        list.add(root.val);
        sum-=root.val;
        dfs(root.left,sum,list);
        dfs(root.right,sum,list);
        list.removeLast();
    }

    private static void dfs1(TreeNode root, int sum, int tmp, LinkedList<Integer> list) {
        if (root.left == null && root.right == null) {
            if (tmp + root.val == sum) {
                List<Integer> temp = new LinkedList<>(list);
                temp.add(root.val);
                res.add(temp);
            }
            return;
        }
        list.add(root.val);
        tmp += root.val;
        if (root.left != null) {
            dfs1(root.left, sum, tmp, list);
        }
        if (root.right != null) {
            dfs1(root.right, sum, tmp, list);
        }
        list.removeLast();
        tmp -= root.val;
    }

    interface A {

    }
    class B extends C implements A{

    }
    static class C {
        public static void main(String[] args) {
            new PathSum().cc();
        }

    }

    public void cc() {
        B b = new B();
        if (b instanceof B) {
            System.out.println("instance of b");
        }
        if (b instanceof C) {
            System.out.println("instance of c");
        }
        if (b instanceof A) {
            System.out.println("instance of a");
        }
    }

    public static void main(String[] args) {
        new PathSum().cc();
    }
}
