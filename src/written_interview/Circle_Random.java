package written_interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName Circle_Random
 * @Description TODO 大小圈概率问题，大圈R圆心（0,0），小圆r等概率出现在大圆内，被大圆完全覆盖，
 * 给定一系列点（1~n）出现在大圆内，求被小圆覆盖的概率，并按概率排序（概率相同按序号）输出序号
 * @Author thinkpad
 * @Date 2020/5/3 20:25
 * @Version 1.0
 **/
public class Circle_Random {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), R = in.nextInt(), r = in.nextInt();
        int sub = R - r, inside2 = (sub - r) * (sub - r);
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(in.nextInt(), in.nextInt());
            int tmp = pairs[i].getLen2();
            pairs[i].x = tmp <= inside2 ? -1 : tmp;
            pairs[i].y = i + 1;
        }

        Arrays.sort(pairs, (a, b) -> {
            if (a.x == b.x) {
                return a.y > b.y ? 1 : -1;
            }
            return a.x > b.x ? 1 : -1;
        });

        System.out.print(pairs[0].y);
        for (int i = 1; i < n; i++) {
            System.out.print(" " + pairs[i].y);
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getLen2() {
            return x * x + y * y;
        }
    }
}
