package cn.az.code.week02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author az
 * @since 2021-10-24 16:15
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
        this.first = this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        node.next = this.first;
        this.first = node;
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        this.last.next = node;
        this.last = node;
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new Itr<>();
    }

    private static class Node<Item> {
        public Item item;
        public Node<Item> next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    private static class Itr<Item> implements ListIterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Item previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Item item) {

        }

        @Override
        public void add(Item item) {

        }
    }


    // unit testing (required)
    public static void main(String[] args) {

    }
}
