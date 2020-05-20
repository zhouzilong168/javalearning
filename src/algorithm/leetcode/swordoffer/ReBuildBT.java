package algorithm.leetcode.swordoffer;


import org.junit.Test;

/**
 * @ClassName ReBuildBT
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/18 18:07
 * @Version 1.0
 **/
public class ReBuildBT {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + " ";
        }
    }

    class Better {
        int i = 0;
        int j = 0;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return reConstructBinaryTree(preorder, inorder, null);
        }

        public TreeNode reConstructBinaryTree(int[] pre, int[] in, TreeNode parent) {
            // 当中序序列遍历到父节点，即为当前父节点（parent）对应的左子树遍历完了，直接返回null，或者越界
            if ((parent != null && in[j] == parent.val) || j == pre.length) {
                return null;
            }
            // 1,2,4,5,3,6,7
            // 4,2,5,1,6,3,7
            TreeNode node = new TreeNode(pre[i]);
            // 当遍历到pre[i] == in[j]时，说明遍历到当前子树最左节点，不再往下递归
            if (pre[i] == in[j]) {
                i++;
                node.left = null; // 设置当前左节点为null
                j++;
            } else {
                i++;
                node.left = reConstructBinaryTree(pre, in, node);// 左子树右节点，则继续递归遍历
                j++;
            }
            // 已经遍历完所有的左节点，然后遍历右节点
            node.right = reConstructBinaryTree(pre, in, parent);// 设置右节点，若内部遍历到父节点则返回null
            return node;
        }
    }


    /*-----------------------------------------------------------------------------------------*/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        domain(preorder, 0, inorder, 0, preorder.length - 1, root);
        return root;
    }

    private void domain(int[] preorder, int pl, int[] inorder, int il, int ir, TreeNode root) {
        int tmp = 0, lsize = 0;
        // 获得当前根节点在中序中的位置
        for (; tmp < inorder.length && preorder[pl] != inorder[tmp]; tmp++) ;

        lsize = tmp - il;
        if (lsize > 0) {// 当前根左边有元素，前序中根之后的就是左孩子
            if (pl + 1 < preorder.length) {
                root.left = new TreeNode(preorder[pl + 1]);
                if (lsize > 1) { // 左子树大小大于1，以左孩子为根递归进去，限定左子树范围
                    domain(preorder, pl + 1, inorder, il, tmp - 1, root.left);
                }
            }
        }
        if (ir > tmp) { // 当前根右边有元素，前序中根左子树大小之后的就是右孩子
            int t = pl + lsize + 1;
            if (t < preorder.length) {
                root.right = new TreeNode(preorder[t]);
                if (ir - tmp > 1) {// 右子树大小大于1，以右孩子为根递归进去，限定右子树范围
                    domain(preorder, t, inorder, tmp + 1, ir, root.right);
                }
            }
        }
    }

    @Test
    public void test() {
        int[] pre = {3, 9, 20, 15, 7}, in = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(pre, in);
        tra(root);
    }

    private void tra(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        tra(root.left);
        tra(root.right);
    }
}
