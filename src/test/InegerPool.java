package test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class InegerPool {
    @Test
    public void test() {
        Integer i = -128;
        Integer j = -128;
        Integer ii = new Integer(-128);
        Integer jj = new Integer(-128);
        Integer iii = -127;
        Integer jjj = 1;
        Integer q = new Integer(1);
        System.out.println(i == j); // true
        System.out.println(i == ii); // false 比较的是地址值
        System.out.println(ii == jj);// false
        System.out.println(iii == j + jjj); // true 运算就是值的比较
        System.out.println(iii == jj + q);
        System.out.println(jjj.hashCode() + "\t" + q.hashCode() + "\t" + ii.hashCode());
        int a = 100, b = 50;
        int c = a-- - b, d = a-- - b;
        System.out.println(a + "\t" + b + "\t" + c + "\t" + d);
        System.out.println("==========");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println(priorityQueue.size());
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        System.out.println(priorityQueue.size());
        priorityQueue.add(1);
        System.out.println(priorityQueue.size());
        priorityQueue.add(1);
        System.out.println(priorityQueue.size());
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), mcount = 0, count = 0;
        for (int i = 0; i < n; i++) {
            list.add(input.nextInt());
        }
        while (mcount < m) {
            int i = list.removeFirst();
            int j = list.removeFirst();
            if (i > j) {// 头元素大就++。说明之前大的大
                list.add(j);
                list.addFirst(i);
                mcount++;
            } else { // 否则新元素大，置1
                mcount = 1;
                list.add(i);
                list.addFirst(j);
            }
            count++;
        }
        System.out.println(count);
    }
}