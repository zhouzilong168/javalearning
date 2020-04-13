package algorithm.sort.simple;

import org.junit.Test;
import algorithm.sort.SortUtils;

/**
 * @ClassName Choose
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 11:40
 * @Version 1.0
 **/
public class Choose {
    /*
    平均：O(n2)
    最好：O(n2)
    最坏：O(n2)
    空间：O(1)
     */
    // 原地排序 适用于键值小文件大的文件 少的空间复制
    public static void chooseSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) { // 小于当前最小
                    min = j;
                }
            }
            if (min != i) {
                SortUtils.swap(array, i, min);
            }
        }
    }

    @Test
    public void testChoose() {
        SortUtils.executeAll(Choose.class, "chooseSort");
    }
}
