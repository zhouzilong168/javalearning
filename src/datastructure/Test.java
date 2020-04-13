package datastructure;

/**
 * @ClassName Test
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/4/11 17:38
 * @Version 1.0
 **/
public class Test<T> {
    @org.junit.Test
    public void testTraversal() {
        Traversal t = new Traversal();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        BinaryTree root = new BinaryTree().arrayToBinaryTree((T[]) array);
        t.LevelOrderTraveralInReverse(root);
    }
}
