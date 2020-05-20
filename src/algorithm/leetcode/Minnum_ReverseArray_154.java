package algorithm.leetcode;

/**
 * @ClassName Minnum_ReverseArray_154
 * @Description TODO 求非递减旋转数组最小值 二分算法&一遍法
 * @Author thinkpad
 * @Date 2020/5/11 11:22
 * @Version 1.0
 **/
public class Minnum_ReverseArray_154 {
    /**
     * 二分法
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        /*
        nums[left] >= nums[right] 保证特殊情况：
            1、输入数组为非递减，即旋转点为头or尾；
            2、二分过程中，left~right之间为非递减
         */
        while (left < right && nums[left] >= nums[right]) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[left]) {// left~mid之间为左边非递减数组，最小值在mid之后
                left = mid + 1;
                if (nums[left] < nums[right]) { // mid正好指向了左数组最后一个元素
                    return nums[left];
                }
            } else if (nums[mid] < nums[right] || nums[mid] < nums[left]) {
                /*
                 nums[mid] < nums[right]：mid~right之间为右边非递减数组，最小值在mid及之前
                 nums[mid] < nums[left]：特殊情况[2,0,1,1,1]，即右子数组mid之后全为相等
                 */
                right = mid;
            } else if (nums[mid] > nums[right]) {// 特殊情况[2,2,2,大,0,1]，左子数组mid之前相等
                left = mid + 1;
            } else {
                left++;
                right--;
            }
        }
        return nums[right];
    }

    /**
     * 简单解法
     *
     * @param nums
     * @return
     */
    public int findMin_Simple(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(nums));
    }
}
/*
        if(nums==null){
            return 0;
        }
        int left = 0,right = nums.length-1;
        while(left<right&&nums[left]>=nums[right]){
            int mid = (left+right)/2;
            if(nums[mid]>nums[left]){
                left=mid+1;
                if (nums[left] < nums[right]) {
                    return nums[left];
                }
            }else if(nums[mid]<nums[right]||nums[mid]<nums[left]){
                right=mid;
            }else if(nums[mid]>nums[right]){
                left=mid+1;
            }else{
                left++;
                right--;
            }
        }
        return nums[left];
*/