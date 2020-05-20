package algorithm.leetcode.swordoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Path_InMatrix {

    private int len;
    private int row;
    private int col;

    public boolean exist_plus(char[][] board, String word) {
        len = word.length();
        if (len == 0) {
            return false;
        }
        row = board.length;
        col = board[0].length;
        char c = word.charAt(0);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == c) {
                    board[i][j] = '0';
                    if (check(board, word, 1, i, j)) {
                        return true;
                    }
                    board[i][j] = c;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, String word, int pos, int i, int j) {
        if (pos >= len) {
            return true;
        }
        char c = word.charAt(pos);

        if (i > 0 && board[i - 1][j] == c) {
            board[i - 1][j] = '0';
            if (check(board, word, pos + 1, i - 1, j)) {
                return true;
            }
            board[i - 1][j] = c;
        }
        if (i < row - 1 && board[i + 1][j] == c) {
            board[i + 1][j] = '0';
            if (check(board, word, pos + 1, i + 1, j)) {
                return true;
            }
            board[i + 1][j] = c;
        }
        if (j > 0 && board[i][j - 1] == c) {
            board[i][j - 1] = '0';
            if (check(board, word, pos + 1, i, j - 1)) {
                return true;
            }
            board[i][j - 1] = c;
        }
        if (j < col - 1 && board[i][j + 1] == c) {
            board[i][j + 1] = '0';
            if (check(board, word, pos + 1, i, j + 1)) {
                return true;
            }
            board[i][j + 1] = c;
        }

        return false;
    }


    /*---------------------------------------------------*/
    List<Pair> road = new ArrayList<>();

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    road.add(new Pair(i, j));
                    if (dfs(board, word, i, j, 1)) {
                        return true;
                    }
                    road.clear();
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int n) {
        System.out.println("(" + i + ", " + j + ")");
        if (n == word.length()) {
            return true;
        }

        // 向上
        if (i > 0 && !isInroad(i - 1, j) && board[i - 1][j] == word.charAt(n)) {
            Pair tmp = new Pair(i - 1, j);
            road.add(tmp);
            if (dfs(board, word, i - 1, j, n + 1)) {
                return true;
            }
            road.remove(tmp);
        }
        // 向下
        if (i + 1 < board.length && !isInroad(i + 1, j) && board[i + 1][j] == word.charAt(n)) {
            Pair tmp = new Pair(i + 1, j);
            road.add(tmp);
            if (dfs(board, word, i + 1, j, n + 1)) {
                return true;
            }
            road.remove(tmp);
        }
        // 向左
        if (j > 0 && !isInroad(i, j - 1) && board[i][j - 1] == word.charAt(n)) {
            Pair tmp = new Pair(i, j - 1);
            road.add(tmp);
            if (dfs(board, word, i, j - 1, n + 1)) {
                return true;
            }
            road.remove(tmp);
        }
        // 向右
        if (j + 1 < board[0].length && !isInroad(i, j + 1) && board[i][j + 1] == word.charAt(n)) {
            Pair tmp = new Pair(i, j + 1);
            road.add(tmp);
            if (dfs(board, word, i, j + 1, n + 1)) {
                return true;
            }
            road.remove(tmp);
        }
        return false;
    }

    private boolean isInroad(int i, int j) {
        for (Pair p :
                road) {
            if (p.a == i && p.b == j) {
                return true;
            }
        }
        return false;
    }

    class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    @Test
    public void test() {
        char[][] board = new char[3][];
        board[0] = new char[]{'A', 'B', 'C', 'E'};
        board[1] = new char[]{'S', 'F', 'E', 'S'};
        board[2] = new char[]{'A', 'D', 'E', 'E'};
        boolean res = exist(board, "ABCESEEEFS");
        System.out.println(res);
/*        board[0] = new char[]{'A','B','C','E'};
        board[1] = new char[]{'S','F','C','S'};
        board[2] = new char[]{'A','D','E','E'};*/
    }
}
