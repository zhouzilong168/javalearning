package written_interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName OrderArray_TwoAdd
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/12 10:53
 * @Version 1.0
 **/
public class OrderArray_TwoAdd {
    int[] arr = {1, 2, 4, 7, 11, 15};
    int res = -1;

    /**
     * 查找优化为二分
     *
     * @param n
     */
    private boolean bianarySearch(int n) {
        int l = 0, r = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            int tmp = n - arr[i], left = l, right = r;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == tmp) {
                    res = tmp;
                    return true;
                } else if (arr[mid] < tmp) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            left = i + 1;
            right = r;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == tmp) {
                    res = tmp;
                    return true;
                } else if (arr[mid] < tmp) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * hash查找 空间换时间
     *
     * @param n
     */
    private boolean hashSearch(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer i :
                arr) {
            hashSet.add(i);
        }
        for (int value : arr) {
            int tmp = n - value;
            if (hashSet.contains(tmp)) {
                res = tmp;
                return true;
            }
        }
        return false;
    }

    /**
     * 两个指针往中间遍历，当前和大，则右指针左移，小左指针右移，等则返回
     *
     * @param n
     * @return
     */
    private boolean TwoPointSearch(int n) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left] + arr[right];
            if (tmp == n) {
                res = arr[left];
                return true;
            } else if (arr[left] + arr[right] < n) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int n = 9;
        if (TwoPointSearch(n)) {
            System.out.println(res + "+" + (n - res) + "=" + n);
        }
        n = 12;
        if (TwoPointSearch(n)) {
            System.out.println(res + "+" + (n - res) + "=" + n);
        }
        n = 15;
        if (TwoPointSearch(n)) {
            System.out.println(res + "+" + (n - res) + "=" + n);
        }
    }
}
