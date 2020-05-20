package written_interview;

import java.text.DecimalFormat;

/**
 * TODO 实现根号 小数点后三位精度 字节面试题
 */
public class Sqrt {
    /*
    精度设置
     */
    static final double ACCURACY = 0.001;

    /**
     * 对double类型开根号，按精度要求
     *
     * @param d
     * @return
     */
    public static double sqrt(double d) {
        double left = 1, right = d, res = 0.0; // 查找的上下边界
        if (d > 2) { // 当数大于二的时候可以收缩查找范围为 1~d/2 证：[d2/2 ~ d]
            right = d / 2;
        } else if (d <= 0) {
            throw new ArithmeticException("sqrt can't use for less than zero like " + d);
        } else if (d < 1) { // 小于1，收缩范围为d~1
            left = d;
            right = 1;
        } else if (d == 1) {// 等于1，直接返回1即可
            return 1.0;
        }
        for (double i = left; i < right; i += ACCURACY) {
            double tmp = i * i;
            if (d - tmp < 0.01) {
                res = i;
                double k = 0; // 精确度提高，往后遍历比较一位，若当前精度差小于则终止
                for (double j = i; j < right; ) {
                    k = j + ACCURACY;
                    if (Math.abs(d - j * j) < Math.abs(d - k * k)) {
                        res = j;
                        break;
                    }
                    j = k;
                }
                break;
            }
        }
        return (double) ((int) (res * 1000)) / 1000;// 三位小数
    }

    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("#0.000");
        System.out.println(sqrt(1) + "\t" + format.format(Math.sqrt(1)));
        System.out.println(sqrt(2) + "\t" + format.format(Math.sqrt(2)));
        System.out.println(sqrt(4) + "\t" + format.format(Math.sqrt(4)));
        System.out.println(sqrt(0.09) + "\t" + format.format(Math.sqrt(0.09)));
        System.out.println(sqrt(64) + "\t" + format.format(Math.sqrt(64)));
        System.out.println(sqrt(256) + "\t" + format.format(Math.sqrt(256)));
        System.out.println(sqrt(0.81) + "\t" + format.format(Math.sqrt(0.81)));
        //System.out.println(sqrt(-1));
    }
}


