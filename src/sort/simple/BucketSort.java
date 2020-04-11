package sort.simple;

/**
 * 桶排序，非比较排序
 */
public class BucketSort {
    /**
     * 时间复杂度O(n)
     * 空间复杂度O(k) k：排序元素范围最值
     */
    static final int LENGTH = 10;

    public static void bucketSort(int[] array) {
        int[] bucket = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            bucket[i] = 0;
        }
        for (int i : array) {
            bucket[i]++;
        }
        for (int i : bucket) {
            System.out.print(i + " ");
        }
        System.out.println();
        int i = 0;
        for (int j = 0; j < LENGTH; j++) {
            int k = bucket[j];
            while (k-- > 0) {
                array[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
//		int[] array = {56,78,45,12,23,56,45,78,89,57,12,10,46,45,99,45};
        int[] array = {5, 7, 3, 1, 2, 5, 6, 7, 1, 9, 5, 5, 7, 8, 2};
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        bucketSort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}