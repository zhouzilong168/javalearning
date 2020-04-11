package sort.simple;

import org.junit.Test;
import sort.SortUtils;

/**
 * 冒泡排序
 *
 * @ClassName Bubble
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 10:53
 * @Version 1.0
 **/
public class Bubble {

    /*
    平均：O(n2)
    最好：O(n2) O(n)[改进]
    最坏：O(n2)
    空间：O(1)
     */

    /**
     * 从小到大排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtils.swap(array, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSortPlus(int[] array) {
        boolean flag = true;
        for (int i = array.length - 1; i > 0 && flag; i--) {
            flag = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtils.swap(array, j, j + 1);
                    flag = true;
                }
            }
        }
    }

    @Test
    public void testBubble() throws NoSuchMethodException {
        SortUtils.executeAll(Bubble.class, "bubbleSortPlus");
    }
}
