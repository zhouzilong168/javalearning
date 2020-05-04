package datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinaryTree_Parent
 * @Description TODO 三指针（带父节点指针）的二叉树，及相关操作
 * @Author thinkpad
 * @Date 2020/4/26 22:00
 * @Version 1.0
 **/
public class BinaryTree_Parent {
    private int data;
    private BinaryTree_Parent left;
    private BinaryTree_Parent right;
    private BinaryTree_Parent parent;

    public BinaryTree_Parent() {
    }

    public BinaryTree_Parent(int data) {
        this.data = data;
    }

    public BinaryTree_Parent(int data, BinaryTree_Parent left, BinaryTree_Parent right, BinaryTree_Parent parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    private static final int size = 20;

    public BinaryTree_Parent init_order() {
        BinaryTree_Parent[] arr = new BinaryTree_Parent[size + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = new BinaryTree_Parent(i);
        }
        BinaryTree_Parent root = arr[1];
        root.left = arr[2];
        root.right = arr[3];
        for (int i = 2; i < arr.length; i++) {
            arr[i].parent = arr[i / 2];
            if (i * 2 < arr.length) {
                arr[i].left = arr[i * 2];
                if (i * 2 + 1 < arr.length) { // 左孩子在界内右孩子才有可能在
                    arr[i].right = arr[i * 2 + 1];
                }
            }
        }
        return root;
    }

    /**
     * 层次遍历 借助队列
     *
     * @param root
     */
    public void levelOrder(BinaryTree_Parent root) {
        if (root == null) {
            return;
        }
        BinaryTree_Parent tmp = new BinaryTree_Parent();
        // 利用队列后入先出的特性实现层次遍历
        Queue<BinaryTree_Parent> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            System.out.print(tmp.data + " ");
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
    }

    /**
     * 搜索二叉树，返回对应节点
     *
     * @param root
     * @param value
     * @return
     */
    public BinaryTree_Parent search(BinaryTree_Parent root, int value) {
        if (root == null) {
            return null;
        } else if (root.data == value) {
            return root;
        } else {
            BinaryTree_Parent tmp = search(root.left, value);
            if (tmp != null) { // 当前左子树查到了对应节点，直接返回
                return tmp;
            }
            return search(root.right, value);// 否则搜索右子树
        }
    }

    /**
     * 判断对应值节点在二叉树中对应层次的节点的值
     *
     * @param root
     * @param value
     * @param level
     */
    public void judge_level(BinaryTree_Parent root, int value, int level) {
        int i = 0;
        while (true) {
            if (Math.pow(2, i) > value) {
                break;
            }
            i++;
        }
        BinaryTree_Parent cur = null;
        if (level > i || level < 1) {
            System.out.println("error");
        } else {
            cur = search(root, value);
            while (i != level) {
                cur = cur.parent;
                i--;
            }
        }
        if (cur != null) {
            System.out.println(cur.data);
        }
    }
}
