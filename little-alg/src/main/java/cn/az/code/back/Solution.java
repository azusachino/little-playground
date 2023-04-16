package cn.az.code.back;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] { 1, 2, 3 };
        GsonUtil.print(s.fullArrange(nums));
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

}
