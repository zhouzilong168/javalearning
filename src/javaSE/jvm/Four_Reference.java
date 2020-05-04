package javaSE.jvm;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @ClassName Main
 * @Description TODO java 四种引用测试
 * @Author thinkpad
 * @Date 2020/4/26 21:56
 * @Version 1.0
 **/
public class Four_Reference {
    private static final Logger logger = Logger.getLogger("hello");

    public static void main(String[] args) throws Exception {
/*        String[] a = new String[100];
        SoftReference<String[]> sr = new SoftReference<>(a);
        System.out.println(sr.get());
        System.gc();
        System.out.println(sr.get());
        a=null;
        System.gc();
        System.out.println(sr.get());

        String[] b = new String[100];
        WeakReference<String[]> wr = new WeakReference<>(b);
        System.out.println(wr.get());
        System.gc();
        System.out.println(wr.get());
        b=null; // 需要置虚索引指向对象的其他强索引为null，才会执行GC
        System.gc();
        System.out.println(wr.get());

        ReferenceQueue<String[]> pqs = new ReferenceQueue<>();
        String[] c = new String[100];
        PhantomReference<String[]> pr = new PhantomReference<>(c,pqs);
        System.out.println(c);
        System.out.println(pr.get());
        System.out.println(pqs.poll());
        System.gc();
        System.out.println(pr.get());*/

        /*创建引用队列*/
        ReferenceQueue<SoftReference<int[]>> rq =
                new ReferenceQueue<SoftReference<int[]>>();

        /*创建一个软引用数组，每一个对象都是软引用类型*/
        SoftReference<int[]>[] srArr = new SoftReference[1000];

        for (int i = 0; i < srArr.length; i++) {
            srArr[i] = new SoftReference(new int[300000], rq);
        }

        /*（可能）在gc前保留下了三个强引用*/
        int[] arr1 = srArr[30].get();
        int[] arr2 = srArr[60].get();
        int[] arr3 = srArr[90].get();
        System.out.println("srArr[1]: " + srArr[1]);
        /*占用内存，会导致一次gc，使得只有软引用指向的对象被回收*/
        int[] strongRef = new int[200000000];
        System.out.println("srArr[1]: " + srArr[1]);
        Object x;
        int n = 0;
        long before = System.currentTimeMillis();
        while ((x = rq.poll()) != null) {
            int idx = 0;
            while (idx < srArr.length) {
                if (x == srArr[idx]) {
                    //System.out.println("free " + x);
                    srArr[idx] = null; // 手动释放内存
                    n++;
                    break;
                }
                idx++;
            }
/*            x=null; // X 对应的空间有两个引用，引用队列中的值也只是复制过来的引用
            n++;*/
        }
        System.out.println("used time：" + (System.currentTimeMillis() - before));
        /*当然最简单的方法是通过isEnqueued()判断一个软引用方法是否在
         * 队列中，上面的方法只是举例
         int n = 0;
         for(int i = 0; i < srArr.length; i++){
            if(srArr[i].isEnqueued()){
                srArr[i] = null;
                n++;
            }
         }
        */
        System.out.println("recycle  " + n + "  SoftReference Object");

        System.out.println("srArr[1]: " + srArr[1]);
    }

    @Test
    public void test() {
        System.out.println("测试一下日志：");
        for (int i = 0; i < 10; i++) {
            logger.info("INFO: this is " + i + " time.");
        }

        for (int i = 0; i < 10; i++) {
            logger.warning("WARN: this is " + i + " time.");
        }
        logger.fine("fine is what");
    }

    @Test
    public void test1() throws Exception {
        String[] a = new String[100];
        SoftReference<String[]> sr = new SoftReference<>(a);
        System.out.println(sr.get());
        System.gc();
        System.out.println(sr.get());
        String[] b = new String[100];
        WeakReference<String[]> wr = new WeakReference<>(b);
        System.out.println(wr.get());
        System.gc();
        Thread.sleep(1000);
        System.out.println(wr.get());
        SoftReference<int[]>[] srs = new SoftReference[1000];
    }

    @Test
    public void test2() {
        String[] strs = new String[100];
        SoftReference<String[]> sr = new SoftReference<>(strs);
        System.out.println(sr);
        System.out.println(strs);
        System.out.println(strs[0]);
        System.out.println(strs[1]);
        strs[1] = "haha";
        System.out.println(strs[1]);
    }

    @Test
    public void test3() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "111");
        hashMap.put(1, "11");
        hashMap.put(17, "17");
        hashMap.put(16, "16");
        hashMap.put(32, "32");
        for (Integer i :
                hashMap.keySet()) {
            System.out.println(i + "\t" + hashMap.get(i));
        }
        System.out.println(hashMap.size());
    }
}
