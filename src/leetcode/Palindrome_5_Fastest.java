package leetcode;

/**
 * @ClassName Palindrome_5_Fastest
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/4 12:31
 * @Version 1.0
 **/
public class Palindrome_5_Fastest {
    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        int[] range = new int[2];
        fun(chars, 0, range);
        return s.substring(range[0], range[1] + 1);
    }

    private void fun(char[] chars, int left, int[] range) {

        if (left >= chars.length - 1) {
            return;
        }

        int right = left;
        while (right < chars.length - 1 && chars[right] == chars[right + 1]) {
            right++;
        }
        int ans = right;
        while (left > 0 && right < chars.length - 1 && chars[left - 1] == chars[right + 1]) {
            left--;
            right++;
        }
        if (right - left > range[1] - range[0]) {
            range[0] = left;
            range[1] = right;
        }

        fun(chars, ans + 1, range);
    }
    /*
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // 把回文看成中间的部分全是同一字符，左右部分相对称
            // 找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
        //查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        //定位中间部分的最后一个字符
        int ans = high;
        //从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        // 记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }
    */
}
