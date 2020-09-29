package written_interview;

import java.util.*;

/**
 * @ClassName AQY
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/13 17:08
 * @Version 1.0
 **/
public class AQY {

}

class NoRepeatString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(domain(str).length());
    }

    private static String domain(String str) {
        String res = "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            builder.append(str.charAt(i));
            for (int j = i + 1; j < str.length(); j++) {
                if (isSame(str.substring(i, j), str.charAt(j))) {
                    if (res.length() < builder.length()) {
                        res = builder.toString();
                    }
                    builder.delete(0, builder.length());
                    break;
                }
                builder.append(str.charAt(j));
            }
        }
        return res;
    }

    private static boolean isSame(String str, char in) {
        boolean res = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == in) {
                res = true;
                break;
            }
        }
        return res;
    }
}

class ThreeToZero {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        Collections.sort(list);
        int z = 0;
        for (; z < list.size() && list.get(z) < 0; z++) ;
        for (int i = 0; i < z; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int k = j + 1;
                if (list.get(j) < 0) {
                    k = z;
                }
                for (; k < list.size(); k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(list.get(i));
                        tmp.add(list.get(j));
                        tmp.add(list.get(k));
                        if (isDif(res, tmp)) {
                            res.add(tmp);
                        }
                    }
                }
            }
        }
        sort(res);
        for (List<Integer> l :
                res) {
            for (Integer i :
                    l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> sort(List<List<Integer>> lists) {
        lists.sort((a, b) -> {
            int res = 0, i = 0;
            for (; i < a.size(); i++) {
                if (a.get(i) < b.get(i)) {
                    res = -1;
                    break;
                } else if (a.get(i) > b.get(i)) {
                    res = 1;
                    break;
                }
            }
            return res;
        });
        return lists;
    }

    private static boolean isDif(List<List<Integer>> set, List<Integer> tmp) {
        boolean res = true;
        for (List<Integer> list :
                set) {
            if (isSame(list, tmp)) {
                res = false;
                break;
            }
        }
        return res;
    }

    private static boolean isSame(List<Integer> a, List<Integer> b) {
        boolean res = true;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) {
                res = false;
                break;
            }
        }
        return res;
    }

    private static int compare(List<Integer> a, List<Integer> b) {
        int res = 0, i = 0;
        for (; i < a.size(); i++) {
            if (a.get(i) < b.get(i)) {
                res = -1;
                break;
            } else if (a.get(i) > b.get(i)) {
                res = 1;
                break;
            }
        }
        return res;
    }
}

class TimesBiggerThenHalf {
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (in.hasNextInt()) {
            count++;
            int t = in.nextInt();
            Integer tm = map.get(t);
            if (tm != null) {
                map.put(t, tm + 1);
            } else {
                map.put(t, 1);
            }
        }
        count /= 2;
        for (Map.Entry<Integer, Integer> entry :
                map.entrySet()) {
            if (entry.getValue() > count) {
                System.out.println(entry.getKey());
                return;
            }
        }
    }
}
