package cn.az.code.linked;

import cn.az.code.utils.GsonUtil;

public class Solution2 {

    public static void main(String[] args) {
        var s = new Solution2();
        var head = new ListNode(1);
        head.next = new ListNode(2, new ListNode(2, new ListNode(1)));
        GsonUtil.print(s.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        var fast = head;
        var slow = head;
        if (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        // reverse the left
        slow = reverse(slow);

        var another = head;
        while (slow != null) {
            if (another.val != slow.val) {
                return false;
            }
            slow = slow.next;
            another = another.next;
        }

        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            var next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

}
