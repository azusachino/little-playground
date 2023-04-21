package cn.az.code.qq;

import java.util.LinkedList;

public class MonoQueue {

    private LinkedList<Integer> queue;

    public MonoQueue() {
        this.queue = new LinkedList<>();
    }

    // no dup
    public void push(int n) {
        // flat all those smaller than current
        while (!this.queue.isEmpty() && this.queue.getLast() <= n) {
            this.queue.removeLast();
        }
        this.queue.offer(n);
    }

    public int getMax() {
        return this.queue.getFirst();
    }

    public void pop(int n) {
        if (this.queue.getFirst() == n) {
            this.queue.removeFirst();
        }
    }

}
