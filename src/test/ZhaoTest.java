package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName ZhaoTest
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/13 15:47
 * @Version 1.0
 **/
public class ZhaoTest {
    final int a;

    public ZhaoTest() {
        a = 1;
    }

    void a() {
        final int a;
        a = 1;
        System.out.println(a);
    }

    Object obj = new Object();

    void b() throws InterruptedException {
        synchronized (Thread.currentThread()) {
            System.out.println(1);
            obj.wait();
            System.out.println(2);
            obj.notify();
            System.out.println(3);
        }
    }

    synchronized void x() {
        System.out.println("x");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void y() {
        synchronized (this) {
            System.out.println("y");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static synchronized void z() {
        System.out.println("z");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void q() {
        synchronized (ZhaoTest.class) {
            System.out.println("q");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void w() {
        synchronized (new Object()) {
            System.out.println("w");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        new Thread(this::x).start();
        new Thread(this::y).start();
        new Thread(ZhaoTest::z).start();
        new Thread(ZhaoTest::q).start();
        new Thread(ZhaoTest::w).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Object o1 = new Object(), o2 = new Object();

    void l1() throws InterruptedException {
        synchronized (o1) {
            System.out.println("l1 get o1");
            Thread.sleep(2000);
            synchronized (o2) {
                System.out.println("l2 get o2");
                Thread.sleep(10000);
            }
        }
    }

    void l2() throws InterruptedException {
        synchronized (o2) {
            System.out.println("l2 get o2");
            Thread.sleep(3000);
            synchronized (o1) {
                System.out.println("l2 get o1");
                Thread.sleep(1000);
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        l1();
        l2();
    }

    class A {
        final void a() {

        }
    }

    class B extends A {
        private void aa() {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer i :
                    map.keySet()) {
                System.out.println(map.get(i));
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());

            }
        }
    }

    public void tt() {
        new ThreadPoolExecutor(3, 6, 60l, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        Executors.newSingleThreadExecutor();//1，1线程，无界队列LinkedBlockingQueue，顺序执行
        Executors.newFixedThreadPool(3);// 3,3线程，无界队列LinkedBlockingQueue,顺序执行
        Executors.newCachedThreadPool();//o,Max线程，直接提交队列SynchronousQueue，60s延时，创建执行
        Executors.newSingleThreadScheduledExecutor();// 单线程定时调度 1核心，DelayedWorkQueue
        Executors.newScheduledThreadPool(3);// 多线程定时调度
    }

    interface Face {
        default void p() {
            System.out.println("p");
        }

        static void pp() {
            System.out.println("pp");
        }

        public abstract void a();

        public static final int a = 1;
    }

    @Test
    public void ttt() {
        int i1 = 1;
        Integer i = 1;
//        Integer ii = 1;
        Integer ii = new Integer(1);
        System.out.println(i == ii);
        System.out.println(i.equals(ii));
        System.out.println();
    }

    @Test
    public void test11() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.add(null);
        System.out.println(queue);
    }

}
