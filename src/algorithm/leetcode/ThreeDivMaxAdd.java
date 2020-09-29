package algorithm.leetcode;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.Arrays;

/**
 * @ClassName ThreeDivMaxAdd
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/26 10:45
 * @Version 1.0
 **/
public class ThreeDivMaxAdd {

    public static int maxSumDivThree(int[] nums) {
        int[][] dp = new int[2][3];
        dp[0][0] = nums[0] % 3 == 0 ? nums[0] : 0;
        dp[0][1] = nums[0] % 3 == 1 ? nums[0] : Integer.MIN_VALUE;
        dp[0][2] = nums[0] % 3 == 2 ? nums[0] : Integer.MIN_VALUE;
        int c = 0;
        for (int i = 1; i < nums.length; i++) {
            switch (nums[i] % 3) {
                case 0: {
                    dp[1 - c][0] = Math.max(dp[c][0], dp[c][0] + nums[i]);
                    dp[1 - c][1] = Math.max(dp[c][1], dp[c][1] + nums[i]);
                    dp[1 - c][2] = Math.max(dp[c][2], dp[c][2] + nums[i]);
                    break;
                }
                case 1: {
                    dp[1 - c][0] = Math.max(dp[c][0], dp[c][2] + nums[i]);
                    dp[1 - c][1] = Math.max(dp[c][1], dp[c][0] + nums[i]);
                    dp[1 - c][2] = Math.max(dp[c][2], dp[c][1] + nums[i]);
                    break;
                }
                case 2: {
                    dp[1 - c][0] = Math.max(dp[c][0], dp[c][1] + nums[i]);
                    dp[1 - c][1] = Math.max(dp[c][1], dp[c][2] + nums[i]);
                    dp[1 - c][2] = Math.max(dp[c][2], dp[c][0] + nums[i]);
                    break;
                }
            }
            c = 1 - c;
        }
        return dp[c][0];
    }

    public static int maxSumDivThree1(int[] nums) {
        int[][] dp = new int[nums.length][3];
        dp[0][0] = nums[0] % 3 == 0 ? nums[0] : 0;
        dp[0][1] = nums[0] % 3 == 1 ? nums[0] : Integer.MIN_VALUE;
        dp[0][2] = nums[0] % 3 == 2 ? nums[0] : Integer.MIN_VALUE;
        for (int i = 1; i < dp.length; i++) {
            switch (nums[i] % 3) {
                case 0: {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + nums[i]);
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + nums[i]);
                    dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + nums[i]);
                    break;
                }
                case 1: {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + nums[i]);
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i]);
                    dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + nums[i]);
                    break;
                }
                case 2: {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i]);
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + nums[i]);
                    dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i]);
                    break;
                }
            }
        }
        return dp[dp.length - 1][0];
    }

    public static void main(String[] args) {
        System.out.println(maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
    }

    class A {
        void d(int a) throws IOException {

        }
    }

    // private default protected public
    // OverWrite 权限大于等于 异常是继承类
    class B extends A {
        void d(int a) throws IOException{
            FileInputStream fis = new FileInputStream("q.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            FileReader fr = new FileReader("q.txt");
            BufferedReader brr = new BufferedReader(fr);
        }

        void d(float a) throws Exception {

        }
    }

    public abstract class C {
        abstract void c(int a);
    }
}
