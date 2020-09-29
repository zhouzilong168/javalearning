package algorithm.leetcode.swordoffer;

import java.util.Arrays;

/**
 * @ClassName ReverseWord
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/23 20:53
 * @Version 1.0
 **/
public class ReverseWord {

    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        int i = 0, len = s.length();
        while (i < len) {
            while (i < len && s.charAt(i) == ' ') {
                i++;
            }
            int t = i;
            while (i < len && s.charAt(i) != ' ') {
                i++;
            }
            res.insert(0, s.substring(t, i) + " ");
        }
        return res.toString().trim();
    }

    public String reverseWords1(String s) {
        String res = "";
        String[] arr = s.trim().split("\\s+");
        for (int i = arr.length - 1; i > 0; i--) {
            res += arr[i] + " ";
        }
        return res + arr[0];
    }

    public String reverseWords0(String s) {
        StringBuilder res = new StringBuilder();
        String[] arr = s.trim().split("\\s+");
        for (int i = arr.length - 1; i > 0; i--) {
            res.append(arr[i]).append(" ");
        }
        return res.append(arr[0]).toString();
    }

    public static void main(String[] args) {
        String a = "", b = " ", c = " a   a";
        System.out.println(a.trim() + b.trim() + c.trim());
        System.out.println(Arrays.toString(c.trim().split("\\s+")));
        StringBuilder res = new StringBuilder();
        res.append("abc");
        res.insert(0, "#");
        System.out.println(res.toString());
    }
}
