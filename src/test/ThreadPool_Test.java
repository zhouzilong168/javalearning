package test;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPool_Test
 * @Description TODO 测试线程池使用
 * @Author thinkpad
 * @Date 2020/4/24 10:28
 * @Version 1.0
 **/
public class ThreadPool_Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===");
        SynchronousQueue<Integer> arrayDeque = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " size: " + arrayDeque.size() + " ele:" + arrayDeque.take());
            } catch (InterruptedException e) {
                System.out.println("!!!");
            }
        }).start();
        try {
            arrayDeque.put(1);
            System.out.println("放一个元素: " + arrayDeque.peek());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("???");
        }

        System.out.println("------");
        if (arrayDeque.isEmpty()) {
            arrayDeque.put(2);
            System.out.println("放第二个个元素: " + arrayDeque.peek());
        }
        System.out.println(Thread.currentThread().getName() + " " + arrayDeque.size());
        for (Integer i :
                arrayDeque) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void bool(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 2, 0l, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
/*            threadPoolExecutor.execute(()->{
                System.out.print(Thread.currentThread().getName()+"  ");
                System.out.print(threadPoolExecutor.getTaskCount()+"  ");
                System.out.println(threadPoolExecutor.getQueue().size());
*//*                System.out.println("------------------------------------------");
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadPoolExecutor.getActiveCount());
                System.out.println(threadPoolExecutor.getLargestPoolSize());
                System.out.println(threadPoolExecutor.getMaximumPoolSize());
                System.out.println(threadPoolExecutor.getPoolSize());
                System.out.println(threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS));
                System.out.println(threadPoolExecutor.getCompletedTaskCount());
                System.out.println(threadPoolExecutor.getTaskCount());
                System.out.println(threadPoolExecutor.getQueue().size());
                for (Runnable r :
                        threadPoolExecutor.getQueue()) {
                    System.out.println(r.toString());
                }
                System.out.println("------------------------------------------");*//*
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });*/
            threadPoolExecutor.execute(new ThreadTask(i));
            System.out.println("queue: " + threadPoolExecutor.getQueue().size());
            for (Runnable r :
                    threadPoolExecutor.getQueue()) {
                ThreadTask t = (ThreadTask) r;
                System.out.print(t.toString() + " ");
            }
            System.out.println();
/*            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        threadPoolExecutor.shutdown();
    }

    static class ThreadTask implements Runnable, Comparable<ThreadTask> {
        int priority;

        public ThreadTask(int priority) {
            super();
            this.priority = priority;
        }

        @Override
        public int compareTo(ThreadTask o) {
            return priority < o.priority ? -1 : 1;
        }

        @Override
        public void run() {
            System.out.println(priority + "  " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            return priority + "";
        }
    }
}

