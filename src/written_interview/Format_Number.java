package written_interview;

import java.util.Scanner;

/**
 * @ClassName Format_Number
 * @Description TODO 2020.04.23 金额数字格式化
 * @Author thinkpad
 * @Date 2020/4/24 9:51
 * @Version 1.0
 **/
public class Format_Number {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder string = null;
        while (in.hasNextLine()) {
            string = new StringBuilder(in.nextLine());
            dealString(string);
        }
    }

    private static void dealString(StringBuilder string) {
        String res = "";
        int dot = string.indexOf(".");
        if (dot > -1) { // 小数
            int sub = string.length() - dot;
            if (sub > 3) {
                string.delete(dot + 3, string.length());
            } else {
                if (sub == 2) {
                    string.append("0");
                } else if (sub == 1) {
                    string.append("00");
                }
            }
            for (int i = string.length() - 6; i > 0; i -= 3) {
                string.insert(i, ",");
            }
        } else { // 整数
            for (int i = string.length() - 3; i > 0; i -= 3) {
                string.insert(i, ",");
            }
            string.append(".00");
        }
        if (string.indexOf("-") > -1) {
            res = "($" + string.substring(1) + ")";
        } else {
            res = "$" + string;
        }
        System.out.println(res);
    }
}

