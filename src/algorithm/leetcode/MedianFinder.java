package algorithm.leetcode;

import java.util.PriorityQueue;

/**
 * @ClassName MedianFinder
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/29 11:05
 * @Version 1.0
 **/
public class MedianFinder {
    int size;
    PriorityQueue<Integer> minHeap, maxHeap;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        size = 0;
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
    }

    public void addNum(int num) {
        if (size % 2 == 0) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
        size++;
    }

    public double findMedian() {
        if (size == 0) {
            return 0;
        }
        double res;
        if (size % 2 == 0) {
            res = (double) (minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            res = minHeap.peek();
        }
        return res;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
}
