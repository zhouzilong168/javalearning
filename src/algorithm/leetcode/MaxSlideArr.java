package algorithm.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName MaxSlideArr
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/27 16:11
 * @Version 1.0
 **/
public class MaxSlideArr {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Queue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
        int i = 0;
        while (i < nums.length && i < k) {
            pq.add(nums[i++]);
        }
        res[i - k] = pq.peek();
        while (i < nums.length) {
            pq.remove(nums[i - k]);
            pq.add(nums[i]);
            res[++i - k] = pq.peek();
        }
        return res;
    }

    @Test
    public void test() {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(2,2);
        map.put(1,1);
        map.put(3,5);
        map.put(4,2);
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());
        Set<Integer> set = new HashSet<>();
        System.out.println(set.add(1));
        System.out.println(set.add(1));
    }
}
