package algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序及改进
 *
 * @ClassName Merge
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/13 17:07
 * @Version 1.0
 **/
public class Merge {
    /*
    平均：O(nlogn)
    最好：O(nlogn)
    最坏：O(nlogn)
    空间：O(n)
    稳定排序
     */
    public void sortByMerge(int[] array, int from, int to) {
        mergeByRecursion(array, from, to - 1);
    }

    public void sortByMerge1(int[] array, int from, int to) {
        mergeByNoRecursion(array, from, to - 1);
    }

    /**
     * 递归
     *
     * @param array
     */
    private void mergeByRecursion(int[] array, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) >> 1;
            mergeByRecursion(array, begin, mid);
            mergeByRecursion(array, mid + 1, end);
            //merge(array,begin,mid+1,end);
            //mergePlus(array,begin,mid+1,end);
            mergeByInplace(array, begin, mid + 1, end);
        }
    }

    /**
     * 非递归
     *
     * @param array
     * @param begin
     * @param end
     */
    private void mergeByNoRecursion(int[] array, int begin, int end) {
        int length = 1, left, mid, right;
        for (; length < end - begin + 1; length *= 2) {
            for (left = begin; left + length <= end; left += 2 * length) {
                mid = left + length >= end ? end : left + length;
                right = mid + length - 1 >= end ? end : mid + length - 1;
                //merge(array,left,mid,right);
                //mergePlus(array,left,mid,right);
                mergeByInplace(array, left, mid, right);
            }
        }
    }

    /**
     * 原地merge
     *
     * @param array
     * @param begin
     * @param mid
     * @param end
     */
    private void mergeByInplace(int[] array, int begin, int mid, int end) {
        int j = mid;
        // 循环直到begin和j相等，或j到达末端end，意为一段合并完了，已然有序
        while (begin < j && j <= end) {
            // 跳出条件为左边第一个比右边j大 或 i=mid
            while (begin < mid && array[begin] <= array[j]) begin++;
            // 进入条件入左边大于右边 or i=mid 跳出条件为右边第一个比左边i小 交换i~mid-1和index~j-1
            while (j <= end && array[j] <= array[begin]) j++;
            swapRange(array, begin, mid, j - 1);
            // 更改i的指向为交换后的位置
            begin += j - mid;
            // 更新mid的位置为第二段首，即为j的位置
            mid = j;
        }
    }

    private void swapRange(int[] array, int i, int mid, int end) {
        reverse(array, i, mid - 1);
        reverse(array, mid, end);
        reverse(array, i, end);
    }

    private void reverse(int[] array, int begin, int end) {
        while (begin < end) {
            array[begin] ^= array[end];
            array[end] ^= array[begin];
            array[begin] ^= array[end];
            begin++;
            end--;
        }
    }

    private void merge(int[] array, int begin, int mid, int end) {
        // 开启辅助空间复制数组，每次merge都要开启length长
        int[] temp = Arrays.copyOf(array, array.length);
        int left = begin, right = mid, i = begin;
        while (left < mid && right <= end) {
            if (temp[left] <= temp[right]) {
                array[i++] = temp[left++];
            } else {
                array[i++] = temp[right++];
            }
        }
        // 右边right未到end，则为右边后面的数大，原地不动即可，因为temp是从array原数组中复制的
        while (left < mid) {
            array[i++] = temp[left++];
        }
    }

    /**
     * 优化空间，减少复制数组开辟的辅助空间
     *
     * @param array
     * @param begin
     * @param mid
     * @param end
     */
    public void mergePlus(int[] array, int begin, int mid, int end) {
        // 开辟begin~end长度的复制空间，同时复制数组
        int[] temp = Arrays.copyOfRange(array, begin, end + 1);
        int i = begin, left, right;
        end -= begin;
        mid -= begin;
        begin = 0;
        left = begin;
        right = mid;
        while (left < mid && right <= end) {
            if (temp[left] <= temp[right]) {
                array[i++] = temp[left++];
            } else {
                array[i++] = temp[right++];
            }
        }
        while (left < mid) {
            array[i++] = temp[left++];
        }
    }


    @Test
    public void test01() {
        int size = 10000; // 10000 && 100000 && 10000000
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        int[] arrays = Arrays.copyOf(array, array.length);
        System.out.println("========不优化空间or优化空间or原地排序========");
        long before = System.currentTimeMillis();
        sortByMerge(array, 0, array.length);
        System.out.println("recuresion time: " + (System.currentTimeMillis() - before));
        long before1 = System.currentTimeMillis();
        sortByMerge1(arrays, 0, array.length);
        System.out.println("norecursion time: " + (System.currentTimeMillis() - before1));
    }

    @Test
    public void test02() {
        int[] array = {100, 78, 78, 78, 98, 98, 12, 78, 65, 3, 14, 99, 45, 23, 78};
        System.out.println(Arrays.toString(array));
        sortByMerge(array, 0, array.length);
        System.out.println(Arrays.toString(array));
        array = new int[]{100, 78, 78, 78, 98, 98, 12, 78, 65, 3, 14, 99, 45, 23, 78};
        sortByMerge1(array, 0, array.length);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void test03() {
        int size = 10000;
        System.out.println("数量级：" + size);
        test04(size);
        test05(size);
        test06(size);
    }

    public void test04(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        int[] arrays = Arrays.copyOf(array, array.length);
        System.out.println("========不优化空间========");
        long before = System.currentTimeMillis();
        mergeByRecursion4(array, 0, array.length - 1);
        System.out.println("recuresion time: " + (System.currentTimeMillis() - before));
        long before1 = System.currentTimeMillis();
        mergeByNoRecursion4(arrays, 0, array.length - 1);
        System.out.println("norecursion time: " + (System.currentTimeMillis() - before1));
    }

    public void test05(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        int[] arrays = Arrays.copyOf(array, array.length);
        System.out.println("========优化空间==========");
        long before = System.currentTimeMillis();
        mergeByRecursion5(array, 0, array.length - 1);
        System.out.println("recuresion time: " + (System.currentTimeMillis() - before));
        long before1 = System.currentTimeMillis();
        mergeByNoRecursion5(arrays, 0, array.length - 1);
        System.out.println("norecursion time: " + (System.currentTimeMillis() - before1));
    }

    public void test06(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        int[] arrays = Arrays.copyOf(array, array.length);
        System.out.println("========原地排序==========");
        long before = System.currentTimeMillis();
        mergeByRecursion(array, 0, array.length - 1);
        System.out.println("recuresion time: " + (System.currentTimeMillis() - before));
        long before1 = System.currentTimeMillis();
        mergeByNoRecursion(arrays, 0, array.length - 1);
        System.out.println("norecursion time: " + (System.currentTimeMillis() - before1));
    }

    private void mergeByRecursion5(int[] array, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) >> 1;
            mergeByRecursion5(array, begin, mid);
            mergeByRecursion5(array, mid + 1, end);
            mergePlus(array, begin, mid + 1, end);
        }
    }

    private void mergeByNoRecursion5(int[] array, int begin, int end) {
        int length = 1, left, mid, right;
        for (; length < end - begin + 1; length *= 2) {
            for (left = begin; left + length <= end; left += 2 * length) {
                mid = left + length >= end ? end : left + length;
                right = mid + length - 1 >= end ? end : mid + length - 1;
                mergePlus(array, left, mid, right);
            }
        }
    }

    private void mergeByRecursion4(int[] array, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) >> 1;
            mergeByRecursion4(array, begin, mid);
            mergeByRecursion4(array, mid + 1, end);
            merge(array, begin, mid + 1, end);
        }
    }

    private void mergeByNoRecursion4(int[] array, int begin, int end) {
        int length = 1, left, mid, right;
        for (; length < end - begin + 1; length *= 2) {
            for (left = begin; left + length <= end; left += 2 * length) {
                mid = left + length >= end ? end : left + length;
                right = mid + length - 1 >= end ? end : mid + length - 1;
                merge(array, left, mid, right);
            }
        }
    }


/*    public void test01(){
        int size = 10000; // 10000 && 100000 && 10000000
        Random random = new Random();
        int[] array=new int[size];
        for(int i=0;i<size;i++){
            array[i]=random.nextInt(10000);
        }
        int[] arrays = Arrays.copyOf(array,array.length);
        System.out.println("========不优化空间or优化空间or原地排序========");
        long before = System.currentTimeMillis();
        sortByMerge(array,0,array.length);
        System.out.println("recuresion time: "+(System.currentTimeMillis()-before));
        long before1 = System.currentTimeMillis();
        sortByMerge1(arrays,0,array.length);
        System.out.println("norecursion time: "+(System.currentTimeMillis()-before1));
    }*/

}
