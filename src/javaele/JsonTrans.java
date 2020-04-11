package javaele;

import org.junit.Test;
import sort.SortUtils;

import java.util.Arrays;

/**
 * Json "flat" to "steric"
 * 转换 扁平的JSON串 为 立体的JSON串
 *
 * @ClassName JsonTrans
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/4/6 10:56
 * @Version 1.0
 **/
public class JsonTrans {
    @Test
    public void test1() {
        int[] arr = {0, 6, 0, 4, 0, -3, 0, 5, -2, 0, -1, 0, 1, 0, -9, 0};
        int[] ar = {-1, -2, -6, -5};
        SortUtils.printInts(arr);
        int e = 0, i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i] > 0) {
                SortUtils.swap(arr, e++, i++);
            } else if (arr[i] < 0) {
                while (i < j & arr[j] < 0) j--;// 加一个i<j的判断，避免全负数越界
                if (i < j) {
                    SortUtils.swap(arr, i, j--);
                }
            } else {
                i++;
            }
        }
        SortUtils.printInts(arr);
    }

    @Test
    public void test2() {
        System.out.println(str);
        System.out.println(Arrays.toString(arr));
        System.out.println("---------------------------");
        trans();
        System.out.println("---------------------------");
        System.out.println(builder);
        System.out.println("---------------------------");
    }

    //String str = "{'A':1,'B.A':2,'B.B':3,'CC.D.E':4,'CC.D.F':5}";
    String str = "{'A':1,'B.A':2,'B.B':3,'CC.D.E':4,'CC.D.E.A':5,'CC.D.F':5,'CC.E.F':6,'CC.E.F.Q.W.R.T':7}";
    String[] arr = str.substring(1, str.length() - 1).split(",");
    StringBuilder builder = new StringBuilder(ONE);
    int cur = 1;
    static final String DOT = ".";
    static final String TAB = "\t";
    static final String LINE = "\n";
    static final String ONE = "{\n";
    static final String TWO = ",\n";
    static final String THREE = "\n}";

    /**
     * 依次转换split数组中的元素
     */
    private void trans() {
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].contains(DOT)) {
                builder.append(arr[i]);
            } else {
                int c = compare(i - 1, i);
                if (c == -1) { // 没有相同部分
                    builder.append(TWO);
                    transCom(i, 0, 0);
                } else { // 相同部分有几个点
                    int t = countDot(i, c);
                    cur = builder.length() - (t + 3) * t / 2;
                    builder.insert(cur, TWO);
                    transCom(i, c, t);
                }
            }
        }
        builder.append(THREE);
    }

    /**
     * 转换有相同部分的串
     *
     * @param in 当前字符串元素
     * @param c  最大相同.所在位置
     * @param t  尾插倒数位数
     */
    private void transCom(int in, int c, int t) {
        int index = (t + 3) * t / 2; // 插入tab符后的应插入位置
        // 按相同部分.的数目插入TAB符号
        for (int co = t; co > 0; co--) {
            builder.insert(builder.length() - index, TAB);
        }
        int i = c + 1, j = arr[in].indexOf(DOT, i), count = 0;
        // 转换不相同部分的字符串
        while (j > -1) {
            builder.insert(builder.length() - index, "'" + arr[in].substring(i, j) + "':" + ONE);
            i = j + 1;
            j = arr[in].indexOf(DOT, i);
            count++;
            // 按不相同部分.以及相同部分.的数目插入TAB,即为总层级
            for (int co = count + t; co > 0; co--) {
                builder.insert(builder.length() - index, TAB);
            }
        }
        builder.insert(builder.length() - index, "'" + arr[in].substring(i));
        // 尾插对应层级的LINE+TAB+"}"
        while (count-- > 0) {
            builder.insert(builder.length() - index, LINE);
            for (int co = count + t; co > 0; co--) {
                builder.insert(builder.length() - index, TAB);
            }
            builder.insert(builder.length() - index, "}");
        }
    }

    /**
     * 返回两个串最大相同部分的.的位置
     *
     * @param i
     * @param j
     * @return
     */
    private int compare(int i, int j) {
        int c = arr[j].lastIndexOf(DOT), b = 0;
        while (c >= arr[i].length() || c > -1 && arr[i].charAt(c) != '.') { // 寻找最大等长串
            c = arr[j].lastIndexOf(DOT, c - 1);
        }
        if (c > -1) {
            b = c;
        } else {
            return c;
        }
        while (b > -1 && c > -1) {
            if (arr[i].substring(0, b).equals(arr[j].substring(0, c))) {
                break;
            }
            b = arr[i].lastIndexOf(DOT, b - 1);
            c = arr[j].lastIndexOf(DOT, c - 1);
        }
        return c;
    }

    /**
     * 计算相同部分有多少的.
     *
     * @param i
     * @param c
     * @return
     */
    private int countDot(int i, int c) {
        int b = c, count = 0;
        while (b > -1) {
            count++;
            b = arr[i].lastIndexOf(DOT, b - 1);
        }
        return count;
    }

}
