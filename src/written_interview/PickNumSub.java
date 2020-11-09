package written_interview;

import java.util.Scanner;

/**
 * @ClassName PickNumSub
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/21 20:43
 * @Version 1.0
 **/
public class PickNumSub {

    static class Main {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
            String a = "a", b = "b";
            StringBuilder sb = new StringBuilder();
            if (n >= k) {
                for (int i = 0; i < k; i++) {
                    sb.append(a);
                }
                System.out.println(sb.toString());
            } else if ((n + m) >= k) {
                for (int i = 0; i < n; i++) {
                    sb.append(a);
                }
                for (int i = 0; i < k - n; i++) {
                    sb.append(b);
                }
                System.out.println(sb.toString());
            } else {
                int index = 0, flag = 0;
                while (index < k) {
                    if (flag % 3 == 0) {
                        
                    }
                }
            }
        }
    }


    static class Main1 {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                domain(in.nextInt());
            }
        }

        private static void domain(int n) {

        }
    }

    static int res = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            res = 0;
            getNum(in.nextInt());
            System.out.println(res);
        }
    }

    static void getNum(int n) {
        if (n == 1) {
            res++;
        } else if (n < 4) {
            res += 2;
        } else {
            res += 1;
            getNum(n / 2);
        }
    }
}
