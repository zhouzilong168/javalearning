package algorithm.leetcode;

import java.util.*;

/**
 * @ClassName CommonChar
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/14 10:34
 * @Version 1.0
 **/
public class CommonChar {
    public List<String> commonChars(String[] A) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < A[0].length(); i++) {
            Character c = A[0].charAt(i);
            Integer in = map.get(c);
            in = in == null ? 1 : in + 1;
            map.put(c, in);
        }
        for (int i = 1; i < A.length; i++) {
            Map<Character, Integer> tmp = new HashMap<>();
            for (int j = 0; j < A[i].length(); j++) {
                Character c = A[i].charAt(j);
                if (map.get(c) != null) {
                    Integer in = tmp.get(c);
                    in = in == null ? 1 : in + 1;
                    tmp.put(c, in);
                }
            }
            for (Character c :
                    new HashSet<>(map.keySet())) {
                if (!tmp.containsKey(c)) {
                    map.remove(c);
                } else {
                    map.put(c, Math.min(map.get(c), tmp.get(c)));
                }
            }
            tmp = null;
        }
        List<String> res = new ArrayList<>();
        for (Character c :
                map.keySet()) {
            for (int i = 0; i < map.get(c); i++) {
                res.add(String.valueOf(c));
            }
        }
        return res;
    }

    class Solution {
        public List<String> commonChars(String[] A) {

            // 入参检查
            if(A == null || A.length == 0 || A.length == 1) {
                return null;
            }
            // 记录每个字符出现的次数（字符的ASCII码-97的值做数组下标）
            int[] hash = new int[26];
            // 是否第一次维护hash数组
            boolean firstFlag = true;
            // 遍历字符串
            for (String word : A) {
                // 拆分成字符数组
                char[] wordChars = word.toCharArray();
                // 如果是第一次维护hash数组
                if (firstFlag) {
                    // 直接记录每个字符出现的个数
                    for (char wordChar : wordChars) {
                        hash[wordChar - 97]++;
                    }
                    // 标志置为否
                    firstFlag = false;
                    // 如果不是第一次维护，即hash数组中有值时
                }else {
                    // 新建临时数组tmpHash来记录当前字符数组每个字符出现的次数
                    int[] tmpHash = new int[26];
                    for (char wordChar : wordChars) {
                        tmpHash[wordChar - 97]++;
                    }

                    // 维护hash数组
                    for(int i = 0; i < hash.length; ++i) {
                        // 比较hash数组和tmpHash数组
                        // hash记录每次每个字符出现的最小次数
                        if(hash[i] > tmpHash[i]) {
                            hash[i] = tmpHash[i];
                        }
                    }
                }
            }
            // 组装返回结果
            List<String> res = new ArrayList<>();
            for(int i = 0; i < hash.length; ++i) {
                if(hash[i] != 0) {
                    String tmp = String.valueOf((char)(i + 97));
                    for(int j = 0; j < hash[i]; ++j) {
                        res.add(tmp);
                    }
                }
            }

            return res;
        }
    }


    class Solution1 {
        public List<String> commonChars(String[] A) {
            int[] minfreq = new int[26];
            Arrays.fill(minfreq, Integer.MAX_VALUE);
            for (String word: A) {
                int[] freq = new int[26];
                int length = word.length();
                for (int i = 0; i < length; ++i) {
                    char ch = word.charAt(i);
                    ++freq[ch - 'a'];
                }
                for (int i = 0; i < 26; ++i) {
                    minfreq[i] = Math.min(minfreq[i], freq[i]);
                }
            }

            List<String> ans = new ArrayList<String>();
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < minfreq[i]; ++j) {
                    ans.add(String.valueOf((char) (i + 'a')));
                }
            }
            return ans;
        }
    }
}
