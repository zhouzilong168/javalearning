package algorithm.others;

import algorithm.leetcode.LevelTraverse;
import algorithm.leetcode.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TreeArray
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/8 18:10
 * @Version 1.0
 **/
public class TreeArray {
    //    class TreeNode {
//        int val;
//        TreeNode left, right;
//
//        public TreeNode() {
//        }
//
//        public TreeNode(int val) {
//            this.val = val;
//        }
//    }
    @Test
    public void test1() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, null);
        System.out.println(map);
        map.put(2, null);
        System.out.println(map);
        System.out.println(map.get(null));
        HashSet<Object> set = new HashSet<>();
        set.add(null);
        System.out.println(set);
        set.add(1);
        System.out.println(set);
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
    }

    public TreeNode ArrayToTree(int[] arr) {
        TreeNode[] tree = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            tree[i] = tree[i] == null ? new TreeNode(arr[i]) : tree[i];
            int left = 2 * i + 1;
            if (left < arr.length) {
                tree[i].left = tree[left] = tree[left] == null ? new TreeNode(arr[left]) : tree[left];
                int right = 2 * i + 2;
                if (right < arr.length) {
                    tree[i].right = tree[right] = tree[right] == null ? new TreeNode(arr[right]) : tree[right];
                }
            }
        }
        return tree[0];
    }

    public int[] TreeToArray(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            res.add(tmp.val);
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public void preOrderNo(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                System.out.print(root.val + " ");
                root = root.left;
            }
            root = stack.pop().right;
        }
        System.out.println();
    }

    public void inOrderNo(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode tmp = stack.pop();
            System.out.print(tmp.val + " ");
            root = tmp.right;
        }
        System.out.println();
    }

    public TreeNode TreeToDoubleList(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        TreeNode res = root;
        while (res.left != null) {
            res = res.left;
        }
        while (root != null || !stack.isEmpty()) {
            while (root != null && !set.contains(root.val)) {
                stack.push(root);
                root = root.left;
            }
            TreeNode tmp = stack.pop();
            set.add(tmp.val);
            if (stack.isEmpty() && (tmp.left == null || set.contains(tmp.left.val)) && (tmp.right == null || set.contains(tmp.right.val))) {
                res.left = tmp;
                tmp.right = res;
                break;
            }
            System.out.print(tmp.val + " ");
            root = tmp.right;
            if (root != null) {
                while (root != null && !set.contains(root.val)) {
                    stack.push(root);
                    root = root.left;
                }
                stack.peek().left = tmp;
                tmp.right = stack.peek();
            } else {
                TreeNode temp = stack.peek();
                if (temp != null) {
                    tmp.right = temp;
                    temp.left = tmp;
                }
            }
        }
        System.out.println();
        return res;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = head, cur = head.next; //0 1 1 1 1 1 3 3 3 2   123
        // 筛选出pre为head
        while (pre != null && cur != null && pre.val == cur.val) {
            while (cur != null && pre.val == cur.val) {
                cur = cur.next;
            }
            pre = cur;
            cur = cur == null ? null : cur.next;
        }
        head = pre;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = cur;
                cur = cur.next;
                continue;
            }
            pre.next = cur.next;
            cur = cur.next;
        }
        return head;
    }

    @Test
    public void ttt() {
        ListNode root = deleteDuplicates1(get(new int[]{1, 2, 2, 2, 1, 1, 3}));
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
        System.out.println();
    }

    public ListNode get(int[] arr) {
        ListNode[] ar = new ListNode[arr.length];
        ar[0] = new ListNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            ar[i] = new ListNode(arr[i]);
            ar[i - 1].next = ar[i];
        }
        return ar[0];
    }

    /**
     * plus
     * 可以实现非排序链表的去重，pre也参与比较
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // write code here
        ListNode pre = head, cur = head.next;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur && pre.val != cur.val) {
                pre = cur;
                cur = cur.next;
                continue;
            }
            if (pre.val == cur.val) {
                head = cur.next;
                if (head == null) {
                    break;
                }
                pre = head;
                cur = head.next;
            } else {
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return head;
    }

//    class Solution {
//        Node pre, head;
//        public Node treeToDoublyList(Node root) {
//            if(root == null) return null;
//            dfs(root);
//            head.left = pre;
//            pre.right = head;
//            return head;
//        }
//        void dfs(Node cur) {
//            if(cur == null) return;
//            dfs(cur.left);
//            if(pre != null) pre.right = cur;
//            else head = cur;
//            cur.left = pre;
//            pre = cur;
//            dfs(cur.right);
//        }
//    }

//    public Node treeToDoublyList(Node root) {
//        if (root == null) {
//            return null;
//        }
//        LinkedList<Node> stack = new LinkedList<>();
//        Set<Integer> set = new HashSet<>();
//        Node res = root;
//        while (res.left != null) {
//            res = res.left;
//        }
//        while (root != null || !stack.isEmpty()) {
//            while (root != null && !set.contains(root.val)) {
//                stack.push(root);
//                root = root.left;
//            }
//            Node tmp = stack.pop();
//            set.add(tmp.val);
//            if (stack.isEmpty() && (tmp.left == null || set.contains(tmp.left.val)) && (tmp.right == null || set.contains(tmp.right.val))) {
//                res.left = tmp;
//                tmp.right = res;
//                break;
//            }
//            root = tmp.right;
//            if (root != null) {
//                root.left = tmp;
//            } else {
//                Node temp = stack.peek();
//                if (temp != null) {
//                    tmp.right = temp;
//                    temp.left = tmp;
//                }
//            }
//        }
//        return res;
//    }

    public void laterOrderNo(TreeNode root) {
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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.peek().right;
                if (root == null) {
                    TreeNode tmp = stack.pop();
                    res.add(tmp.val);
                    while (!stack.isEmpty() && tmp == stack.peek().right) {
                        tmp = stack.pop();
                        res.add(tmp.val);
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test() throws InterruptedException {
        LevelTraverse l = new LevelTraverse();
        TreeNode root = ArrayToTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(l.levelOrderDFSZ(root));
        System.out.println(Arrays.toString(TreeToArray(root)));
        preOrderNo(root);
        inOrderNo(root);
        laterOrderNo(root);
        System.out.println();
        root = ArrayToTree(new int[]{4, 2, 5, 1, 3});
        inOrderNo(root);
        root = TreeToDoubleList(root);
        while (root != null) {
            System.out.println(root.val + " ");
            if (root.right == null) {
                break;
            }
            Thread.sleep(1000);
            root = root.right;
        }
        System.out.println();
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.left;
        }
        System.out.println();
    }

}
