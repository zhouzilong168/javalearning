package algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName BSTK
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/10 17:52
 * @Version 1.0
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public int kthLargest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> resStack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            resStack.push(cur.val);
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        for (int i = 0; i < k - 1; i++) {
            resStack.pop();
        }
        return resStack.element();
    }

    public int kthLargest_dfs(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> resStack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            resStack.push(cur.val);
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        for (int i = 0; i < k - 1; i++) {
            resStack.pop();
        }
        return resStack.element();
    }
}

// 指针传递 递归空间优化
///**
// * Definition for a binary tree node.
// * type TreeNode struct {
// *     Val int
// *     Left *TreeNode
// *     Right *TreeNode
// * }
// */
//func kthLargest(root *TreeNode, k int) int {
//    res := 0
//    dfs(root, &k ,&res)
//    return res
//}
//
//func dfs(root *TreeNode, k *int, res *int) {
//
//    if root == nil || (*k) == 0 {
//        return
//    }
//
//    dfs(root.Right, k, res)
//
//    *k--
//    if (*k) == 0 {
//        *res = root.Val
//        return
//    }
//
//    dfs(root.Left, k, res)
//
//}












