package cn.az.code.dfs;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    int numDistinctIslands(int[][] grid) {
        Set<String> st = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 999);
                    st.add(sb.toString());
                }
            }
        }

        return st.size();
    }

    void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }
        // flood fill
        // pre-order
        grid[i][j] = 0;
        sb.append(dir).append(",");

        dfs(grid, i - 1, j, sb, 1); // 上
        dfs(grid, i + 1, j, sb, 2); // 下
        dfs(grid, i, j - 1, sb, 3); // 左
        dfs(grid, i, j + 1, sb, 4); // 右
        // post-order
        sb.append(-dir).append(",");
    }
}
