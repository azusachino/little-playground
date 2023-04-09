package cn.az.code.linked;

import java.util.PriorityQueue;

public class MergeKLists {
    
    public ListNode mergeKLists(ListNode[] lists) {

        if (null == lists || lists.length == 0) {
            return null;
        }
        ListNode tomb = new ListNode(0);
        ListNode tail = tomb;

        // min-heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a, b) -> a.val - b.val);

        for (ListNode n : lists) {
            if (n != null) {
                pq.offer(n);
            }
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;

            if (node.next != null) {
                pq.offer(node.next);
            }
            tail = tail.next;
        }

        return tomb.next;

    }
}
