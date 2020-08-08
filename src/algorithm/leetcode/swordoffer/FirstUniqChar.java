package algorithm.leetcode.swordoffer;

import org.junit.Test;

/**
 * @ClassName Main
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/20 17:57
 * @Version 1.0
 **/
public class FirstUniqChar {

    /**
     * 逆向思维，既然知道了只有小写26个字母，就直接遍历这26个字母
     * 然后通过indexOf和lastIndexOf判断是否是同一个，
     *      是则为单字符，需要判断当前index和res的大小，更新为小的
     * @param s
     * @return
     */
    public char firstUniqChar_plus(String s) {
        int res = -1;
        for(char c = 'a' ; c <= 'z' ;c++){
            int index = s.indexOf(c);
            if(index != -1 && s.lastIndexOf(c) == index){
                res = (res == -1 || res > index) ? index : res;
            }
        }

        return res == -1 ? ' ' : s.charAt(res);
    }

    private static final char A = 'a';
    public char firstUniqChar(String s) {
        int[] arr = new int[26];
        for(int i = 0;i<s.length();i++){
            arr[s.charAt(i)-A]++;
        }
        for(int i = 0;i<s.length();i++){
            if(arr[s.charAt(i)-A]==1){
                return s.charAt(i);
            }
        }
        return ' ';
    }

    @Test
    public void test() {
        System.out.println(firstUniqChar_plus("abaccdeff"));
        System.out.println(firstUniqChar_plus(""));
    }
}
