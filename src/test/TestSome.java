package test;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName TestSome
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/11 17:43
 * @Version 1.0
 **/
public class TestSome {

    @Test
    public void test() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement s :
                stackTrace) {
            System.out.println(s.getClassName() + "." + s.getMethodName());
        }
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i == 2) {
                iterator.remove();
            }
        }
        ListIterator<Integer> il = list.listIterator();
        while (il.hasNext()) {
            System.out.print(il.next() + " ");
        }
        System.out.println();
        iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        Set<Integer> lhset = new LinkedHashSet<>();
        lhset.add(4);
        lhset.add(2);
        lhset.add(3);
        lhset.add(1);
        lhset.add(0);
        for (Integer i :
                lhset) {
            System.out.print(i + " ");
        }
        System.out.println();
        Set<Integer> tset = new TreeSet<>();
        tset.add(4);
        tset.add(2);
        tset.add(3);
        tset.add(1);
        tset.add(0);
        for (Integer i :
                tset) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("c", 3);
        map.put("a", 1);
        map.put("b", 2);
        for (Map.Entry<String, Integer> entry :
                map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        map = new LinkedHashMap<>();
        map.put("c", 3);
        map.put("a", 1);
        map.put("b", 2);
        for (Map.Entry<String, Integer> entry :
                map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        for (String key :
                map.keySet()) {
            System.out.println(key + "," + map.get(key));
        }
    }
}
