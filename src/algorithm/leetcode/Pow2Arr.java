package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName Pow2Arr
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/16 10:37
 * @Version 1.0
 **/
public class Pow2Arr {
    static class A {
        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);
//            int n = in.nextInt(), m = in.nextInt();
            int n = 7, m = 3;
            int[][] res = new int[m + 1][n + 1];
            for (int[] arr :
                    res) {
                Arrays.fill(arr, -1);
            }
            System.out.println(solve(res, m, n));
        }

        static int solve(int[][] res, int m, int n) {
            if (m >= res.length || n >= res[m].length) {
                return 0;
            }
            if (n < m) {
                res[m][n] = 0;
            } else if (m == 1 || m == n || m + 1 == n) {
                res[m][n] = 1;
            } else if (m == 2) {
                res[m][n] = n / 2;
            } else {
                for (int i = 1; i <= m; i++) {
                    res[m][n] += res[i][n - m] == -1 ? solve(res, i, n - m) : res[i][n - m];
                }
            }
            return res[m][n];
        }
    }

    static class B extends A {
    }

    class Solution {
        public int[] sortedSquares(int[] A) {
            int start = 0;
            int end = A.length - 1;
            int i = end;
            int[] B = new int[A.length];
            while (i >= 0) {
                B[i--] = A[start] * A[start] >= A[end] * A[end] ? A[start] * A[start++] : A[end] * A[end--];
            }
            return B;
        }
    }

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    public int[] sortedSquares1(int[] A) {
        if (A == null || A.length < 1) {
            return A;
        }
        int index = 0;
        for (; index < A.length; index++) {
            if (A[index] >= 0) {
                break;
            }
            A[index] = A[index] * A[index];
        }
        for (int i = index; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        if (index == 0) {
            return A;
        }
        int[] res = new int[A.length];
        System.arraycopy(A, index, res, 0, A.length - index);
        return insertSortBinary(res, A.length - index - 1, A, index - 1);
    }

    int[] insertSortBinary(int[] res, int k, int[] arr, int x) {
        int l = 0, r, m;
        for (int i = x; i >= 0; i--) {
            r = k;
            l = (l = Arrays.binarySearch(res, l, r + 1, arr[i])) < 0 ? -l - 1 : l;
            if (k + 1 - l >= 0) System.arraycopy(res, l, res, l + 1, k + 1 - l);
            res[l] = arr[i];
            k++;
        }
        return res;
    }

    int[] insertSortBinary1(int[] res, int k, int[] arr, int x) {
        int l = 0, r, m;
        for (int i = x; i >= 0; i--) {
            r = k;
            while (l <= r) { // 二分插入查找中 left为插入位置 当前位置偏小l+1，偏大r-1
                m = l + (r - l) / 2;
                if (res[m] < arr[i]) {
                    l = m + 1;
                } else if (res[m] > arr[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                    break;
                }
            }
            System.out.println("k: " + k + "  l:" + l + "  r:" + r);
            if (k + 1 - l >= 0) System.arraycopy(res, l, res, l + 1, k + 1 - l);
            res[l] = arr[i];
            k++;
        }
        return res;
    }

    @Test
    public void test() {
//        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
//        System.out.println();
//        System.out.println(Arrays.toString(sortedSquares(new int[]{-7, -3, 2, 3, 11})));
//        System.out.println();
//        System.out.println(Arrays.toString(sortedSquares(new int[]{0, 1, 2, 3, 11})));
//        System.out.println();
//        System.out.println(Arrays.toString(sortedSquares(new int[]{-0, -1, -2, -3, -11})));
        System.out.println(Arrays.binarySearch(new int[]{0, 9, 100}, -1)); // -1abs -1
        System.out.println(Arrays.binarySearch(new int[]{0, 9, 100}, 1));// -2 abs -1
        System.out.println(Arrays.binarySearch(new int[]{0, 9, 100}, 9)); // 1
        System.out.println(Arrays.binarySearch(new int[]{0, 2, 5, 9, 10, 11, 12, 100}, 2, 6, 3));
        System.out.println(Arrays.binarySearch(new int[]{0, 2, 5, 9, 10, 11, 12, 100}, 2, 6, 6));
        System.out.println(Arrays.binarySearch(new int[]{0, 2, 5, 9, 10, 11, 12, 100}, 2, 6, 9));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
    }

}
