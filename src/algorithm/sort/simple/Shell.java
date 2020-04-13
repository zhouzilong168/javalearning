package algorithm.sort.simple;

import org.junit.Test;
import algorithm.sort.SortUtils;

/**
 * @ClassName Shell
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 15:09
 * @Version 1.0
 **/
public class Shell {
    /*
    平均：O(nlogn~n2) O(n1.3)
    最好：O(nlogn)
    最坏：O(n2)
    空间：O(1)
     */
    public static void shellSort(int[] array) {
        // 定义间隔
        for (int interval = array.length / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < array.length; i++) {
                int tmp = array[i], j = i - interval;
                while (j >= 0 && array[j] > tmp) {
                    array[j + interval] = array[j];
                    j -= interval;
                }
                array[j + interval] = tmp;
            }
        }
    }

    @Test
    public void testShell() {
        SortUtils.executeAll(Shell.class, "shellSort");
    }
}
