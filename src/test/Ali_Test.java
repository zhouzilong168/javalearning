package test;

import java.util.Scanner;

public class Ali_Test {

    static int n = 0, m = 0, k = 0, counts = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt() + 1;
        m = in.nextInt();
        k = in.nextInt();
        int[][] arr = new int[n][n];
        boolean[] flags = new boolean[n];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            arr[a][b] = in.nextInt();
            arr[b][a] = arr[a][b];
        }
        dfs(arr, flags, 1, 0);

        System.out.println(counts / 2);
    }

    private static void dfs(int[][] arr, boolean[] flags, int now, int max) {
        for (int i = 1; i < n; i++) {
            if (!flags[i]) {
                flags[i] = true;
                if (arr[now][i] != 0) {
                    max = Math.max(max, arr[now][i]);
                    if (max <= k) {
                        if (max == k) {
                            counts++;
                        }
                        dfs(arr, flags, i, max);
                    } else {
                        max = 0;
                    }
                }
                flags[i] = false;
            }
        }
    }
}