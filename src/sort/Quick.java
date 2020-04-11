package sort;

import javafx.util.Pair;
import org.junit.Test;
import sort.simple.Insert;

import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName Quick
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 18:10
 * @Version 1.0
 **/
public class Quick {

    /*
    平均：O(nlogn)
    最好：O(nlogn)
    最坏：O(n2)
    空间：O(1)
     */
    // 优化 三数中值法 处理重复元素可用三指针也可直接位移指针去中间
    private static void quickSortR(int[] array, int low, int high) {
        if (low < high) {
            int mid = partitionOne(array, low, high);
            quickSortR(array, low, mid - 1);
            quickSortR(array, mid + 1, high);
        }
    }

    private static void quickSortR1(int[] array, int low, int high) {
        if (low < high) {
            int mid = partition(array, low, high);
            quickSortR1(array, low, mid - 1);
            quickSortR1(array, mid + 1, high);
        }
    }

    @Test
    public void test01() {
        int size = 10000; // 10000 && 100000 && 10000000
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            //array[i]=random.nextInt(size);
            array[i] = 10;
        }
        int[] arrays = Arrays.copyOf(array, array.length);
        int[] arrayss = Arrays.copyOf(array, array.length);
        long before = System.currentTimeMillis();
        quickSortR(array, 0, array.length - 1);
        System.out.println("one time: " + (System.currentTimeMillis() - before));

        long before1 = System.currentTimeMillis();
        quickSortR1(arrays, 0, arrays.length - 1);
        System.out.println("two time: " + (System.currentTimeMillis() - before1));

        long before2 = System.currentTimeMillis();
        quickSortThree(arrayss, 0, arrays.length - 1);
        System.out.println("three time: " + (System.currentTimeMillis() - before2));
    }

    /**
     * 单向扫描分区快排，i指针之前的都是小于等于主元的，j之后的都是大于等于主元的
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    private static int partitionOne(int[] array, int low, int high) {
        int i = low + 1, j = high, v = array[low];
        while (i < j) {
            if (array[i] <= v) {
                i++;
            } else {
                SortUtils.swap(array, i, j--);
            }
        }
        /**
         * 边界：i扫描到j
         * 若主元小，j左移，否则，不动
         * 最终都是j指向了<=
         */
        if (array[j] > v) {
            j--;
        }
        array[low] = array[j];
        array[j] = v;
        return j;
    }

    /**
     * 双向扫面
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] array, int low, int high) {
        int midValue = array[low], i = low + 1, j = high;
        while (i < j) {
            // 自左往右找第一个比midValue大的
            while (i < j && array[i] <= midValue) i++;
            // 自右往左找第一个比midValue小的
            while (array[j] > midValue) j--;
            if (i < j) {
                SortUtils.swap(array, i, j);
            }
        }
        // i之前的的数段小于等于midValue，循环退出为i>=j，交换low与j的值即可
        array[low] = array[j];
        array[j] = midValue;
        return j;
    }

    /**
     * 三指针切分法，处理重复元素问题
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    private static Pair partitionThree(int[] array, int low, int high) {
        int e = low + 1, i = low + 1, j = high, v = array[low];
        /**
         * low+1~e-1小于主元
         * e~i-1    等于主元
         * i~j      未知
         * j+1~high 大于主元
         * 边界：i，j同指
         * 最终都是j指向了等于区间，i指向了大于区间
         */
        while (i <= j) {
            if (array[i] < v) {// 小于主元 交换e和i，e++，s++ 扩增小于区间，等于区间左移
                SortUtils.swapRep(array, e++, i++);
            } else if (array[i] == v) { // 等于主元，i++,扩增等于区间
                i++;
            } else { // 大于主元，交换i和j，j--，j原指向未知，还需再判，扩增大于区间，未知区间右减一
                SortUtils.swapRep(array, i, j--);
            }
        }
        SortUtils.swap(array, low, e - 1);
        Pair pair = new Pair<>(e - 2, i);
        return pair;
    }

    public static void quickSortThree(int[] array, int low, int high) {
        if (low < high) {
            Pair pair = partitionThree(array, low, high);
            quickSortThree(array, low, (int) pair.getKey());
            quickSortThree(array, (int) pair.getValue(), high);
        }
    }

    private void partition_Three_Dot(int[] array, int low, int high) {

    }

    private void sortThreeDot(int[] array, int low, int high) {
        int mid = (low + high) >> 1;
        if (array[low] > array[mid]) {
            SortUtils.swap(array, low, mid);
        }
    }

    @Test
    public void test() {
        SortUtils.executeAll(Quick.class, "quickSort");
    }

    public static void quickSort(int[] array) {
        //quickSortR(array,0,array.length-1);
        quickSortThree(array, 0, array.length - 1);
    }
}
