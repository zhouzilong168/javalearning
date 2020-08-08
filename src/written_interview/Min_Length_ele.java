package written_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Min_Length_ele {
    public void test() {
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
            int pre = i - 1 < 0 ? 0 : i - 1, next = i + 1 > size - 1 ? size - 1 : i + 1;// 前后元素，优化时间
            int minl = n, minr = n, left = i, right = i;
            while (--left > pre) { // 往左最小
                if (strs[left].equals(str2)) {
                    minl = i - left;
                    break;
                }
            }
            while (++right < next) { // 往右最小
                if (strs[right].equals(str2)) {
                    minr = right - i;
                    break;
                }
            }
            nowMin = minl < minr ? minl : minr;
            if (nowMin < minLen) {
                minLen = nowMin;
            }
        }
        for (int cur : nums) {
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
        }
        minLen = nowMin == n ? -1 : nowMin;
        System.out.println(minLen);
    }
}

