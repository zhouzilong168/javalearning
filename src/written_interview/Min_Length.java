package written_interview;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * TODO 两个集合最短距离
 */
public class Min_Length {

    public static void main1(String[] args) {
        Set<Pair> A = new HashSet<>();
        Set<Pair> B = new HashSet<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                A.add(new Pair(in.nextInt(), in.nextInt()));
            }
            for (int j = 0; j < m; j++) {
                B.add(new Pair(in.nextInt(), in.nextInt()));
            }

            System.out.println(String.format("%.3f", getMin(A, B)));
            A.clear();
            B.clear();
        }
    }

    private static double getMin(Set<Pair> A, Set<Pair> B) {
        double min = Double.MAX_VALUE;
        for (Pair w :
                A) {
            for (Pair l :
                    B) {
                double cur = w.getLen(l);
                if (cur < min) {
                    min = cur;
                }
            }
        }
        return min;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getLen(Pair pair) {
            int x1 = Math.abs(x - pair.x);
            int y1 = Math.abs(y - pair.y);
            return Math.sqrt(x1 * x1 + y1 * y1);
        }

        @Override
        public int hashCode() {
            return x << 16 + y >> 16;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return x == pair.x && y == pair.y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
/*
2
4
0 0
0 1
1 0
1 1
2 2
2 3
3 2
3 3
4
0 0
0 0
0 0
0 0
0 0
0 0
0 0
0 0
*/
        /*            System.out.print("A: ");
            System.out.println(A);
            System.out.print("B: ");
            System.out.println(B)*/;
/*        Set<Pair> s1 = new HashSet<>();
        s1.add(new Pair(1,1));
        s1.add(new Pair(1,1));
        System.out.println(s1.size());*/