package algorithm.others;

import algorithm.leetcode.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @ClassName TenthWithSame
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/9 16:05
 * @Version 1.0
 **/
public class TenthWithSame {
    public int thirdMax(int[] nums) {
        System.out.println(null == null);
        TreeSet<Integer> set = new TreeSet<>((a, b) -> b - a);
        set.addAll(Arrays.asList(4, 1, 5, 6, 41, 14, 5));
        System.out.println(set);
        return 0;
    }
    private int pri = 1;

    class t {
        void test() {
            System.out.println(pri);
        }
    }

    public static Object single;

    public static Object getSingle() {
        if (single == null) {
            synchronized (TenthWithSame.class) {
                if (single == null) {
                    single = new Object();
                }
            }
        }
        return single;
    }

    @Test
    public void test() {
        thirdMax(new int[]{});
    }
}
