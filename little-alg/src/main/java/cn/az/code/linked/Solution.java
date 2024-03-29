package cn.az.code.linked;

import java.util.List;
import java.util.PriorityQueue;

/**
 * LinkedList Solution
 */
public class Solution {

    public boolean hasCycle(ListNode head) {
        if (null == head) {
            return false;
        }
        var fast = head;
        var slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public ListNode hasCycle2(ListNode head) {
        if (null == head) {
            return null;
        }
        var fast = head;
        var slow = head;
        // fast slow meets at k-m
        var h = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (h != slow) {
                    h = h.next;
                    slow = slow.next;
                }
            }
        }
        return h;
    }

    public ListNode inersection(ListNode a, ListNode b) {
        if (null == a || null == b) {
            return null;
        }
        var ac = a;
        var bc = b;
        while (ac != bc) {
            if (null == ac) {
                ac = b;
            } else {
                ac = ac.next;
            }
            if (null == bc) {
                bc = a;
            } else {
                bc = bc.next;
            }

        }
        return ac;
    }

    ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return p2;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        var dummy = new ListNode(0);
        dummy.next = head;

        var fast = dummy;
        var slow = dummy;

        // find last n+1 node for deletion
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode middleNode(ListNode head) {
        if (null == head) {
            return null;
        }
        var fast = head;
        var slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null && fast.next != null) {
            return slow.next;
        }
        return slow;
    }

    public ListNode partition(ListNode head, int x) {
        var small = new ListNode();
        var big = new ListNode();
        var dummy = head;
        var p1 = small;
        var p2 = big;
        while (dummy != null) {
            var v = dummy.val;
            if (v < x) {
                p1.next = dummy;
                p1 = p1.next;
            } else {
                p2.next = dummy;
                p2 = p2.next;
            }
            // in case of cycle
            var next = dummy.next;
            dummy.next = null;
            dummy = next;
        }
        p1.next = big.next;
        return small.next;
    }

    public ListNode mergeK(List<ListNode> lists) {
        if (lists.isEmpty()) {
            return null;
        }
        ListNode dummy = new ListNode();
        var itr = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a, b) -> a.val - b.val);
        for (var n : lists) {
            if (n != null) {
                pq.offer(n);
            }
        }
        while (!pq.isEmpty()) {
            var tail = pq.poll();
            itr.next = tail;

            if (tail.next != null) {
                pq.offer(tail.next);
            }
            itr = itr.next;
        }
        itr = itr.next;

        return dummy.next;
    }

    ListNode deleteDup(ListNode head) {
        var fast = head;
        var slow = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        prev.next = head;
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            first.next = second.next;
            second.next = first;
            prev.next = second;
            prev = first;
        }
        return dummy.next;
    }

    ListNode deleteDup2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            // 2->2->2->3
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // 3->
            return deleteDup2(head.next);
        } else {
            head.next = deleteDup2(head.next);
            return head;
        }
    }

    boolean checkPalindrome(ListNode head) {
        var fast = head;
        var slow = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            var n = slow.next;
            slow.next = prev;
            n.next = slow;
            slow = n;
        }

        return false;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a,b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    public ListNode reverse(ListNode head) {
        ListNode newHead = null;
        // don't modify the head
        ListNode cur = head;
        while (cur != null) {
            var next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }

    public ListNode reverse(ListNode a, ListNode b) {
        ListNode newHead = null;
        // don't modify the head
        ListNode cur = a;
        while (cur != b) {
            var next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }
}
