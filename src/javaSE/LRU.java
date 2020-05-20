package javaSE;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LRU
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/11 16:05
 * @Version 1.0
 **/
public class LRU<K, V> extends LinkedHashMap<K, V> {
    // 默认缓存大小为16
    private int cacheSize = 16;

    /**
     * 利用 LinkedHashMap 提供的机制 实现LRU缓存
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > cacheSize;
    }

    public LRU() {
        super(16, 0.75f, true);
    }

    public LRU(int cacheSize) {
        super(cacheSize, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    public LRU(int cacheSize, float loadFactor) {
        super(cacheSize, loadFactor, true);
        this.cacheSize = cacheSize;
    }

    /**
     * 测试LRU
     *
     * @param args
     */
    public static void main(String[] args) {
        LRU<Integer, Integer> map = new LRU<>(3);
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        System.out.println(map);
        map.put(4, 1);
        System.out.println(map);
        map.get(2);
        System.out.println(map);
        map.put(5, 1);
        System.out.println(map);
        // test();
    }

    /**
     * 测试LinkedHashMap的插入及访问有序
     */
    private static void test() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(3, 0.75f, true);
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(4, 1);
        System.out.println(map);
        map.get(2);
        System.out.println(map);
        System.out.println("---------------------");
        LinkedHashMap<Integer, Integer> map1 = new LinkedHashMap<>(3, 0.75f, false);
        map1.put(1, 1);
        map1.put(2, 1);
        map1.put(3, 1);
        map1.put(4, 1);
        System.out.println(map1);
        map1.get(2);
        System.out.println(map1);
        System.out.println("--------------------");
    }
}
