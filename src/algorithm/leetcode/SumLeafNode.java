package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SumLeafNode
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/10/29 10:34
 * @Version 1.0
 **/
public class SumLeafNode {
    int res = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res += root.val;
            return;
        }
        if (root.left != null) {
            root.left.val += root.val * 10;
            dfs(root.left);
        }
        if (root.right != null) {
            root.right.val += root.val * 10;
            dfs(root.right);
        }
    }

    /**
     * 机器人移动，不可碰障碍
     *
     * @param command
     * @param obstacles
     * @param x
     * @param y
     * @return
     */
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int xs = 0, ys = 0, tmp = Integer.MAX_VALUE;
        List<Pair> list = new ArrayList<>(command.length());
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'U') ys++;
            else xs++;
            list.add(new Pair(xs, ys));
        }
        for (int[] arr :
                obstacles) {
            int i = arr[0] / xs, j = arr[1] / ys;
            if (i == j + 1) {
                i = arr[0] % xs + xs;
                j = arr[1] % ys;
            } else if (i + 1 == j) {
                i = arr[0] % xs;
                j = arr[1] % ys + ys;
            } else if (i == j) {
                i = arr[0] % xs;
                j = arr[1] % ys;
            } else {
                continue;
            }
            tmp = Math.min(cmp(i, j, list), tmp);
        }
        int i = x / xs, j = y / ys;
        if (i == j + 1) {
            x = x % xs + xs;
            y = y % ys;
        } else if (i + 1 == j) {
            x = i % xs;
            y = y % ys + ys;
        } else if (i == j) {
            x = x % xs;
            y = y % ys;
        } else {
            return false;
        }
        int t = cmp(x, y, list);
        return t < list.size() && t < tmp;
    }

    private int cmp(int i, int j, List<Pair> list) {
        int res = Integer.MAX_VALUE;
        System.out.println(i + ", " + j);
        for (int k = 0; k < list.size(); k++) {
            if (i == list.get(k).x && j == list.get(k).y) {
                res = k;
                break;
            }
        }
        return res;
    }

    class Pair {
        int x, y;

        public Pair() {
        }

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x +
                    ", " + y + ")";
        }
    }

    public boolean robot1(String command, int[][] obstacles, int x, int y) {
        boolean res = true;
        int cur = 0, len = command.length(), i = 0, j = 0;
        while (i != x || j != y) {
            if (isDestroy1(i, j, obstacles)) {
                res = false;
                break;
            }
            switch (command.charAt(cur)) {
                case 'U':
                    j++;
                    break;
                case 'R':
                    i++;
            }
            cur = (cur + 1) % len;
        }
        return res;
    }

    private boolean isDestroy1(int i, int j, int[][] obstacles) {
        boolean res = false;
        for (int[] arr :
                obstacles) {
            if (arr[0] == i && arr[1] == j) {
                res = true;
                break;
            }
        }
        return res;
    }
}
