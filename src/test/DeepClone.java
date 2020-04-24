package test;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * @ClassName IntegerPool
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/19 18:21
 * @Version 1.0
 **/
public class DeepClone {

    /**
     * 深拷贝
     *
     * @return
     */
    public Object deepClone() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); // 写this对象到Byte流

            bis = new ByteArrayInputStream(bos.toByteArray()); // 得到字节流中的数组
            ois = new ObjectInputStream(bis);
            return ois.readObject();// 再从字节数组中读取对象
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                assert bos != null;
                bos.close();
                assert oos != null;
                oos.close();
                assert bis != null;
                bis.close();
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() throws Exception {
        String input = "1 fish 2 fish red fish blue fish";
        Scanner s = new Scanner(input);
        s.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
        MatchResult result = s.match();
        for (int i = 1; i <= result.groupCount(); i++)
            System.out.println(result.group(i));
        s.close();
    }

    public Object test11() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void test(double l) {
        System.out.println("double:" + l);
    }

    public void test(String l) {
        System.out.println("String:" + l);
    }

    @Test
    public void test01() {
        short i = 10;
        test(i);
        double d = 5.5;
        float f = 6.6f;
        test(f);
        test(d);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入：");
        int matchNum = 1;
        while (matchNum < 5 && scanner.hasNext()) {
            System.out.print("扫描到第" + matchNum + "段匹配的内容：");
            String str = scanner.next();
            System.out.println(str);
            System.out.println(Integer.parseInt(str));
            matchNum++;
        }
        System.out.println("能到这儿吗？");
        //do something
    }

    @Test
    public void test1() {
        Scanner input = new Scanner(System.in);
        int count = 0, begin = 0, end = 0;
        while (count < 1 && input.hasNext()) {
            begin = input.nextInt();
            end = input.nextInt();
            count++;
        }
        if (begin > end || begin < 0 || end < 0) {
            System.out.println("error data");
        }
    }

    public static List<Integer> getAll(int begin, int end) {
        List<Integer> res = new LinkedList<>();
        int b, s, g;
        begin = begin < 100 ? 100 : begin;
        end = end > 999 ? 999 : end;
        System.out.println("======= " + begin + "\t" + end);
        for (int i = begin; i <= end; i++) {
            g = i % 10;
            s = i / 10 % 10;
            b = i / 100;
            if ((g * g * g + s * s * s + b * b * b) == i) {
                res.add(i);
            }
        }
        return res;
    }
}
