package cn.az.code.concurrent;

import java.util.Vector;

public class WaitNotify {

    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>(10);
        int size = 10;
        WaitNotify wn = new WaitNotify();
        new Thread(wn.new Producer(v, size)).start();
        new Thread(wn.new Consumer(v)).start();
    }

    class Producer implements Runnable {

        private Vector<Integer> v;
        private Integer size;

        public Producer(Vector<Integer> v, Integer size) {
            this.v = v;
            this.size = size;
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    System.out.println("producing a product...");
                    produce(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        private void produce(int i) throws InterruptedException {
            while (v.size() == size) {
                synchronized (v) {
                    System.out.println(
                            "Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + v.size());
                    v.wait();
                }
            }
            synchronized (v) {
                v.add(i);
                v.notifyAll();
            }
        }
    }

    class Consumer implements Runnable {

        private Vector<Integer> v;

        public Consumer(Vector<Integer> v) {
            this.v = v;
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    System.out.println("consuming a product...");
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {
            while (this.v.isEmpty()) {
                synchronized (this.v) {
                    System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: "
                            + this.v.size());
                    this.v.wait();
                }
            }
            synchronized (this.v) {
                this.v.remove(0);
                this.v.notifyAll();
            }
        }
    }
}
