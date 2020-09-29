package written_interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName Maino
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/21 12:55
 * @Version 1.0
 **/
public class Maino {

}

class Main1 {

    static class A {
        public float method(float a, float b) throws IOException {
            return a + b;
        }
    }

    static class B extends A {
        public float method(float a, float b) {
            return a + b;
        }
    }

    static class C extends B {
        static int x = 1;

        static {
            x += 1;
        }

        static class ListNode {
            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }
        }

        public static void main(String[] args) {
            System.out.println(x);
        }
    }
}
class Main0 {
    static class Good {
        int num, weight, value;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), maxV = in.nextInt();
        Good[] arr = new Good[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Good();
            arr[i].num = in.nextInt();
            arr[i].weight = in.nextInt();
            arr[i].value = in.nextInt();
        }
        Arrays.sort(arr, (a, b) -> {
            int res = 0;
            double aa = (double) a.value / a.weight, bb = (double) b.value / b.weight;
            if (aa < bb) {
                res = 1;
            } else if (aa > bb) {
                res = -1;
            }
            return res;
        });
        int res = 0;
        for (Good g :
                arr) {
            int i = g.num;
            for (; i > 0; i--) {
                if (i * g.weight <= maxV) {
                    break;
                }
            }
            res += i*g.value;
            maxV -= i*g.weight;
        }
        System.out.println(res);
    }
}

//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        for (int i = 0; i < str.length(); i++) {
//            if (Character.isDigit(str.charAt(i))) {
//                try {
//                    int tmp = Integer.parseInt(str.substring(i, i + 4));
//                    if (tmp > 999 && tmp < 4000) {
//                        System.out.print(tmp + " ");
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }

//    static Character a = '+', b = '-', c = '*', d = '(', e = ')';
//    static Deque<Character> stack = new LinkedList<>(), s = new LinkedList<>();
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        String[] arr = new String[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = in.nextLine();
//        }
//
//        for (int i = 0; i < n; i++) {
//            System.out.println(domain(arr[i]));
//        }
//    }
//
//    private static String domain(String str) {
//        String res = "invalid";
//        for (int i = 0; i < str.length(); i++) {
//            Character ch = str.charAt(i);
//            if (ch == ' ') {
//                continue;
//            }
//            if (ch == d) {
//                stack.push(ch);
//                Character chh = str.charAt(i + 1);
//                if (chh != a || chh != b || chh != c) {
//                    break;
//                }
//            } else if (ch == e) {
//                if (stack.element() != d) {
//                   break;
//                }
//                stack.pop();
//                int q = s.pop() - a, w = s.pop() - a;
//                s.push(cal(q,w));
//            } else if (ch == a || ch == b || ch == c) {
//
//            }
//        }
//        return res;
//    }
//    private static Character cal(int a, int b) {
//
//    }

//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt(), m = input.nextInt();
//        ArrayList<Integer> a = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            a.add(input.nextInt());
//        }
//        ArrayList<Integer> b = new ArrayList<>(), tmp = null;
//        for (int i = 0; i < m; i++) {
//            int k = 0, j = n / 2;
//            while (k < n / 2 && j < n) {
//                b.add(a.get(j++));
//                b.add(a.get(k++));
//            }
//            while (k < n / 2) {
//                b.add(a.get(k++));
//            }
//            while (j < n) {
//                b.add(a.get(j++));
//            }
//            tmp = a;
//            a = b;
//            b = tmp;
//            b.clear();
//        }
//        for (int i :
//                a) {
//            System.out.print(i + " ");
//        }
//    }

//    public static void main(String[] args) {
//        System.out.println("hello world");
////        Scanner input = new Scanner(System.in);
////        int n = input.nextInt();
////        for (int i = 0; i < n; i++) {
////            int len = input.nextInt();
////            String a = input.next();
////            String b = input.next();
////            System.out.println(domain(len,a,b));
////        }
//    }
//    public static int domain(int len,String a, String b) {
//        if(a.compareTo(b) > -1){
//            return 0;
//        }
//        int res =  b.charAt(0)-a.charAt(0);
//        for (int i = 1; i < len; i++) {
//            int tmp = 25+b.charAt(i)-a.charAt(i);
//            res *= tmp;
//        }
//        return res;
//    }