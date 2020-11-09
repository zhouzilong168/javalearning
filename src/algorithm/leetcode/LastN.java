package algorithm.leetcode;

import org.junit.Test;

/**
 * @ClassName LastN
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/18 15:52
 * @Version 1.0
 **/
public class LastN {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode l = head, r = head;
        for (int i = 0; i < n; i++) {
            r = r.next;
        }
        if (r == null) {
            return head.next;
        }
        while (r.next != null) {
            r = r.next;
            l = l.next;
        }
        l.next = l.next.next;
        return head;
    }

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int max = 1 << 31, tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            tmp = tmp <= 0 ? nums[i] : tmp + nums[i];
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int max = 1 << 31, tmp = 0;

        return max;
    }

    int maxSub(int[] nums, int l, int r) {
        if (l >= r) {
            return nums[l];
        }
        int max = 1 << 31, tmp = 0;
        for (int i = l; i < r; i++) {
            tmp = tmp <= 0 ? nums[i] : tmp + nums[i];
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }

    class Solution {
        public class Status {
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum, int rSum, int mSum, int iSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.mSum = mSum;
                this.iSum = iSum;
            }
        }

        public int maxSubArray(int[] nums) {
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        public Status getInfo(int[] a, int l, int r) {
            if (l == r) {
                return new Status(a[l], a[l], a[l], a[l]);
            }
            int m = (l + r) >> 1;
            Status lSub = getInfo(a, l, m);
            Status rSub = getInfo(a, m + 1, r);
            return pushUp(lSub, rSub);
        }

        public Status pushUp(Status l, Status r) {
            int iSum = l.iSum + r.iSum;
            int lSum = Math.max(l.lSum, l.iSum + r.lSum);
            int rSum = Math.max(r.rSum, r.iSum + l.rSum);
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }
    }

    @Test
    public void test() {

    }

}
