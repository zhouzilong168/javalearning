package javaSE.MultiThread;

import java.util.stream.IntStream;

/**
 * @ClassName ThreadLocal_Security
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/2 15:33
 * @Version 1.0
 **/
public class ThreadLocal_Security {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("主线程变量");
        IntStream.range(1, 10).parallel().forEach(id -> {
            System.out.println(String.format("%s==%s", id, local.get()));
        });
        Thread.sleep(1000);
        System.out.println("-----------------------------");
        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(String.format("%s==%s", temp, local.get()));
            }).start();
        }
        System.out.println(Thread.currentThread().getName() + "==" + local.get());


    }
}
