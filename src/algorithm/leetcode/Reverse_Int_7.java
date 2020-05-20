package algorithm.leetcode;

import java.util.Arrays;

/**
 * @ClassName Reverse_Int_7
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/9 12:11
 * @Version 1.0
 **/
public class Reverse_Int_7 {

    static int[] MAXDIGITS = new int[10];

    /**
     * 保存INT边界值位值，-2^31~2^31-1  2^31=2,147,483,648
     * 2,147,483,647
     */
    private static void getMaxD() {
        int max = Integer.MAX_VALUE;
        for (int i = 9; i >= 0; i--) {
            MAXDIGITS[i] = max % 10;
            max /= 10;
        }
    }

    public static int reverse(int x) {
        getMaxD();
        int res = x / 10, digits = 1, base = 1;
        for (; res != 0; res /= 10) {
            digits++;
            base *= 10;
        }
        if (digits < 10) {
            while (x != 0) {
                res += x % 10 * base;
                base /= 10;
                x /= 10;
            }
        } else {
            int tmp;
            for (int i = 0; i < 10; i++) {
                tmp = x % 10;
                if (tmp > MAXDIGITS[i] || tmp < -MAXDIGITS[i]) {
                    return 0;
                } else if (tmp < MAXDIGITS[i] && tmp > -MAXDIGITS[i]) {
                    for (int j = i; j < 10; j++) {
                        res += tmp * base;
                        base /= 10;
                        x /= 10;
                        tmp = x % 10;
                    }
                    break;
                } else if (tmp == MAXDIGITS[i] || tmp == -MAXDIGITS[i]) {
                    res += tmp * base;
                    base /= 10;
                    x /= 10;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(120));
        System.out.println(reverse(2033333333));
        System.out.println(reverse(1));
        System.out.println(reverse(0));
        System.out.println(reverse(-123));
        System.out.println(reverse(-120));
        System.out.println(reverse(-1));
        System.out.println(reverse(-2147483412));
        System.out.println(reverse(1563847412));
        System.out.println(Arrays.toString(MAXDIGITS));
        /*2,147,483,647  -2143847412*/
    }
}
