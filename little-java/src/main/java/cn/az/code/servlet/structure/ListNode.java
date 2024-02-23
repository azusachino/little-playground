package cn.az.code.servlet.structure;

/**
 * @author Liz
 * @version 2019/11/27
 */
public class ListNode<T> {

    private T val;
    private ListNode<T> prev;
    private ListNode<T> next;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }


}
