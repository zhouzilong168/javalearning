package algorithm.leetcode.swordoffer;

import org.junit.Test;

/**
 * TODO HammingWeight && 自定义的次方函数 mypow
 */
public class HammingWeight {
    public static int hammingWeight1(int n) {
        n = n < 0 ? -n : n;
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            int tmp = (int) Math.pow(2, i);
            if (tmp <= n) {
                count++;
                n -= tmp;
            }
        }
        return count;
    }

    public static int hammingWeight(int n) {
        int count = 0, r = 1;
        while (n != 0) {
            if ((n & r) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }

    public double myPow(double x, int n) {
        if (n == 0 || x == 1) return 1;
        if (x == -1) return n % 2 == 0 ? 1 : -1;
        double res = 1.0;
        int tmp;
        if (n == Integer.MIN_VALUE) {
            res *= x;
            tmp = Integer.MAX_VALUE;
        } else {
            tmp = Math.abs(n);
        }
        int count = 0;
        while (tmp > 0) {
            res *= x;
            if (res == 0 || Double.isInfinite(res) || Double.isNaN(res)) {
                return 0.0;
            }
            tmp--;
        }
        return n < 0 ? 1 / res : res;
    }

    @Test
    public void test_H() {
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        Double tmp = Double.MAX_VALUE + Double.MIN_VALUE;
        System.out.println(tmp);
/*        System.out.println(hammingWeight(0));
        System.out.println(hammingWeight(7));
        System.out.println(hammingWeight(1));
        System.out.println(hammingWeight(-1));
        System.out.println(hammingWeight(Integer.MAX_VALUE));
        System.out.println(hammingWeight(Integer.MIN_VALUE));*/
    }

    @Test
    public void test_pow() {
        double res = myPow(-2.0, 2);
        System.out.println(res);
        System.out.println(myPow(2,-2));
        System.out.println(myPow(2,113));
        System.out.println(myPow(2,-113));
        System.out.println(myPow(0.00001, 2147483647));
        System.out.println(myPow(-1, 2147483647));
        System.out.println(myPow(2,Integer.MAX_VALUE));
        System.out.println(myPow(2,Integer.MIN_VALUE));
    }
}
