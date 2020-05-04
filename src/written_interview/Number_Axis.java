package written_interview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @ClassName Number_Axis
 * @Description TODO 数轴重合
 * @Author thinkpad
 * @Date 2020/4/24 10:00
 * @Version 1.0
 **/
public class Number_Axis {
    static LinkedList<Pair> list = new LinkedList<>();
    static LinkedList<Pair> plist = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean res;
        int m = in.nextInt(), count = 0;
        list.add(new Pair(in.nextInt(), in.nextInt(), 1));
        for (int i = 1; i < m; i++) {
            Pair tmp = new Pair(in.nextInt(), in.nextInt(), 1), re = null, tp = (Pair) tmp.clone();
            System.out.println(list);
            System.out.println("===========now==========" + tmp);
            Iterator<Pair> iterator = list.iterator();
            while (iterator.hasNext()) {
                if ((re = union(iterator.next(), tmp, tp)) != null) {
                    plist.add(re);
                }
            }
            System.out.println("plist: " + plist);
            if (tp != null) {
                if (tp.c == -1) {
                    plist.add(new Pair(tmp.l, tp.l));
                    plist.add(new Pair(tp.r, tmp.r));
                } else {
                    plist.add(tp);
                }
            }
            list.addAll(plist);
            plist.clear();
            System.out.println("list: " + list);
            list.removeIf(p -> p.l == p.r);
            System.out.println("list: " + list);
            System.out.println("===========end==========" + tmp + "\n");
        }
/*        for (int i = 1; i < m; i++) {
            Pair re = null,rp = list.get(i);
            for (int j = 0; j <i; j++) {
                Pair lp = list.get(j);
                System.out.println("before "+lp);
                System.out.println("before "+rp);
                if((re=union(lp,rp))!=null){
                    list.addFirst(re);
                }
                System.out.println("later "+lp);
                System.out.println("later "+rp);
            }
        }*/
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).c > count) {
                count = list.get(i).c;
            }
        }
        System.out.println(count);
    }

    private static Pair union(Pair lp, Pair rp, Pair tp) {
        Pair res = null;
        if (lp.l <= rp.l && lp.r >= rp.r) {
            res = new Pair(rp.l, rp.r, lp.c + 1);
            lp.r = rp.l;
            if (rp.r != lp.r) {
                plist.add(new Pair(rp.r, lp.r));
            }
            tp = null;
        } else if (rp.l <= lp.l && rp.r >= lp.r) {
            res = new Pair(lp.l, lp.r, lp.c + 1);
            lp.l = lp.r;
            tp.l = lp.l;
            tp.r = lp.r;
            tp.c = -1;
        } else if (lp.l < rp.r && lp.l >= rp.l) {
            res = new Pair(lp.l, rp.r, lp.c + 1);
            tp.r = lp.l;
            lp.l = rp.r;
        } else if (rp.l < lp.r && rp.l >= lp.l) {
            res = new Pair(rp.l, lp.r, lp.c + 1);
            tp.l = lp.r;
            lp.r = rp.l;
        }
/*        if (res != null) {
            System.out.println("res "+res);
        }*/
        return res;
    }

    private static Pair unions(Pair lp, Pair rp) {
        Pair res = null;
        if (lp.l < rp.r && lp.l >= rp.l) {
            int tc = lp.c > rp.c ? lp.c : rp.c;
            res = new Pair(lp.l, rp.r, tc + 1);
            lp.l = rp.r;
            rp.r = lp.l;
        } else if (rp.l < lp.r && rp.l >= lp.l) {
            int tc = lp.c > rp.c ? lp.c : rp.c;
            res = new Pair(rp.l, lp.r, tc + 1);
            rp.l = lp.r;
            lp.r = rp.l;
        }
        if (res != null) {
            System.out.println("res " + res);
        }
        return res;
    }
}

class Pair {
    int l, r, c;

    public Pair(int l, int r) {
        this.l = l;
        this.r = r;
        c = 1;
    }

    public Pair(int l, int r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "{[" + l + "," + r + "] " + c + "}";
    }

    @Override
    public Object clone() {
        return new Pair(l, r, c);
    }
}
