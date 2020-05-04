package written_interview;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @ClassName Poke
 * @Description TODO 手上扑克牌顶一张放桌子上一张放牌底，重复两步操作，后桌上顺序已知，求原有顺序
 * @Author thinkpad
 * @Date 2020/4/25 11:54
 * @Version 1.0
 **/
public class Poke {
    /*
    已知顺序：[13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    原有顺序：[13, 2, 12, 6, 11, 3, 10, 5, 9, 1, 8, 4, 7]
    */

    /**
     * 逆向思维，逆操作
     */
    @Test
    public void sovle_reverse() {
        Queue<Integer> queue = new ArrayDeque<>(); // 默认16大小，扩到2的幂，两倍扩容
        int number = 1;
        queue.offer(number++);
        while (number < 14) {
            queue.offer(queue.poll()); // 反向从低到顶
            queue.offer(number++); // 然后放回一张
        }
        System.out.println(Arrays.toString(queue.toArray()));
    }

    /**
     * 步长解决，观察可得第一次步长为2，后每次由于抽出了一半数，倍增*2
     */
    @Test
    public void sovle_span() {
        int number = 13, now = number, span = 2;
        int[] res = new int[number];
        boolean two = false;
        while (now > 0) {
            int i = 0;
            for (; i < number && i < span && res[i] != 0; i++) ;// 找到第一个为0的数
            if (two) { // 是否需要找第二个，看上一轮结束后尾巴有无为0
                int tmp = i++;
                for (; i < number && i < span && res[i] != 0; i++) ;
                if (i >= number) { // 越界说明没有，则只有一个数为0了
                    i = tmp;
                }
            }
            while (i < number) { // 按步长span赋值
                res[i] = now--;
                i += span;
            }
            two = true; // 最后一个赋值的之后有无为0数
            for (i = i - span; i >= 0 && i < number; i++) {
                if (res[i] == 0) {
                    two = false;
                    break;
                }
            }
            span *= 2; // 步长倍增
        }
        System.out.println(Arrays.toString(res));
    }

}
