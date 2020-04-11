package sort.simple;

import org.junit.Test;
import sort.SortUtils;

/**
 * @ClassName Insert
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 11:21
 * @Version 1.0
 **/
public class Insert {
    /*
    平均：O(n2)
    最好：O(n)
    最坏：O(n2)
    空间：O(1)
     */
    // 原地排序 稳定 可用于预排序 数据规模小
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i], j = i - 1;
            while (j >= 0 && array[j] > tmp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    @Test
    public void testInsert() {
        SortUtils.executeAll(Insert.class, "insertSort");
    }
}
