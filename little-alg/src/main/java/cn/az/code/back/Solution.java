package cn.az.code.back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] { 1, 2, 3 };
        GsonUtil.print(s.fullArrange(nums));
        GsonUtil.print(s.subset(nums));
    }

    public List<List<Integer>> fullArrange(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        backtrack(ret, nums, new LinkedList<>());
        return ret;
    }

    public void backtrack(List<List<Integer>> ret, int[] nums, List<Integer> choice) {
        if (choice.size() == nums.length) {
            ret.add(new LinkedList<>(choice));
            return;
        }
        for (int n : nums) {
            if (choice.contains(n)) {
                continue;
            }
            choice.add(n);
            backtrack(ret, nums, choice);
            choice.remove(Integer.valueOf(n));
        }
    }

    private List<List<char[]>> result = new ArrayList<>();

    public List<List<char[]>> eightQueens() {
        char[] def = new char[] { '.', '.', '.', '.', '.', '.', '.', '.' };
        List<char[]> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(def);
        }
        backtrack1(list, 0);

        return result;
    }

    void backtrack1(List<char[]> board, int row) {
        if (row == board.size()) {
            result.add(new ArrayList<>(board));

            return;
        }
        int len = board.get(row).length;
        for (int i = 0; i < len; i++) {
            if (!isValid(board, row, i)) {
                continue;
            }
            board.get(row)[i] = 'Q';
            backtrack1(board, row + 1);
            board.get(row)[i] = '.';
        }
    }

    public boolean isValid(List<char[]> board, int row, int col) {
        int len = board.size();
        // check col
        for (int k = 0; k < col; k++) {
            if (board.get(row)[k] == 'Q') {
                return false;
            }
        }
        // check up-right
        for (int i = row - 1, j = col + 1; i >= 0 && j < len; i--, j++) {
            if (board.get(i)[j] == 'Q') {
                return false;
            }
        }
        // check up-left
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i)[j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack2(list, nums, 0, new LinkedList<>());
        return list;
    }

    void backtrack2(List<List<Integer>> list, int[] nums, int start, LinkedList<Integer> tmp) {
        list.add(new ArrayList<>(tmp));
        for (int i = start; i < nums.length; i++) {
            tmp.addLast(nums[i]);
            backtrack2(list, nums, i + 1, tmp);
            tmp.removeLast();
        }
    }

    static int L = 9;

    boolean validSodoku(char[][] board) {
        return backtrack3(board, 0, 0);
    }

    boolean backtrack3(char[][] board, int r, int c) {
        if (c == L) {
            return backtrack3(board, r + 1, 0);
        }
        if (r == L) {
            return true;
        }
        if (board[r][c] != '.') {
            return backtrack3(board, r, c + 1);
        }
        for (char i = '1'; i <= '9'; i++) {
            if (!isValid(board, r, c, i)) {
                continue;
            }
            board[r][c] = i;

            if (backtrack3(board, r, c + 1)) {
                return true;
            }
            board[r][c] = '.';

        }
        return false;
    }

    boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < L; i++) {
            if (board[r][i] == n) {
                return false;
            }
            if (board[i][c] == n) {
                return false;
            }
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == n) {
                return false;
            }
        }
        return true;

    }

    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }

}
