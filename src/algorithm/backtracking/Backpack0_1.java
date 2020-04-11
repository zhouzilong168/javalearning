package algorithm.backtracking;

import org.junit.Test;

/**
 * @ClassName Backpack0_1
 * @Description TODO 回溯法求解0/1背包问题
 * @Author thinkpad
 * @Date 2020/4/11 10:01
 * @Version 1.0
 **/
public class Backpack0_1 {

    @Test
    public void test0_1() {
        calV(1, 0, 0);
        System.out.println(maxw + " " + maxv);
        for (int i :
                w) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i :
                v) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i :
                res) {
            System.out.print(i + " ");
        }
    }

    /*0/1背包*/
    static final int NUM = 4;
    static final int WEIGHT = 6;
    static int[] w = {0, 5, 3, 2, 1};
    static int[] v = {0, 4, 4, 3, 1};

    static int[] x = new int[NUM + 1];
    static int[] res = new int[NUM + 1];
    static int maxw = 0;
    static int maxv = 0;
    static int tolw = 0;
    static int tolv = 0;

    /**
     * 全局变量 恢复回溯操作
     *
     * @param i
     */
    void calV(int i) {
        if (i > NUM) {
            if (tolw <= WEIGHT && tolv > maxv) {
                maxw = tolw;
                maxv = tolv;
                System.arraycopy(x, 0, res, 0, x.length);
//                res=Arrays.copyOf(x,x.length);
            }
            return;
        }
        x[i] = 0;
        calV(i + 1);
        x[i] = 1;
        tolw += w[i];
        tolv += v[i];
        calV(i + 1);
        x[i] = 0;
        tolw -= w[i];
        tolv -= v[i];
    }

    /**
     * 形参递归恢复操作
     *
     * @param i
     * @param tolw 当前总容量
     * @param tolv 当前总价值
     */
    void calV(int i, int tolw, int tolv) {
        if (i > NUM) {
            if (tolw <= WEIGHT && tolv > maxv) {
                maxw = tolw;
                maxv = tolv;
                System.arraycopy(x, 0, res, 0, x.length);
            }
            return;
        }
        if (tolw + tolW(i) > WEIGHT) { // 左剪枝 减去加上当前i小于等于WEIGHT的节点
            x[i] = 0;
            calV(i + 1, tolw, tolv);
        }
        if (tolw + w[i] <= WEIGHT) {// 右剪枝 减去加上当前节点大于WEIGHT的节点
            x[i] = 1;
            calV(i + 1, tolw + w[i], tolv + v[i]);
        }
    }

    /**
     * 计算当前i及之后的所有物品选中的最大容量
     *
     * @param i
     * @return
     */
    private int tolW(int i) {
        int rw = 0;
        for (int j = i; j < w.length; j++) {
            rw += w[j];
        }
        return rw;
    }
}
