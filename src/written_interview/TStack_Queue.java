package written_interview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * TODO 两个栈实现队列
 */
public class TStack_Queue {


    Deque<Integer> s1, s2;

    private static final String ADD = "add";
    private static final String PEEK = "peek";
    private static final String POLL = "poll";

    public static void main(String[] args) {
        TStack_Queue queue = new TStack_Queue();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String op = in.next();
            switch (op) {
                case ADD: {
                    int num = in.nextInt();
                    queue.add(num);
                }
                break;
                case PEEK: {
                    System.out.println(queue.peek());
                }
                break;
                case POLL: {
                    queue.poll();
                }
                break;
            }
        }
    }

    public TStack_Queue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    public void add(int x) {
        s1.push(x);
    }

    public int poll() {
        int res;
        if (!s2.isEmpty()) {
            res = s2.pop();
        } else {
            while (s1.size() > 1) {
                s2.push(s1.pop());
            }
            try {
                res = s1.pop();
            } catch (NoSuchElementException e) {
                res = -1;
            }
        }
        return res;
    }

    public int peek() {
        int res;
        if (!s2.isEmpty()) {
            res = s2.peek();
        } else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            res = s2.peek() == null ? -1 : s2.peek();
        }
        return res;
    }
}
