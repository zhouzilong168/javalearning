package datastructure;

import algorithm.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Traversal
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/4/13 16:51
 * @Version 1.0
 **/
public class Traversal<T> {

    // sovle some problem

    /**
     * 按层次逆序分层输出，利用两个队列
     *
     * @param root
     */
    public int LevelOrderTraveralInReversePlus(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        // 利用队列后入先出的特性实现层次遍历
        int level = 0; // 计算层次
        Queue<BinaryTree> queue = new LinkedList<>();
        Deque<BinaryTree> stack = new LinkedList<>(); // 栈实现逆序
        queue.offer(root);
        queue.offer(null); // 第一层遍历完毕
        while (!queue.isEmpty()) {
            root = queue.poll();
            stack.push(root);
            if (root == null) { // 插入null值分隔层次 当前层遍历完毕 插入null分隔新层
                if (!queue.isEmpty()) {
                    stack.push(null);
                    queue.offer(null);
                    level++;
                }
            } else {
                System.out.print(root.getData() + " ");
                if (root.getLeft() != null) {
                    queue.offer(root.getLeft());
                }
                if (root.getRight() != null) {
                    queue.offer(root.getRight());
                }
            }
        }
        System.out.println();
        // 利用栈后入先出的特性，实现逆序输出，null值分隔层次
        while (!stack.isEmpty()) {
            if (stack.peek() == null) {
                stack.pop();
                System.out.println();
            } else {
                System.out.print(stack.pop().getData() + " ");
            }
        }
        return level;
    }

    /**
     * 按层次逆序分层输出，利用两个队列
     *
     * @param root
     */
    public void LevelOrderTraveralInReverse(BinaryTree root) {
        if (root == null) {
            return;
        }
        BinaryTree tmp = new BinaryTree();
        // 利用队列后入先出的特性实现层次遍历
        Queue<BinaryTree> queue = new LinkedList<>(); // 指向非空队列
        Queue<BinaryTree> queue1 = new LinkedList<>(); // 空队列
        Deque<BinaryTree> stack = new LinkedList<>(); // 栈实现逆序
        queue.offer(root);
        while (!queue.isEmpty() || !queue1.isEmpty()) { // 两队列中有元素就未遍历完
            if (queue.isEmpty()) { // 按需交换指向
                Queue t = queue;
                queue = queue1;
                queue1 = t;
            }
            while (!queue.isEmpty()) {
                tmp = queue.poll();
                stack.push(tmp);
                if (queue.isEmpty()) { // 插入null值分隔层次
                    stack.push(null);
                }
                System.out.print(tmp.getData() + " ");
                if (tmp.getLeft() != null) {
                    queue1.offer(tmp.getLeft());
                }
                if (tmp.getRight() != null) {
                    queue1.offer(tmp.getRight());
                }
            }
        }
        System.out.println();
        // 利用栈后入先出的特性，实现逆序输出，null值分隔层次
        while (!stack.isEmpty()) {
            if (stack.peek() == null) {
                stack.pop();
                System.out.println();
            } else {
                System.out.print(stack.pop().getData() + " ");
            }
        }
    }

    /* traversal =======================================Start*/
    Deque<BinaryTree> stack = new LinkedList<>();

    public void preOrderRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getData() + " ");
        preOrderRecursion(root.getLeft());
        preOrderRecursion(root.getRight());
    }

    public void preOrderNonRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        // 用栈保存遍历过已访问的节点，当遍历到最左的时候弹出以便访问右节点
        stack.clear();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.print(root.getData() + " "); // 直接遍历当前根
                stack.push(root); // 保存根用于访问右节点
                root = root.getLeft(); // “递归”寻找左节点
            }// 栈中为空，则说明所有遍历过的根节点为空，root也遍历到null了，遍历完了
            root = stack.pop().getRight(); // 获得根节点去得到右节点右子树
        }
    }

    public void inOrderRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        inOrderRecursion(root.getLeft());
        System.out.print(root.getData() + " ");
        inOrderRecursion(root.getRight());
    }

    public void inOrderNonRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        // 用栈保存遍历过为访问的节点，后期弹栈，遍历再获取右节点
        stack.clear();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root); // 左节点先与根节点访问，则先入栈后期弹栈访问，然后获得右节点
                root = root.getLeft(); // ”递归“寻找最左的节点先访问
            }
            root = stack.pop(); // 弹栈访问根节点
            System.out.print(root.getData() + " ");
            root = root.getRight(); // 访问右节点右子树
        }
    }

    public void postOrderRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        postOrderRecursion(root.getLeft());
        postOrderRecursion(root.getRight());
        System.out.print(root.getData() + " ");
    }

    public void postOrderNoRecursion(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else { // 直接找到最左节点 找右节点访问右子树 右节点为空 直接访问 再判断是否为栈顶节点右节点
                root = stack.peek().right;
                if (root == null) {
                    TreeNode tmp = stack.pop();
                    System.out.print(tmp.val + " ");
                    while (!stack.isEmpty() && tmp == stack.peek().right) {
                        tmp = stack.pop();
                        System.out.print(tmp.val + " ");
                    }
                }
            }
        }
    }

    public void postOrderNonRecursion(BinaryTree root) {
        if (root == null) {
            return;
        }
        /**
         * 用栈保存遍历过并未访问的节点，后期弹栈，先检查是否有右节点（栈中存储的意为根节点）
         * 有，则root指向右节点，先遍历右子树
         * 无，则访问右节点（说明左子树访问完了）
         *      然后要判断栈顶元素（根节点）的右节点是否为当前访问节点，意为当前访问的是右/左子树
         *      否，则说明访问的是 左子树：
         *          root指向栈顶元素的右节点，去判断右子树；
         *      是，则说明访问的是 右子树：
         *          直接弹栈，访问栈顶元素；
         * tip: 只有两处打印
         *      一是：叶子节点或右子树为空的节点
         *      二是：循环判断 上打印节点为右孩子的节点
         */
        stack.clear();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.getLeft(); // 右节点先与左节点访问，先遍历到当前子树的最左节点
            } else {
                if (stack.peek().getRight() == null) { // 遍历到叶子节点或右子树为空的节点，则打印
                    root = stack.pop();
                    System.out.print(root.getData() + " ");
                    // 判断当前遍历节点是否为栈顶（父节点）的右孩子，循环判断，递归向上，遍历到最大的右子树
                    while (!stack.isEmpty() && root == stack.peek().getRight()) {
                        root = stack.pop();
                        System.out.print(root.getData() + " ");
                    }
                }
                if (!stack.isEmpty()) { // 栈非空就获得栈顶（左子树遍历后的右子树右节点），为空则循环进入打印
                    root = stack.peek().getRight();
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 层次遍历 借助队列
     *
     * @param root
     */
    public void levelOrder(BinaryTree root) {
        if (root == null) {
            return;
        }
        BinaryTree tmp = new BinaryTree();
        // 利用队列后入先出的特性实现层次遍历
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            System.out.print(tmp.getData() + " ");
            if (tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            }
        }
    }
    /* traversal =======================================End*/
}
