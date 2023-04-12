package cn.az.code.structs;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    public static void main(String[] args) {
        LRU lfu = new LRU(2);
        lfu.put(1, 2);
        lfu.put(2, 2);
        lfu.put(3, 3);

        System.out.println(lfu.get(1));
    }

    public Map<Integer, Node> cache;
    public NodeList data;
    public int len;

    public LRU(int len) {
        this.cache = new HashMap<>();
        this.data = new NodeList();
        this.len = len;
    }

    public void put(int key, int val) {
        // check hash
        Node node = this.cache.get(key);
        if (node != null) {
            this.deleteKey(key);
            this.addRecently(key, val);
            return;
        }
        if (this.len <= this.data.size) {
            this.remoteLeastRecently();
        }
        this.addRecently(key, val);
    }

    public Integer get(int key) {
        Node node = this.cache.get(key);
        if (node != null) {
            this.makeRecently(key);
            return node.val;
        }
        return null;
    }

    private void makeRecently(int k) {
        Node n = this.cache.get(k);
        this.data.remove(n);
        this.data.addLast(n);
    }

    private void addRecently(int k, int v) {
        Node n = new Node(k, v);
        this.data.addLast(n);
        this.cache.put(k, n);
    }

    private void deleteKey(int k) {
        Node n = this.cache.remove(k);
        this.data.remove(n);
    }

    private void remoteLeastRecently() {
        Node n = this.data.removeFirst();
        this.cache.remove(n.key);
    }

    class Node {
        public int key;
        public int val;
        public Node prev;
        public Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class NodeList {

        public int size;

        public Node head;
        public Node tail;

        public NodeList() {
            this.head = new Node();
            this.tail = new Node();
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
        }

        public void addLast(Node x) {
            x.prev = this.tail.prev;
            x.next = this.tail;
            this.tail.prev.next = x;
            this.tail.prev = x;

            this.size++;
        }

        // must exist in List
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            // help gc
            x = null;
            this.size--;
        }

        public Node removeFirst() {
            if (this.head.next == this.tail) {
                return null;
            }
            Node n = this.head.next;
            this.remove(n);
            return n;
        }

        public int size() {
            return this.size;
        }

    }

}
