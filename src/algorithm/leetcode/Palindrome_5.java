package algorithm.leetcode;

import org.junit.Test;

/**
 * @ClassName Palindrome_5
 * @Description TODO 最长回文串 中间开始贪心解决
 * @Author thinkpad
 * @Date 2020/4/21 16:58
 * @Version 1.0
 **/
public class Palindrome_5 {

    static int length = 0;
    // 遇到非回文串，减少内存分配，直接定义static final Pair返回，实现一致性
    static final Pair PAIR = new Pair(1, 1);

    /**
     * 最长回文串算法 返回最长回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        Pair res = PAIR, leftp = null, rightp = null, tmp = null;
        length = s.length();// 字符串长度为偶数，直接设置最中间左右索引，两边遍历长度一致
        int right = length / 2, left = right - 1, lenl = 0, lenr = 0;
        if (length % 2 != 0) { // 字符串长度为奇数
            res = traversal(s, right); // 先将中间的字符判断完，然后左右需要遍历长度一致
            right++;
        }
        // 中间开始往两边遍历
        while (left >= 0 || right < length) {
            leftp = traversal(s, left); // 往左遍历
            rightp = traversal(s, right); // 往右遍历
            lenl = leftp.getLen();
            lenr = rightp.getLen();
            tmp = lenl < lenr ? rightp : leftp;
            res = res.getLen() < tmp.getLen() ? tmp : res;
            /**
             * 贪心：当前索引返回的Pair到边界时候，终止循环，再往边界移动指针不会得到更长
             */
            if (/*lenl > 2 && lenr > 2 && */leftp.left < 1 || rightp.right >= length) { // 贪心法
                break;
            }
            left--;
            right++;
        }
        if (res == PAIR) {
            return s.substring(0, 1);
        }
        return s.substring(res.left, res.right);
    }

    /**
     * 指定索引有两种情况，此处调用judgeTwo函数
     *
     * @param s
     * @param index
     * @return
     */
    private Pair traversal(String s, int index) {
        //System.out.println("-----------------------------------\nindex: "+index);
        Pair fir = judgeTwo(s, index - 1, index + 1),
                sec = judgeTwo(s, index, index + 1);
/*        System.out.println("fir: "+fir.left+", "+fir.right);
        System.out.println("sec: "+sec.left+", "+sec.right);*/
        return fir.getLen() < sec.getLen() ? sec : fir;
    }

    /**
     * 指定左右对比索引位置，进行回文判断
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private Pair judgeTwo(String s, int left, int right) {
        for (; left >= 0 && right < length; left--, right++) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
        }
        return right - left <= 2 ? PAIR : new Pair(left + 1, right);
    }

    static class Pair {
        int left, right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLen() {
            return right - left;
        }
    }

    @Test
    public void test() {
        System.out.println(longestPalindrome(""));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("aa"));
        System.out.println(longestPalindrome("aad"));
        System.out.println(longestPalindrome("daa"));
        System.out.println(longestPalindrome("rrdc"));
        System.out.println(longestPalindrome("dcrr"));
        System.out.println(longestPalindrome("rrdd"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("aacdefcaa"));
        System.out.println(longestPalindrome("fffffggggg"));
        System.out.println(longestPalindrome("asdfghjk"));
    }

}