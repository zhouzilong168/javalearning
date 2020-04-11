package sort;

import org.junit.Test;

/**
 * @ClassName Heap
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 16:31
 * @Version 1.0
 **/
public class Heap {

     /*
    平均：O(nlogn)
    最好：O(nlogn)
    最坏：O(nlogn)
    空间：O(1)
     */
    // 最好最坏都是logn 不稳定原地排序

    /**
     * 数组下标自0开始
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        maxHeap(array); // 堆化
        for (int n = array.length - 1; n > 0; n--) {
            SortUtils.swap(array, 0, n);
            maxHeapDown(array, 0, n - 1);
        }
    }

    /**
     * 大顶堆堆化
     *
     * @param array
     */
    private static void maxHeap(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            maxHeapDown(array, i, array.length - 1);
        }
    }

    /**
     * 大顶堆向下调整
     *
     * @param array
     * @param i
     */
    private static void maxHeapDown(int[] array, int i, int n) {
        int left = 2 * i + 1, right = 2 * i + 2, max = left;
        if (left > n) {// 左孩子越界 没有孩子
            return;
        }
        if (right > n) {// 右孩子越界 使其也指向左孩子 简化后续代码量
            right = left;
        }
        if (array[left] < array[right]) { // 大孩子
            max = right;
        }
        if (array[i] < array[max]) { // 大孩子大
            SortUtils.swap(array, i, max);
            maxHeapDown(array, max, n);// 递归调整 因为可能调整一个比孩子大的
        }
    }

    @Test
    public void testHead() {
        SortUtils.executeAll(Heap.class, "heapSort");
    }

    // 数组下标0不用 版本
//    public static void heapSort(int[] array){
//        maxHeap(array); // 堆化
//        for (int n = array.length-1; n >1 ; n--) {
//            SortUtils.swap(array,1,n);
//            maxHeapDown(array,1,n-1);
//        }
//    }
//
//    /**
//     * 大顶堆堆化
//     * @param array
//     */
//    private static void maxHeap(int[] array){
//        for(int i=(array.length-1)/2;i>0;i--){
//            maxHeapDown(array,i,array.length-1);
//        }
//    }
//
//    /**
//     * 大顶堆向下调整
//     * @param array
//     * @param i
//     */
//    private static void maxHeapDown(int[] array, int i,int n) {
//        int left=2*i,right=2*i+1,max=left;
//        if(left>n){// 左孩子越界 没有孩子
//            return;
//        }
//        if(right>n){// 右孩子越界 使其也指向左孩子 简化后续代码量
//            right=left;
//        }
//        if(array[left]<array[right]){ // 大孩子
//            max=right;
//        }
//        if(array[i]<array[max]){ // 大孩子大
//            SortUtils.swap(array,i,max);
//            maxHeapDown(array,max,n);// 递归调整 因为可能调整一个比孩子大的
//        }
//    }
}
