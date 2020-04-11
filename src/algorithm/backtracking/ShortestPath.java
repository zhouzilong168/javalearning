package algorithm.backtracking;


import org.junit.Test;

/**
 * @ClassName ShortestPath
 * @Description TODO 处理最短路径的方法类
 * @Author thinkpad
 * @Date 2020/4/3 19:44
 * @Version 1.0
 **/
public class ShortestPath {
    @Test
    public void testTrack() {
/*        //从键盘获取输入
        Scanner input = new Scanner(System.in);
        int num = Integer.parseInt(input.nextLine());
        int n = num;
        Point[] points = new Point[n];
        //获得输入点的坐标并存入坐标数组中
        for(int i=0; i<points.length; i++) {
            String[] strings = input.nextLine().trim().split(",");
            points[i] = new Point(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }*/
        calculate(StartPoint, 0);
        //calculate1(StartPoint,0,points.length);
        System.out.println(minPath);
    }

    /*最短路径*/
    //定义一个坐标类
    static class Point {
        //(x,y)坐标
        int x, y;
        //判断是否遍历过的标志变量
        boolean isVisited;

        //构造函数
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.isVisited = false;//初始化为false，若遍历过则置为true
        }

        //获得该点到指定点的距离
        public int getLength(Point p) {
            return Math.abs(x - p.x) + Math.abs(y - p.y);
        }
    }

    //起始点
    static Point StartPoint = new Point(0, 0);
    static Point[] points = new Point[4];
    static int tolLen = 0;

    static {
        points[0] = new Point(4, 4);
        points[1] = new Point(7, 2);
        points[2] = new Point(2, 2);
        points[3] = new Point(2, 8);
    }

    //最小路径先设为系统最大值
    static int minPath = Integer.MAX_VALUE;
    //static int counts = 0;

    /**
     * 定义获取给定点到其余点的最小路径的方法，递归调用
     *
     * @param p     当前的起始点
     * @param count 用来对遍历过的点计数
     * @return
     */
    private static void calculate(Point p, int count) {
        //判断停止条件,如果所有的点遍历完，则返回
        if (count == points.length) {
            minPath = tolLen + p.getLength(StartPoint);
        }
        //否则遍历其余的点并进行路径和的计算
        for (Point point : points) {
            //判断此点是否遍历过
            if (!point.isVisited) {
                //计算起始点到遍历点之间的距离，从而更新最小路径
                tolLen += point.getLength(p);
                //该点的标志位置为true
                point.isVisited = true;
                //若小于最小路径，则遍历此点继续下一层
                if (tolLen + point.getLength(StartPoint) < minPath) {
                    //每遍历一个点，层次加1，起始点更改为遍历后的点,继续计算其余点
                    calculate(point, count + 1);
                }
                //将路径和倒减，标志置为false，进行下一个方案的计算
                tolLen -= point.getLength(p);
                point.isVisited = false;
            }
        }
    }

    /**
     * 排列树 实排列 上下交换递归
     *
     * @param p
     * @param i
     * @param n
     */
    private static void calculate1(Point p, int i, int n) {
        if (i >= n) {
            minPath = tolLen + p.getLength(StartPoint);
            //dispa();
            return;
        }
        // i 层的枚举
        for (int j = i; j < n; j++) {
            swap(i, j); // 交换实现排列元素不同
            tolLen += p.getLength(points[i]); // 目标条件等处理
            if (tolLen + points[i].getLength(StartPoint) < minPath) { // 剪枝函数
                calculate1(points[i], i + 1, n);
            }
            tolLen -= p.getLength(points[i]);
            swap(i, j); // 还原环境
        }
    }

    private static void swap(int i, int j) {
        Point tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private static void dispa() {
        for (Point p :
                points) {
            System.out.print("(" + p.x + "," + p.y + ")");
        }
        System.out.println();
    }

}
