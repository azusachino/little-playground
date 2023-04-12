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
    public int size;

    public LRU(int len) {
        this.cache = new HashMap<>();
        this.data = new NodeList();
        this.len = len;
        this.size = 0;
    }

    public void put(int key, int val) {
        // check hash
        Node node = this.cache.get(key);
        if (node != null) {
            // move to head
            this.data.removeNode(node);
            this.data.newHead(node);
        } else {
            // check size
            if (this.size >= this.len) {
                // remove the tail
                this.data.removeTail();
                this.size--;
            }
            Node newNode = new Node(key, val);
            // set to head
            this.data.newHead(newNode);
            this.cache.put(key, newNode);
            this.size++;
        }
    }

    public int get(int key) {
        Node node = this.cache.get(key);
        if (node != null) {
            // move to head
            this.data.removeNode(node);
            this.data.newHead(node);
            return node.val;
        }
        return -1;
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

        public Node head;
        public Node tail;

        public NodeList() {
        }

        public void removeTail() {
            if (this.tail != null) {
                if (this.tail.prev != null) {
                    this.tail = this.tail.prev;
                    this.tail.next = null;
                } else {
                    this.head = null;
                    this.tail = null;
                }
            }
        }

        public void newHead(Node node) {
            if (this.head != null) {
                node.next = this.head;
                this.head.prev = node;
                this.head = node;
            } else {
                this.head = node;
                this.tail = node;
            }
        }

        public void removeNode(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }
    }

}
