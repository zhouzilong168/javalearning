package algorithm.leetcode;

import java.util.LinkedList;

/**
 * @ClassName ReOrderList
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/20 9:20
 * @Version 1.0
 **/
public class ReOrderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        LinkedList<ListNode> deque = new LinkedList<>();
        ListNode tmp = head;
        while (tmp != null) {
            deque.offer(tmp);
            tmp = tmp.next;
        }
        tmp = deque.pollLast();
        deque.pollFirst().next = tmp;
        ListNode cur = null;
        while (true) {
            cur = deque.pollFirst();
            tmp.next = cur;
            if (deque.isEmpty()) {
                cur.next = null;
                break;
            }
            tmp = deque.pollLast();
            cur.next = tmp;
            if (deque.isEmpty()) {
                tmp.next = null;
                break;
            }
        }
    }
}
