package algorithm.leetcode.swordoffer;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName LongestLengthOfDiffSubString
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/24 15:59
 * @Version 1.0
 **/
public class LongestLengthOfDiffSubString {



    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int res = 1, l = 0, r = 1;
        while (r < s.length() && res < s.length() - l) {
            char tmp = s.charAt(r);
            r++;
            if (!set.contains(tmp)) {
                set.add(tmp);
                continue;
            }
            if (res < set.size()) {
                res = set.size();
            }
            while (l < r) {
                if (s.charAt(l) == tmp) {
                    l++;
                    break;
                }
                set.remove(s.charAt(l));
                l++;
            }
        }
        return Math.max(res, set.size());
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public int lengthOfLongestSubstring_error(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        int res = 0;
        for (int i = 0; i < s.length() && res < s.length() - i + set.size(); i++) {
            char tmp = s.charAt(i);
            if (!set.contains(tmp)) {
                set.add(tmp);
                continue;
            }
            if (res < set.size()) {
                res = set.size();
            }
            set.parallelStream().forEach(a -> {
                set.removeIf(b -> b != tmp);
            });
        }
        return Math.max(res, set.size());
    }
}


