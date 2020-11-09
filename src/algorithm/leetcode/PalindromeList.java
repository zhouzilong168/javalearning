package algorithm.leetcode;

import org.junit.Test;

/**
 * @ClassName PalindromeList
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/23 10:28
 * @Version 1.0
 **/
public class PalindromeList {
    class Solution {
        private ListNode frontPointer;
        // 回文递归  head往后 cur递归往前 遇到null回溯递归
        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) {
                    return false;
                }
                if (currentNode.val != frontPointer.val) {
                    return false;
                }
                frontPointer = frontPointer.next;
            }
            return true;
        }

        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }
    }
    ListNode cur, mid = null;
    boolean flag = false, sign = false;
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        cur = head;
        ListNode fast = head;
        while (fast != null) {
            cur = cur.next;
            fast = fast.next; // 提前
            if (fast == null) {
                mid = cur;
                flag = true;
                break;
            } else {
                fast = fast.next;
            }
        }
        if (mid == null) {
            mid = cur;
        }
        return domain(head);
    }

    public boolean domain(ListNode head) {
        if (flag) {
            if (head.next.next == mid) {
                sign = true;
            }
        } else if (head.next == mid) {
            sign = true;
        }
        if (!sign) {
            boolean tmp = domain(head.next) && head.val == cur.val;
            cur = cur.next;
            return tmp;
        } else {
            if (head.val == cur.val) {
                cur = cur.next;
                return true;
            }
            return false;
        }
    }

    @Test
    public void test() {
        System.out.println(isPalindrome(new ListNode(1, new ListNode(4, new ListNode(-1, new ListNode(-1, new ListNode(4, new ListNode(1, null))))))));
    }
}
