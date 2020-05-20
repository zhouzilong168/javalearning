package algorithm.leetcode;

/**
 * @ClassName Restore_Array_1416
 * @Description TODO 数组划分，动态规划dp解决
 * @Author thinkpad
 * @Date 2020/5/5 18:29
 * @Version 1.0
 **/
public class Restore_Array_1416 {
    /*
      1 <= s.length <= 10^5.
      s 只包含数字且不包含前导 0 。
      1 <= k <= 10^9.
    */
    static final char ZERO = '0';

    public static int numberOfArrays(String s, int k) {
        int n = s.length(), mod = 10_0000_0007;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // 计算dp 1~n
        for (int i = 0; i < n; i++) {
            dp[i + 1] = 0;
            long now = 0, base = 1; // now可能超过int范围21_4748_3647
            // 每个数最大为k，k最大11位
            for (int j = i, c = 0; j >= 0 && c < 11; j--, c++) {
                //System.out.println("dp[j]: " + j + " " + dp[j]);
                char ch = s.charAt(j);
                now += (ch - ZERO) * base;
                base *= 10;
                //System.out.println("now= " + now);
                if (now > k) {
                    break;
                }
                // 动态规划dp
                if (ch != ZERO) {
                    dp[i + 1] = (dp[i + 1] + dp[j]) % mod;
                }
            }
            //System.out.println("dp[i+1]: " + (i + 1) + " " + dp[i + 1]);
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numberOfArrays("1000", 10000));
        System.out.println(numberOfArrays("1000", 10));
        System.out.println(numberOfArrays("1317", 2000));
        System.out.println(numberOfArrays("2020", 20));
        System.out.println(numberOfArrays("1234567890", 90));
    }
}
