package algorithm.dynamic;

import java.util.Scanner;

/**
 * TODO 上楼梯问题，一步1、2阶或3阶，但3阶只能有一次，两次dp求解（携程笔试）
 */
public class Upstairs_Constraint {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int climb(int n) {
        if (n < 1) {
            return -1;
        } else if (n < 3) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        //System.out.println(Arrays.toString(dp));
        int[] dpp = new int[n + 1];
        dpp[0] = 1;
        dpp[1] = 1;
        dpp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dpp[i] = dpp[i - 1] + dpp[i - 2] + dp[i - 3];
        }
        //System.out.println(Arrays.toString(dpp));
        return dpp[n];
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        res = climb(_n);

        System.out.println(String.valueOf(res));
    }
}
