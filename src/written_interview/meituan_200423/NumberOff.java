package written_interview.meituan_200423;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * @ClassName NumberOff
 * @Description TODO 2020.04.23士兵报数
 * @Author thinkpad
 * @Date 2020/4/24 9:48
 * @Version 1.0
 **/
public class NumberOff {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        LinkedList<Integer> lists = new LinkedList<>();
        int[] A = new int[n + 1];
        int[] res = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            lists.add(i);
            A[i] = in.nextInt();
        }
        int count = 1;
        while (count < n) {
            int num = 0;
            ListIterator<Integer> iterator = lists.listIterator();
            A[count] %= lists.size();
            while (iterator.hasNext()) {
                int now = iterator.next();
                if (num++ == A[count]) {
                    res[now] = n - count + 1;
                    iterator.remove();
                    break;
                }
            }
            count++;
        }
        res[lists.get(0)] = 1;
        for (int i = 1; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}