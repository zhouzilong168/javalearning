package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * @ClassName Stack_to_Queue_232
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/18 18:53
 * @Version 1.0
 **/
public class Stack_to_Queue_232 {

    Deque<Integer> s1, s2, p, q;

    /**
     * Initialize your data structure here.
     */
    public Stack_to_Queue_232() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        s1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
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

    /**
     * Get the front element.
     */
    public int peek() {
        int res;
        if (!s2.isEmpty()) {
            res = s2.pop();
        } else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            try {
                res = s1.peek();
            } catch (NullPointerException e) {
                res = -1;
            }
        }
        return res;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    @Test
    public void test01() {
        Stack_to_Queue_232 stack = new Stack_to_Queue_232();
        stack.push(1);
/*        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);*/
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.empty());
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

/*    public void push(int x) {
        p=s1.isEmpty()?s2:s1;
        p.push(x);
    }

    *//**
     * Removes the element from in front of queue and returns that element.
     *//*
    public int pop() {
        if(s1.isEmpty()){
            q=s2;
            p=s1;
        }else{
            q=s1;
            p=s2;
        }
        while(q.size()>1){
            p.push(q.pop());
        }
        int res = q.pop();
        while(p.size()>0){
            q.push(p.pop());
        }
        return res;
    }

    *//**
     * Get the front element.
     *//*
    public int peek() {
        if(s1.isEmpty()){
            q=s2;
            p=s1;
        }else{
            q=s1;
            p=s2;
        }
        while(q.size()>1){
            p.push(q.pop());
        }
        int res = q.peek();
        while(p.size()>0){
            q.push(p.pop());
        }
        return res;
    }

    *//**
     * Returns whether the queue is empty.
     *//*
    public boolean empty() {
        return s1.isEmpty()&&s2.isEmpty();
    }*/
}