package algorithm.leetcode;

import java.util.*;

/**
 * @ClassName PartitionLabels
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/22 10:08
 * @Version 1.0
 **/
public class PartitionLabels {

    class Solution {
        // 存边界
        public List<Integer> partitionLabels(String S) {
            if (S == null || S.length() == 0) {
                return null;
            }
            int len = S.length();

            // 做映射表，记录每个字母最后出现的位置
            int[] ma = new int[26];
            for (int i = 0; i < len; ++i) {
                ma[S.charAt(i) - 'a'] = i;
            }

            List<Integer> ans = new ArrayList<>();
            int left = 0;
            while (left < len) {
                char curLeft = S.charAt(left);
                // 最小右边界
                int right = ma[curLeft - 'a'];
                for (int i = left + 1; i < right; ++i) {
                    // 枚举当前分段中的字符，更新右边界
                    if (ma[S.charAt(i) - 'a'] > right) {
                        right = ma[S.charAt(i) - 'a'];
                    }
                }
                // 至此，一个新的片段生成了
                ans.add(right - left + 1);
                // 分析下一段
                left = right + 1;
            }
            return ans;
        }
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        Integer tmp = 0;
        char[] arr = S.toCharArray();
        for (char c :
                arr) {
            map.put(c, (tmp = map.get(c)) == null ? 1 : tmp + 1);
        }
        Set<Character> set = new HashSet<>();
        int index = 0, size = map.size(), before = 0;
        for (; index < arr.length; index++) {
            set.add(arr[index]);
            if ((tmp = map.get(arr[index])) == 1) {
                map.remove(arr[index]);
            } else {
                map.put(arr[index], tmp - 1);
            }
            if (set.size() != 0 && size - map.size() == set.size()) {
                res.add(index - before + 1);
                before = index + 1;
                size = map.size();
                set.clear();
                continue;
            }
        }
        return res;
    }
}
