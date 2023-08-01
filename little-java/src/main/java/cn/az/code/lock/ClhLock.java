package cn.az.code.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.lang.NonNull;

import lombok.extern.slf4j.Slf4j;

/**
 * @author az
 * @since 2020-03-31
 */
@Slf4j
public class ClhLock implements Lock {

    // 1.1 SMP(Symmetric Multi-Processor)
    // 对称多处理器结构，它是相对非对称多处理技术而言的、应用十分广泛的并行技术。在这种架构中，一台计算机由多个CPU组成，并共享内存和其他资源，所有的CPU都可以平等地访问内存、I/O和外部中断。
    // 虽然同时使用多个CPU，但是从管理的角度来看，它们的表现就像一台单机一样。操作系统将任务队列对称地分布于多个CPU之上，从而极大地提高了整个系统的数据处理能力。
    // 但是随着CPU数量的增加，每个CPU都要访问相同的内存资源，共享资源可能会成为系统瓶颈，导致CPU资源浪费。

    // 1.2 NUMA(Non-Uniform Memory Access)
    // 非一致存储访问，将CPU分为CPU模块，每个CPU模块由多个CPU组成，并且具有独立的本地内存、I/O槽口等，模块之间可以通过互联模块相互访问，
    // 访问本地内存（本CPU模块的内存）的速度将远远高于访问远地内存(其他CPU模块的内存)的速度，这也是非一致存储访问的由来。NUMA较好地解决SMP的扩展问题，
    // 当CPU数量增加时，因为访问远地内存的延时远远超过本地内存，系统性能无法线性增加。

    // SMP：对称多处理器结构
    // NUMA：非一致存储访问

    // CLH是一种基于单向链表的高性能、公平的自旋锁。申请加锁的线程通过前驱节点的变量进行自旋。在前置节点解锁后，当前节点会结束自旋，并进行加锁。
    // 在SMP架构下，CLH更具有优势。在NUMA架构下，如果当前节点与前驱节点不在同一CPU模块下，跨CPU模块会带来额外的系统开销，而MCS锁更适用于NUMA架构。

    private final AtomicReference<ClhNode> tail;
    private final ThreadLocal<ClhNode> threadLocal;

    public ClhLock() {
        this.tail = new AtomicReference<>();
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * Acquires the lock.
     *
     * <p>
     * If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     *
     * <p>
     * <b>Implementation Considerations</b>
     *
     * <p>
     * A {@code Lock} implementation may be able to detect erroneous use
     * of the lock, such as an invocation that would cause deadlock, and
     * may throw an (unchecked) exception in such circumstances. The
     * circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     */
    @Override
    public void lock() {
        ClhNode cur = threadLocal.get();
        if (cur == null) {
            cur = new ClhNode();
            threadLocal.set(cur);
        }

        ClhNode prev = tail.getAndSet(cur);
        if (prev != null) {
            while (prev.getLocked()) {
                log.info("prev is locked");
            }
        }
    }

    /**
     * Acquires the lock unless the current thread is
     * {@linkplain Thread#interrupt interrupted}.
     *
     * <p>
     * Acquires the lock if it is available and returns immediately.
     *
     * <p>
     * If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until
     * one of two things happens:
     *
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported.
     * </ul>
     *
     * <p>
     * If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring the
     * lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>
     * <b>Implementation Considerations</b>
     *
     * <p>
     * The ability to interrupt a lock acquisition in some
     * implementations may not be possible, and if possible may be an
     * expensive operation. The programmer should be aware that this
     * may be the case. An implementation should document when this is
     * the case.
     *
     * <p>
     * An implementation can favor responding to an interrupt over
     * normal method return.
     *
     * <p>
     * A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would
     * cause deadlock, and may throw an (unchecked) exception in such
     * circumstances. The circumstances and the exception type must
     * be documented by that {@code Lock} implementation.
     */
    @Override
    public void lockInterruptibly() {

    }

    /**
     * Acquires the lock only if it is free at the time of invocation.
     *
     * <p>
     * Acquires the lock if it is available and returns immediately
     * with the value {@code true}.
     * If the lock is not available then this method will return
     * immediately with the value {@code false}.
     *
     * <p>
     * A typical usage idiom for this method would be:
     * 
     * <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // manipulate protected state
     *   } finally {
     *     lock.unlock();
     *   }
     * } else {
     *   // perform alternative actions
     * }}</pre>
     * <p>
     * This usage ensures that the lock is unlocked if it was acquired, and
     * doesn't try to unlock if the lock was not acquired.
     *
     * @return {@code true} if the lock was acquired and
     *         {@code false} otherwise
     */
    @Override
    public boolean tryLock() {
        return false;
    }

    /**
     * Acquires the lock if it is free within the given waiting time and the
     * current thread has not been {@linkplain Thread#interrupt interrupted}.
     *
     * <p>
     * If the lock is available this method returns immediately
     * with the value {@code true}.
     * If the lock is not available then
     * the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of three things happens:
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported; or
     * <li>The specified waiting time elapses
     * </ul>
     *
     * <p>
     * If the lock is acquired then the value {@code true} is returned.
     *
     * <p>
     * If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring
     * the lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>
     * If the specified waiting time elapses then the value {@code false}
     * is returned.
     * If the time is
     * less than or equal to zero, the method will not wait at all.
     *
     * <p>
     * <b>Implementation Considerations</b>
     *
     * <p>
     * The ability to interrupt a lock acquisition in some implementations
     * may not be possible, and if possible may
     * be an expensive operation.
     * The programmer should be aware that this may be the case. An
     * implementation should document when this is the case.
     *
     * <p>
     * An implementation can favor responding to an interrupt over normal
     * method return, or reporting a timeout.
     *
     * <p>
     * A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would cause
     * deadlock, and may throw an (unchecked) exception in such circumstances.
     * The circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return {@code true} if the lock was acquired and {@code false}
     *         if the waiting time elapsed before the lock was acquired
     */
    @Override
    public boolean tryLock(long time, @NonNull TimeUnit unit) {
        return false;
    }

    /**
     * Releases the lock.
     *
     * <p>
     * <b>Implementation Considerations</b>
     *
     * <p>
     * A {@code Lock} implementation will usually impose
     * restrictions on which thread can release a lock (typically only the
     * holder of the lock can release it) and may throw
     * an (unchecked) exception if the restriction is violated.
     * Any restrictions and the exception
     * type must be documented by that {@code Lock} implementation.
     */
    @Override
    public void unlock() {
        ClhNode cur = threadLocal.get();
        threadLocal.remove();

        if (cur == null || !cur.getLocked()) {
            return;
        }

        if (!tail.compareAndSet(cur, null)) {
            cur.setLocked(false);
        }
    }

    /**
     * Returns a new {@link Condition} instance that is bound to this
     * {@code Lock} instance.
     *
     * <p>
     * Before waiting on the condition the lock must be held by the
     * current thread.
     * A call to {@link Condition#await()} will atomically release the lock
     * before waiting and re-acquire the lock before the wait returns.
     *
     * <p>
     * <b>Implementation Considerations</b>
     *
     * <p>
     * The exact operation of the {@link Condition} instance depends on
     * the {@code Lock} implementation and must be documented by that
     * implementation.
     *
     * @return A new {@link Condition} instance for this {@code Lock} instance
     * @throws UnsupportedOperationException if this {@code Lock}
     *                                       implementation does not support
     *                                       conditions
     */
    @Override
    @NonNull
    public Condition newCondition() {
        Lock lock = new ReentrantLock();
        return lock.newCondition();
    }

    static class ClhNode {
        private volatile Boolean locked = true;

        public boolean getLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }
    }
}
