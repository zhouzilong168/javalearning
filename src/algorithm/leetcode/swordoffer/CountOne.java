package algorithm.leetcode.swordoffer;

/**
 * @ClassName CountOne
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/12 16:53
 * @Version 1.0
 **/
public class CountOne {
    public int countDigitOne(int n) {
        int res = 0, digit = 1, high = n / 10, low = 0, cur = n % 10;
        while (high != 0 || cur != 0) {
            if (cur == 0) res += high * digit;
            else if (cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
