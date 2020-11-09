package algorithm.leetcode.swordoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName MinArray
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/11 21:05
 * @Version 1.0
 **/
public class MinArray {
    public String minNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, (a, b) -> (int) ((int) Long.parseLong(a + b) - Long.parseLong(b + a)));
        for (String s :
                arr) {
            res.append(s);
        }
        return res.toString();
    }

    /**
     * 摩尔投票法
     * 若vote票数为0，则选当前数为众数
     * 若当前数字等于目前众数，则vote++
     *      否则，vote--
     *      由于 众数大于一半 所以最后抵消后都会是众数别选出
     *
     * 还可以通过排序选出，由于超过一半，则排序后中数就是众数
     * Hash计数也可以解决
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int res = 0, vote = 0;
        for (int i = 0; i < nums.length; i++) {
            if (vote == 0) {
                res = nums[i];
            }
            if (nums[i] != res) {
                vote -= 1;
            } else {
                vote += 1;
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(minNumber(new int[]{2, 10, 122, 34, 6, 8, 9, 645, 46, 8, 9}));
    }
    /*
    case 1: cur=0
     2  3   0  4
     千位和百位可以选00 01 02....22  十位可以取到1( 形如[00|01..|22]1[0-9] 都是<2304 ) 个位可以选0-9  共有 23 * 10 中排列
     当千位和百位取23,如果十位取1 那就是形如 231[0-9] > 2304,所以当千位和百位取23，十位只能能取0，个位取0-4即 2300 2301 2302 2303 2304
     但是2301不应该算进来，这个1是 单独  出现在个位的（而11，121,111这种可以被算多次）
     即 23*10
    case 2: cur=1
       2  3  1  4
       千位和百位可以选00 01 02....22  十位可以取到1 个位可以选0-9  共有 23 * 10 中排列
       当千位和百位取23,十位取1，个位可以去0-4 即 2310-2314共5个
       即 23 *10 + 4 +1
    case 3: cur>1 即2-9
       2  3  2  4
       千位和百位可以选00 01 02....22  十位可以取到1(形如 [00|01...|22]1[0-9] 都是<2324) 个位可以选0-9  共有 23 * 10 中排列
       当千位和百位取23,十位取1，个位可以去0-9 即 2310-2319共10个 （其中2311，被计算了两次，分别是从个位和十位分析得到的1次）
       即 23 *10 + 10
     */
}
