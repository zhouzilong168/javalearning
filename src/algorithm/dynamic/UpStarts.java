package algorithm.dynamic;

import java.util.Scanner;

/**
 * @ClassName UpStarts
 * @Description TODO 上楼梯问题
 * @Author thinkpad
 * @Date 2020/4/15 16:16
 * @Version 1.0
 **/
public class UpStarts {
    public static int upStairs(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n; i++) {
            dp[i % 3] = dp[(i - 1) % 3] + dp[(i - 2) % 3];
        }
        return dp[(n - 1) % 3];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = upStairs(input.nextInt());
        }
        for (int i :
                arr) {
            System.out.println(i);
        }
    }
}