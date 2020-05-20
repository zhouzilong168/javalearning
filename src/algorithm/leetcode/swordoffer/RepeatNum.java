package algorithm.leetcode.swordoffer;

/**
 * @ClassName RepeatNum
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/17 17:24
 * @Version 1.0
 **/
public class RepeatNum {
    /**
     * 散列
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int[] hash = new int[nums.length];
        for (int i : nums) {
            hash[i]++;
        }
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 不开辟散列数组，直接当前数组上交换
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber_plus(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) { // 下标与元素不一致
                if (nums[i] == nums[nums[i]]) { // 如果当前元素与将要放到位置上的元素相等，即重复
                    return nums[i];
                } else {
                    int tmp = nums[i]; // 通过交换将当前元素放到对应下标位置
                    nums[i] = nums[tmp];
                    nums[tmp] = tmp;
                }
            }
        }
        return -1;
    }
}
