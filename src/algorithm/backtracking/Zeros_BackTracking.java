package algorithm.backtracking;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字节笔试题
 * TODO n个数中挑出k个数，求乘积末尾0的个数最大，回溯法解决
 */
public class Zeros_BackTracking {

    static int n = 0, k = 0, max = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        boolean[] flags = new boolean[n];
        backTracking(arr, flags, 0, 1, 0);
        System.out.println(max);
    }

    /**
     * 回溯法主体
     *
     * @param arr   数组
     * @param flags 对应状态位，表示是否已经访问
     * @param now   当前层次，即已选个数
     * @param nowr  当前乘积
     * @param from  下层遍历起始位置，优化去重
     */
    private static void backTracking(int[] arr, boolean[] flags, int now, int nowr, int from) {
//        System.out.println(now+"   "+nowr+"  "+Arrays.toString(flags));
        if (now == k) {
//            System.out.println(now+"   "+nowr+"  "+Arrays.toString(flags));
            int tmp = getPer(nowr);
            if (max < tmp) {
                max = tmp;
            }
            return;
        }
        for (int i = from; i < n; i++) {
            if (!flags[i]) {
                flags[i] = true;
                backTracking(arr, flags, now + 1, nowr * arr[i], i);
                flags[i] = false; // 回溯 恢复操作
            }
        }
    }

    /**
     * 得到int类型末尾0的个数，题称 完美度
     *
     * @param a
     * @return
     */
    private static int getPer(int a) {
        if (a == 0) {
            return 1;
        }
        int count = 0;
        while (a % 10 == 0) {
            count++;
            a = a / 10;
        }
        return count;
    }
}
