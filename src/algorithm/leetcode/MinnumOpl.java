package algorithm.leetcode;

import org.junit.Test;

/**
 * @ClassName MinnumOpl
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/1 11:51
 * @Version 1.0
 **/
public class MinnumOpl {
    /*
    0表示首部red
    1表示中部yellow
    2表示尾部red
     */
    public int minimumOperations1(String leaves) {
        int[][] dp = new int[leaves.length()][3];
        dp[0][0] = isYellow(leaves.charAt(0));
        dp[0][1] = dp[0][2] = Integer.MAX_VALUE;
        dp[1][0] = dp[0][0] + isYellow(leaves.charAt(1));
        dp[1][1] = dp[0][0] + isRed(leaves.charAt(1));
        dp[1][2] = Integer.MAX_VALUE;
        for (int i = 2; i < leaves.length(); i++) {
            dp[i][0] = dp[i - 1][0] + isYellow(leaves.charAt(i));
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed(leaves.charAt(i));
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow(leaves.charAt(i));
        }
        return dp[leaves.length() - 1][2];
    }

    public int minimumOperations(String leaves) {
        int[][] dp = new int[2][3];
        dp[0][0] = isYellow(leaves.charAt(0));
        dp[1][0] = dp[0][0] + isYellow(leaves.charAt(1));
        dp[1][1] = dp[0][0] + isRed(leaves.charAt(1));
        dp[1][2] = Integer.MAX_VALUE;
        int n = leaves.length(), c = 0;
        for (int i = 2; i < n; i++) {
            dp[c][0] = dp[1 - c][0] + isYellow(leaves.charAt(i));
            dp[c][1] = Math.min(dp[1 - c][0], dp[1 - c][1]) + isRed(leaves.charAt(i));
            dp[c][2] = Math.min(dp[1 - c][1], dp[1 - c][2]) + isYellow(leaves.charAt(i));
            c = 1 - c;
        }
        return dp[(leaves.length() - 1) % 2][2];
    }

    private int isRed(char c) {
        return c == 'r' ? 1 : 0;
    }

    private int isYellow(char c) {
        return c == 'y' ? 1 : 0;
    }

    @Test
    public void test01() {
//        System.out.println(minimumOperations("rrryyyrryyyrr"));
//        System.out.println(minimumOperations("ryr"));
        System.out.println(minimumOperations("ryyryyyrryyyyyryyyrrryyyryryyyyryyrrryryyyryrryrrrryyyrrrrryryyrrrrryyyryyryrryryyryyyyryyrryrryryy"));
    }
}
