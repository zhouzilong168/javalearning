package algorithm.leetcode;

import java.util.*;

/**
 * @ClassName InOrderBT_94
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/4/20 16:41
 * @Version 1.0
 **/
public class InOrderBT_94 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();// 访问最左元素、子树根节点元素
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list2 = new LinkedList<>(Arrays.asList(7, 8, 9));
        List<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3));
        System.out.println(list);
        list.addAll(2, list2);
        System.out.println(list);
    }
}
