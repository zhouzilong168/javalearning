package algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @ClassName MaxQueue_Interview_59
 * @Description TODO leetCode 面试题59-II 队列最大值
 * @Author thinkpad
 * @Date 2020/3/18 11:46
 * @Version 1.0
 **/
public class MaxQueue_Interview_59 {

    private Queue<Integer> queue;
    private Deque<Integer> deque;

    public MaxQueue_Interview_59() {
        queue = new ArrayDeque<>();
        deque = new ArrayDeque<>();
    }

    public int max_value() {
        Integer res = deque.peek();
        return res == null ? -1 : res;
    }

    public void push_back(int value) {
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.addLast(value);
        queue.add(value);
    }

    public int pop_front() {
        int tmp;
        if (queue.isEmpty()) {
            tmp = -1;
        } else {
            tmp = queue.poll();
            if (tmp == deque.peekFirst()) {
                deque.pollFirst();
            }
        }
        return tmp;
    }

    @Test
    public void test01() {
        MaxQueue_Interview_59 maxQueueInterview59 = new MaxQueue_Interview_59();
        maxQueueInterview59.push_back(9);
        System.out.println(maxQueueInterview59.max_value());
        maxQueueInterview59.push_back(5);
        maxQueueInterview59.push_back(5);
        maxQueueInterview59.pop_front();
        System.out.println(maxQueueInterview59.max_value());
        maxQueueInterview59.pop_front();
        System.out.println(maxQueueInterview59.max_value());
        maxQueueInterview59.push_back(1);
        System.out.println(maxQueueInterview59.max_value());
        maxQueueInterview59.push_back(7);
        System.out.println(maxQueueInterview59.max_value());

    }
}
