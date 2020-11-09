package written_interview;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

}

class solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] res = new int[m + 1][n + m + 1];
        System.out.println(solve(res, m, n + m));
    }

    static int solve(int[][] res, int m, int n) {
        if (m >= res.length || n >= res[m].length) {
            return 0;
        }
        if (n < m) {
            res[m][n] = 0;
        } else if (m == 1 || m == n || m + 1 == n) {
            res[m][n] = 1;
        } else if (m == 2) {
            res[m][n] = n / 2;
        } else {
            for (int i = 1; i <= m; i++) {
                res[m][n] += res[i][n - m] == 0 ? solve(res, i, n - m) : res[i][n - m];
            }
        }
        return res[m][n];
    }
}
