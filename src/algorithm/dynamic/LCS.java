package algorithm.dynamic;

import org.junit.Test;

/**
 * @ClassName LCS
 * @Description TODO 动态规划求解最长公共子序列
 * @Author thinkpad
 * @Date 2020/4/9 12:32
 * @Version 1.0
 **/
public class LCS {
    @Test
    public void test1() {
        char[] a = "abcbdb".toCharArray();
        char[] b = "acbbabdbb".toCharArray();
        int aLen = a.length, bLen = b.length;
        int[][] dp = new int[aLen + 1][bLen + 1];
        for (int i = 0; i < aLen; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < bLen; i++) {
            dp[0][i] = 0;
        }
        // 初始化dp数组
        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        for (int i = 0; i <= aLen; i++) {
            for (int t :
                    dp[i]) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
        System.out.println("最长公共子序列长度：" + dp[aLen][bLen]);
        int k = dp[aLen][bLen];
        char[] sub = new char[k--];
        int i = aLen, j = bLen;
        while (k >= 0) {
            if (dp[i][j] == dp[i - 1][j]) { // 同上
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) { // 同左
                j--;
            } else {
                sub[k] = a[i - 1];
                i--;
                j--;
                k--;
            }
        }
        System.out.print("最长公共子序列为:");
        System.out.println(sub);
    }
}
