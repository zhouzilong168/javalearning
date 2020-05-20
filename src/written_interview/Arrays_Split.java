package written_interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * TODO 数组划分求和得无重复元素数组个数（携程笔试）
 * n个元素数组划分得到m组元素集，求m组元素集和作为长度为m新数组的元素，求新数组无重复元素的个数
 */
public class Arrays_Split {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static void calc(int[] nums, int n, int m) {
        //System.out.println("n,m: "+n+","+m);
        if (m < 1 || n < m) {
            return;
        }
        if (n == m) {
            System.arraycopy(nums, 0, arrs, 0, n);
            System.out.println(Arrays.toString(arrs));
            if (isSame(arrs)) {
                counts++;
            }
            return;
        } else if (m == 1) {
            arrs[0] = 0;
            for (int i = 0; i < n; i++) {
                arrs[0] += nums[i];
            }
            System.out.println(Arrays.toString(arrs));
            if (isSame(arrs)) {
                counts++;
            }
            return;
        }
        for (int i = n - 1; i >= m - 1; i--) {
            int tmp = 0;
            for (int j = n - 1; j >= i; j--) {
                tmp += nums[j];
            }
            arrs[m - 1] = tmp;
            calc(nums, i, m - 1);
        }

    }

    /******************************结束写代码******************************/
    static int _m = 0;
    static int counts = 0;
    static int[] arrs;

    static boolean isSame(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        if (set.size() == _m) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int _nums_size = 0;
        _nums_size = Integer.parseInt(in.nextLine().trim());
        int[] _nums = new int[_nums_size];
        int _nums_item;
        for (int _nums_i = 0; _nums_i < _nums_size; _nums_i++) {
            _nums_item = Integer.parseInt(in.nextLine().trim());
            _nums[_nums_i] = _nums_item;
        }

        _m = Integer.parseInt(in.nextLine().trim());
        arrs = new int[_m];
        calc(_nums, _nums.length, _m);
        System.out.println(counts);
    }
}
/*
5
4
2
6
7
3
3
*/