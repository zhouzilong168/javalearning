package leetcode;

import org.junit.Test;

/**
 * @ClassName Palindrome_5
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/4/21 16:58
 * @Version 1.0
 **/
public class Palindrome_5 {
    static int count = 0, counts = 0, length = 0;
    String res = null;

    public String longestPalindrome(String s) {
        length = s.length();
        t(s, (length - 1) / 2, length / 2);
        return res;
    }

    private void t(String s, int mid, int maxLen) {
        counts = 1;
        for (int i = 1; mid - i >= 0 && s.charAt(mid - i) == s.charAt(mid + i); i++) {
            counts++;
        }
        count = 0;
        for (int i = 0; mid - i >= 0 && s.charAt(mid - i) == s.charAt(mid + i + 1); i++) {
            count++;
        }
        if (counts >= maxLen - 1) {
            res = s.substring(mid);
        } else if (count >= maxLen - 1) {
            res = s.substring(mid - count + 1, mid + count + 1);
        } else {
            t(s, mid - 1, maxLen - 1);
            t(s, mid + 1, maxLen - 1);
        }
    }

    @Test
    public void test() {
        longestPalindrome("babad");
        System.out.println(res);
    }
}
