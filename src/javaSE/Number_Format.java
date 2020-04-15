package javaSE;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @ClassName Number_Format
 * @Description TODO double格式数据保留两位小数输出 四种方式
 * @Author thinkpad
 * @Date 2020/4/15 15:43
 * @Version 1.0
 **/
public class Number_Format {

    public static void main(String[] args) {
        // way1 Math.round
        double per = (double) Math.round((double) 6 / 9 * 10000) / 100;
        System.out.println(per);
        double d = (double) 6 / 9 * 100;
        // way2 NumberFormat
        NumberFormat nformat = NumberFormat.getNumberInstance();
        nformat.setMaximumFractionDigits(2); // 保留两位小数
        nformat.setRoundingMode(RoundingMode.UP); // 四舍五入
        System.out.println(nformat.format(d));
        // way3 DecimalFormat
        DecimalFormat dFormat = new DecimalFormat("#.00");
        System.out.println(dFormat.format(d));
        // way4 String.format
        System.out.println(String.format("%.2f", d));
    }
}
