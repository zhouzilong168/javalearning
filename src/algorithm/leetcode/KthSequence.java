package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName KthSequence
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/22 16:55
 * @Version 1.0
 **/
public class KthSequence {
    int[] nums;
    StringBuilder sb;

    public String getPermutation(int n, int k) {
        sb = new StringBuilder();
        nums = new int[n + 1];
        int m = 1;
        nums[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            nums[i] = nums[i + 1] * m;
            m++;
        }
        System.out.println(Arrays.toString(nums));
        Set<Integer> set = new HashSet<>();
        domain(1, k, n, 0, set);
        return sb.toString();
    }

    private void domain(int i, int k, int n, int sum, Set<Integer> set) {
        if (i == n) {
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (set.contains(j)) {
                continue;
            }
            sum += nums[i];
            if (sum >= k) {
                sum -= nums[i];
                sb.append(j);
                set.add(j);
                domain(i + 1, k, n, sum, set);
                break;
            }
        }
    }

    @Test
    public void test() {
        System.out.println(getPermutation(3, 1));
    }
}
