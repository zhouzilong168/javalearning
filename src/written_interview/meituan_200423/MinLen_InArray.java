package written_interview.meituan_200423;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName MinLen_InArray
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/1 10:57
 * @Version 1.0
 **/
public class MinLen_InArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String str1 = in.next(), str2 = in.next();
        String[] strs = new String[n];
        List<Integer> nums = new ArrayList<>();
        int minLen = n;
        for (int i = 0; i < n; i++) {
            strs[i] = in.next();
            if (strs[i].equals(str1)) {
                nums.add(i);
            }
        }
        System.out.println(nums);
        int nowMin = n, size = nums.size();
        for (int i = 0; i < size; i++) {
            int pre = i - 1 < 0 ? nums.get(0) : nums.get(i - 1),
                    next = i + 1 > size - 1 ? nums.get(size - 1) : nums.get(i + 1);// 前后元素，优化时间
            int cur = nums.get(i);
            int minl = n, minr = n, left = nums.get(i), right = left;
            while (--left > pre) { // 往左最小
                if (strs[left].equals(str2)) {
                    minl = cur - left;
                    break;
                }
            }
            while (++right < next) { // 往右最小
                if (strs[right].equals(str2)) {
                    minr = right - cur;
                    break;
                }
            }
            nowMin = minl < minr ? minl : minr;
            minLen = nowMin < minLen ? nowMin : minLen;
        }
/*        for (int cur : nums) {
            int minl = n, minr = n, left = cur, right = cur;
            while (--left >= 0) { // 往左最小
                if (strs[left].equals(str2)) {
                    minl = cur - left;
                    break;
                }
            }
            while (++right < n) { // 往右最小
                if (strs[right].equals(str2)) {
                    minr = right - cur;
                    break;
                }
            }
            nowMin = minl < minr ? minl : minr;
            if (nowMin < minLen) {
                minLen = nowMin;
            }
        }*/
        minLen = minLen == n ? -1 : minLen;
        System.out.println(minLen);
    }
}

