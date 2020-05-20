package algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Permutation
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/14 12:14
 * @Version 1.0
 **/
public class Permutation {

    /**
     * 处理有重复元素的全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        for (int num : nums) {
            cur.add(num);
        }
        backtrack(cur, 0, result);
        return result;
    }

    /**
     * 交换回溯，效率高
     *
     * @param cur
     * @param i
     * @param result
     */
    private void backtrack(List<Integer> cur, int i, List<List<Integer>> result) {
        int len = cur.size();
        if (len == i) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < len; j++) {
            if (i != j && cur.get(i).equals(cur.get(j))) {
                continue;
            }
            boolean flag = false;
            for (int t = j - 1; t > i; t--) {
                if (cur.get(j).equals(cur.get(t))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            Collections.swap(cur, i, j);
            backtrack(cur, i + 1, result);
            Collections.swap(cur, i, j);
        }
    }

    @Test
    public void test2() {
        List<List<Integer>> res = permuteUnique(new int[]{1, 1, 2});
        System.out.println(res);
        List<List<Integer>> res1 = permuteUnique(new int[]{1, 1, 1, 3});
        System.out.println(res1);
        List<List<Integer>> res2 = permuteUnique(new int[]{1, 1, 2, 2});
        System.out.println(res2);
    }

    boolean[] flags;
    List<List<Integer>> res = new LinkedList<>();

    /**
     * 无重复元素全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        flags = new boolean[nums.length];
        domain(nums, new LinkedList<>(), 0);
        //backtrack(new ArrayList<>(Arrays.asList(nums)),0,new ArrayList<List<Integer>>());
        return res;
    }

    /**
     * 直接枚举，效率低
     *
     * @param nums
     * @param l
     * @param n
     */
    private void domain(int[] nums, LinkedList<Integer> l, int n) {
        if (n == nums.length) {
            res.add((List) l.clone());
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!flags[i]) {
                flags[i] = true;
                l.addLast(nums[i]);
                domain(nums, l, n + 1);
                l.removeLast();
                flags[i] = false;
            }
        }
    }

    @Test
    public void test() {
        List<List<Integer>> res = permute(new int[]{1, 2, 3, 4, 5});
        //System.out.println(res);
    }

    @Test
    public void test1() {
        List<List<Integer>> res = permute(new int[]{1, 2});
/*        System.out.println(res.size());
        res.clear();
        res = permute(new int[]{1, 2, 3});
        System.out.println(res.size());
        res.clear();
        res = permute(new int[]{1, 2, 3, 4});
        System.out.println(res.size());
        res.clear();
        res = permute(new int[]{1, 2, 3, 4, 5});
        System.out.println(res.size());*/
        //System.out.println(res);

        res.clear();
        res = permute(new int[]{1, 1, 1, 4});
        System.out.println(res.size());
        System.out.println(res);
        int size = res.size();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            for (int j = i + 1; j < res.size(); j++) {
                if (res.get(i).equals(res.get(j))) {
                    size--;
                    temp.add(j);
                }
            }
            //System.out.println(temp);
            for (int j = temp.size() - 1; j >= 0; j--) {
                res.remove((int) temp.get(j));
            }
            temp.clear();
        }
        System.out.println(size);
        System.out.println(res.size());
    }

}
