package algorithm.others;

/**
 * @ClassName KMP
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/18 13:01
 * @Version 1.0
 **/
public class KMP {
    /*
     * 逻辑：模式串已匹配部分，最长前缀和最长后缀相等 实现回退
     * */
    // KMP算法主体逻辑。str是主串，pattern是模式串
    public static int kmp(String str, String pattern) {
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int j = 0;
        //主循环，遍历主串字符
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                //遇到坏字符时，查询next数组并改变模式串的起点
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                //匹配成功，返回下标
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

    // 生成Next数组
    private static int[] getNexts(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 2; i < pattern.length(); i++) {
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i - 1)) {
                //从next[i+1]的求解回溯到 next[j] 可直到j==0
                j = next[j];
            }
            // 回溯后对应 j位置和 i-1位置相等则+1
            if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    // 坏字符和好后缀 两种 可以用来比较 取两者中移动距离大的那个
    // 坏字符：向前找相等的
    // 好后缀：模式串后面匹配的后缀与前者的比较 直接移动 注意若无相等则要看后缀的后缀
    public static int BM(String str, String pattern) {
        if (str.length() < pattern.length()) {
            return -1;
        }
        int len = pattern.length(), i = len - 1, j = i;
        while (true) {
            // 找到第一次坏字符或者匹配完成
            j = len - 1;
            while (j >= 0 && str.charAt(i) == pattern.charAt(j)) {
                i--;
                j--;
            }
            if (j == -1) {
                break;
            }
            // 模式串往前找匹配主串i位上的字符
            j--;
            while (j >= 0 && pattern.charAt(j) != str.charAt(i)) {
                j--;
            }
            // 匹配到头未找到
            if (j == -1) {
                i += len;
            } else { // 找到更新i的位置往后加对应长度
                i += len - 1 - j;
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        String str = "ATGTGAGCTGGTGTGTGCFAA";
        String pattern = "GTGTGCF";
        int index = kmp(str, pattern);
        System.out.println("index for first occur: " + index);
        System.out.println("index for first occur: " + BM(str, pattern));
    }
}
