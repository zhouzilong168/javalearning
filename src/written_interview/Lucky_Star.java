package written_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Lucky_Star
 * @Description TODO 幸运星问题，解决坐标轴四向都有问题
 * @Author thinkpad
 * @Date 2020/4/24 9:54
 * @Version 1.0
 **/
public class Lucky_Star {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int res = 0;
        for (int i = 0; i < n; i++) {
            points.add(new Point(in.nextInt(), in.nextInt()));
        }
        for (Point p :
                points) {
            if (judge(p)) {
                res++;
            }
        }
        System.out.println(res);
        points.clear();
    }

    private static boolean judge(Point point) {
        boolean xs = false, xb = false, ys = false, yb = false, res = false;
        for (Point p :
                points) {
            if (p.x == point.x) {
                if (!xs && p.y < point.y) {
                    xs = true;
                }
                if (!xb && p.y > point.y) {
                    xb = true;
                }
            }
            if (p.y == point.y) {
                if (!ys && p.x < point.x) {
                    ys = true;
                }
                if (!yb && p.x > point.x) {
                    yb = true;
                }
            }
            if (xs && xb && ys && yb) {
                res = true;
                break;
            }
        }
        return res;
    }
}
