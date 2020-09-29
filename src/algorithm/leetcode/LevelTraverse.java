package algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LevelTraverse
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/29 10:25
 * @Version 1.0
 **/
public class LevelTraverse {

    public List<List<Integer>> levelOrderDFSZ(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfsZ(0, root, res);
        return res;
    }

    public void dfsZ(int i, TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        List<Integer> tmp = null;
        if (i < res.size()) { // 使用递归层次i分隔层次
            tmp = res.get(i);
        } else {
            tmp = new LinkedList<>();
            res.add(tmp);
        }
        if (i % 2 == 0) {
            tmp.add(root.val);
        } else {
            ((LinkedList<Integer>) tmp).addFirst(root.val);
        }
        dfs(i + 1, root.left, res);
        dfs(i + 1, root.right, res);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            int size = queue.size();
            // 使用size分隔层次 双端队列实现逆序正序
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (res.size() % 2 == 0) {
                    tmp.addLast(poll.val);
                } else {
                    tmp.addFirst(poll.val);
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(0, root, res);
        return res;
    }

    public void dfs(int i, TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (i < res.size()) { // 使用递归层次i分隔层次
            res.get(i).add(root.val);
        } else {
            List<Integer> tmp = new LinkedList<>();
            tmp.add(root.val);
            res.add(tmp);
        }
        dfs(i + 1, root.left, res);
        dfs(i + 1, root.right, res);
    }

    public List<List<Integer>> levelOrderQueue(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        TreeNode tmp = null;
        queue.offer(root);
        queue.offer(null); // 添加null区分层次
        List<Integer> list = new LinkedList<>();
        while (queue.peek() != null) {
            tmp = queue.poll();
            list.add(tmp.val);
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
            if (queue.peek() == null) {
                queue.offer(queue.poll());
                res.add(new LinkedList<>(list));
                list.clear();
            }
        }
        return res;
    }

}
