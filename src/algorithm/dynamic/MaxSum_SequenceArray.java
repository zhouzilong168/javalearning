package algorithm.dynamic;

import org.junit.Test;

/**
 * @ClassName MaxSum_SequenceArray
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/12 12:26
 * @Version 1.0
 **/
public class MaxSum_SequenceArray {

    /**
     * 一遍遍历法
     *
     * @param arr
     * @return
     */
    private int one_Traverse(int[] arr) {
        int now = -1, max = Integer.MIN_VALUE;
        for (int value : arr) {
            if (now < 0) { // 遍历到当前元素时，先判断之前的累加是否已经小于0了，小于直接重置now为当前即可
                now = value;
            } else {
                now += value;// 之前累加大于0，说明继续累加可能达到最大，直接累加
            }
            if (max < now) max = now; // 更新最大值
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(one_Traverse(new int[]{31, -41, 59, 26, -53, 58, 97, -93, -23, 84}));
    }
}







