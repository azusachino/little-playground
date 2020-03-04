package cn.az.code.thread;

/**
 * @author az
 * @date 2020/3/1
 */
public class Val<T> {
    T v;

    public void set(T va) {
        v = va;
    }

    public T get() {
        return v;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
