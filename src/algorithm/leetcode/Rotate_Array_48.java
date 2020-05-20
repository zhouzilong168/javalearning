package algorithm.leetcode;

import java.util.Arrays;

public class Rotate_Array_48 {
    /**
     * 每次旋转一个正方形，正方形边长逐步减2
     */
    // 表示当前旋转正方形的左右边界
    static int left, right;

    /**
     * 顺时针旋转数组90°
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        left = 0;
        right = matrix.length - 1;
        while (right - left > 0) {
            doSide(matrix);
            left++;
            right--;
        }
    }

    /**
     * 旋转边 注意是：left~right,即交换长度为边长-1
     *
     * @param matrix
     */
    private static void doSide(int[][] matrix) {
        int first = right;
        for (int i = left; i < right; i++) {
            int tmp = matrix[left][i];
            matrix[left][i] = matrix[first][left];
            matrix[first][left] = matrix[right][first];
            matrix[right][first--] = matrix[i][right];
            matrix[i][right] = tmp;
            //print(matrix);
        }
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 3, val = 1;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = val++;
            }
        }
        print(matrix);
        rotate(matrix);
        print(matrix);
    }
}
