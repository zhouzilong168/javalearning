package algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName Queue_to_Stack_225
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/18 17:19
 * @Version 1.0
 **/
public class Queue_to_Stack_225 {

    Queue<Integer> q1, q2, q, p;

    /**
     * Initialize your data structure here.
     */

    public Queue_to_Stack_225() {
        q1 = new ArrayDeque();
        q2 = new ArrayDeque();
    }

    /**
     * Push element x onto stack.
     */

    public void push(int x) {
        q = q1.isEmpty() ? q2 : q1;
        q.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */

    public int pop() {
        if (q1.isEmpty()) {
            q = q2;
            p = q1;
        } else {
            q = q1;
            p = q2;
        }
        while (q.size() > 1) {
            p.offer(q.poll());
        }
        return q.poll();
    }

    /**
     * Get the top element.
     */

    public int top() {
        if (q1.isEmpty()) {
            q = q2;
            p = q1;
        } else {
            q = q1;
            p = q2;
        }
        while (q.size() > 1) {
            p.offer(q.poll());
        }
        int tmp = q.poll();
        p.offer(tmp);
        return tmp;
    }

    /**
     * Returns whether the stack is empty.
     */

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    @Test
    public void test01() {
        Queue_to_Stack_225 stack = new Queue_to_Stack_225();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}


/*public class Queue_to_Stack {

    private Queue<Integer> q1,q2;
    *//**
 * Initialize your data structure here.  Push element x onto stack.  Removes the element on top of the stack and returns that element.  Get the top element.  Returns whether the stack is empty.
 * Push element x onto stack.  Removes the element on top of the stack and returns that element.  Get the top element.  Returns whether the stack is empty.
 * Push element x onto stack.  Removes the element on top of the stack and returns that element.  Get the top element.  Returns whether the stack is empty.
 *//*
    public Queue_to_Stack() {
        q1=new ArrayDeque();
        q2=new ArrayDeque();
    }

    *//** Push element x onto stack. *//*
    public void push(int x) {
        if(q1.isEmpty()){
            q2.add(x);
        }else{
            q1.add(x);
        }
    }

    *//** Removes the element on top of the stack and returns that element. *//*
    public int pop() {
        int tmp;
        if(q1.isEmpty()){
            while(q2.size()>1){
                q1.offer(q2.poll());
            }
            tmp=q2.poll();
        }else{
            while(q1.size()>1){
                q2.offer(q1.poll());
            }
            tmp=q1.poll();
        }
        return tmp;
    }

    *//** Get the top element. *//*
    public int top() {
        int tmp;
        if(q1.isEmpty()){
            while(q2.size()>1){
                q1.offer(q2.poll());
            }
            tmp=q2.poll();
            q1.add(tmp);
        }else{
            while(q1.size()>1){
                q2.offer(q1.poll());
            }
            tmp=q1.poll();
            q2.add(tmp);
        }
        return tmp;
    }

    *//** Returns whether the stack is empty. *//*
    public boolean empty() {
        return q1.isEmpty()&&q2.isEmpty();
    }

    public void prints(Queue q){
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addAll(q);
        while(!arrayDeque.isEmpty()){
            System.out.print(arrayDeque.removeFirst()+"--");
        }
        System.out.println();
    }

    @Test
    public void test01(){
        Queue_to_Stack stack = new Queue_to_Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}*/
