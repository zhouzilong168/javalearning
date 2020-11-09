package javaSE.MultiThread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ConcurrentLock
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/15 22:22
 * @Version 1.0
 **/
public class ConcurrentLock {
    //    AtomicInteger sum = new AtomicInteger(0);
    static AtomicInteger[] sums = new AtomicInteger[10];

    static {
        for (int i = 0; i < 10; i++) {
            sums[i] = new AtomicInteger(0);
        }
    }

    @Test
    public void testAtomic() throws InterruptedException {
        CountDownLatch latchs = new CountDownLatch(10);
        for (int k = 0; k < 10; k++) {
            final int t = k;
            new Thread(() -> {
                AtomicInteger sum = sums[t];
                CountDownLatch latch = new CountDownLatch(10);
                for (int i = 0; i < 10; i++) {
                    new Thread(() -> {
                        for (int j = 0; j < 10; j++) {
                            sum.incrementAndGet();
//                    System.out.println(Thread.currentThread().getName()+" == "+sum);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        latch.countDown();
                    }).start();
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(sum);
                latchs.countDown();
            }).start();
        }
        latchs.await();
    }

    int suml;

    @Test
    public void addSyn() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    synchronized (this) {
                        suml++;
                    }
                    System.out.println(Thread.currentThread().getName() + " == " + suml);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(suml);
    }

    @Test
    public void addLock() throws InterruptedException {
        ReentrantLock rl = new ReentrantLock();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    lock.writeLock().lock();
                    try {
                        suml++;
                    } finally {
                        lock.writeLock().unlock();
                    }
                    rl.lock();
                    try {
                        suml++;
                    } finally {
                        rl.unlock();
                    }

                    System.out.println(Thread.currentThread().getName() + " == " + suml);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(suml);
    }

}
