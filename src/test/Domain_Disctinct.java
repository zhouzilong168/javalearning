package test;

import java.util.*;

/**
 * TODO 根据路径，判断域名是否对应，字符串集合匹配解决
 */
public class Domain_Disctinct {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strs = new String[n];
        sc.skip("\n"); // 跳过输入数字时末尾的换行
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }

        Set<String> set = new HashSet<>(); // 存放域名（开头相同字符串）
        HashMap<String, Set<String>> map = new HashMap<>();// 存放域名对应下所有的请求

        // 分隔域名及路径，并添加到对应集合保存
        int size = set.size();
        for (int i = 0; i < n; i++) {
            int index = strs[i].indexOf("/", 7);
            String head = "", tail = "";
            if (index == -1) { // 空路径
                head = strs[i];
            } else {
                head = strs[i].substring(0, index);
                tail = strs[i].substring(index);
            }
            set.add(head);
            if (size == set.size()) { // 即之前已经将和当前域名一样的字符串放入set中
                map.get(head).add(tail); // 直接获得map中的set集合添加当前路径即可
            } else { // 第一次遇到，新建一个set，并添加<head,set>到map中
                size++;
                Set<String> tmp = new HashSet<>();
                tmp.add(tail);
                map.put(head, tmp);
            }
        }

        Map<Integer, Set<String>> res = new HashMap<>();
        Map<String, Integer> temp = new HashMap<>();
        String[] arr = new String[size];

        //System.out.println("set: ");
        for (String st :
                set) {
            arr[--size] = st;
            //System.out.print(st+"  ");
        }
/*        System.out.println("\nmap");
        for (Map.Entry entry :
                map.entrySet()) {
            System.out.print("key: "+entry.getKey());
            System.out.println(entry.getValue()+"  ");
        }
        System.out.println();*/

        // 判断同域名对应路径是否相同
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                String s = arr[i], ss = arr[j];
                if (!s.equals(ss)) {
                    if (compare(map.get(s), map.get(ss))) {
                        Integer st = temp.get(s), sst = temp.get(ss);
                        if (st != null) {
                            res.get(st).add(ss);
                        } else if (sst != null) {
                            res.get(sst).add(s);
                        } else {
                            Set<String> tmp = new HashSet<>();
                            tmp.add(s);
                            tmp.add(ss);
                            temp.put(s, ++count);
                            temp.put(ss, count);
                            res.put(count, tmp);
                        }
                    }
                }
            }
        }
        System.out.println(res.size());
        for (Integer integer :
                res.keySet()) {
            for (String s :
                    res.get(integer)) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    private static boolean contain(List<String> list, int j) {
        for (int i = j; i >= 0; i--) {
            if (list.get(i).equals(list.get(j))) {
                list.remove(j);
                return true;
            }
        }
        return false;
    }

    public static void main1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> strs = new LinkedList<>();
        sc.skip("\n"); // 跳过输入数字时末尾的换行
        for (int i = 0; i < n; i++) {
            strs.add(sc.nextLine());
        }

        Set<String> set = new HashSet<>(); // 存放域名（开头相同字符串）
        HashMap<String, Set<String>> map = new HashMap<>();// 存放域名对应下所有的请求

        // 分隔域名及路径，并添加到对应集合保存
        int size = set.size();
        for (int i = 0; i < n; i++) {
            String str = strs.get(i);
            int index = str.indexOf("/", 7);
            String head = "", tail = "";
            if (index == -1) { // 空路径
                head = str;
            } else {
                head = str.substring(0, index);
                tail = str.substring(index);
            }
            if (contain(strs, i)) { // 之前已经遍历过当前字符串
                map.get(head).add(tail); // 直接获得map中的set集合添加当前路径即可
            } else {

                Set<String> tmp = new HashSet<>();
                tmp.add(tail);
                map.put(head, tmp);
            }
            set.add(head);
            if (size == set.size()) { // 即之前已经将和当前域名一样的字符串放入set中
                map.get(head).add(tail); // 直接获得map中的set集合添加当前路径即可
            } else { // 第一次遇到，新建一个set，并添加<head,set>到map中
                size++;
                Set<String> tmp = new HashSet<>();
                tmp.add(tail);
                map.put(head, tmp);
            }
        }

        Map<Integer, Set<String>> res = new HashMap<>();
        Map<String, Integer> temp = new HashMap<>();
        String[] arr = new String[size];

        //System.out.println("set: ");
        for (String st :
                set) {
            arr[--size] = st;
            //System.out.print(st+"  ");
        }
/*        System.out.println("\nmap");
        for (Map.Entry entry :
                map.entrySet()) {
            System.out.print("key: "+entry.getKey());
            System.out.println(entry.getValue()+"  ");
        }
        System.out.println();*/

        // 判断同域名对应路径是否相同
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                String s = arr[i], ss = arr[j];
                if (!s.equals(ss)) {
                    if (compare(map.get(s), map.get(ss))) {
                        Integer st = temp.get(s), sst = temp.get(ss);
                        if (st != null) {
                            res.get(st).add(ss);
                        } else if (sst != null) {
                            res.get(sst).add(s);
                        } else {
                            Set<String> tmp = new HashSet<>();
                            tmp.add(s);
                            tmp.add(ss);
                            temp.put(s, ++count);
                            temp.put(ss, count);
                            res.put(count, tmp);
                        }
                    }
                }
            }
        }
        System.out.println(res.size());
        for (Integer integer :
                res.keySet()) {
            for (String s :
                    res.get(integer)) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    private static boolean compare(Set<String> l1, Set<String> l2) {
        return l1.containsAll(l2) && l2.containsAll(l1);
    }
}
/*
12
http://google.ru/test
http://google.ru/
http://google.com
http://google.com/test
http://google.de/
http://google.ru/test
http://google.de/test
http://google.com/
http://google.com/t
http://google.com/test
http://google.hello/test
http://google.hello/

*/