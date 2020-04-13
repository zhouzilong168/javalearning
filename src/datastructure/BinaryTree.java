package datastructure;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinaryTree
 * @Description TODO 二叉树ADT
 * @Author thinkpad
 * @Date 2020/4/11 12:15
 * @Version 1.0
 **/
public class BinaryTree<T> {
    private T data;
    private BinaryTree left;
    private BinaryTree right;


    // trans other types to binary tree=================Start

    /**
     * 转换数组为二叉树，数组0号位弃用
     * @param array
     * @return
     */
    public BinaryTree<T> arrayToBinaryTree(T[] array) {
        BinaryTree root = new BinaryTree(array[1]);
        transToArray(root, array, 1);
        return root;
    }

    public void transToArray(BinaryTree root, T[] array, int i) {
        if (2 * i < array.length) {
            root.setLeft(new BinaryTree(array[2 * i]));
            transToArray(root.getLeft(), array, i * 2);
        }
        if (2 * i + 1 < array.length) {
            root.setRight(new BinaryTree(array[2 * i + 1]));
            transToArray(root.getRight(), array, i * 2 + 1);
        }
    }
    // trans other types to binary tree=================Start

    /* Construction get/set toString=================================Start*/

    public BinaryTree() {
    }

    public BinaryTree(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
    /* get/set toString=================================End*/
}
