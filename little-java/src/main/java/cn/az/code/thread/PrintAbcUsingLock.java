package cn.az.code.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author az
 * @since 11/15/20
 */
public class PrintAbcUsingLock {
  private final Lock lock = new ReentrantLock();
  // 控制打印次数
  private final int times;
  // 当前状态值：保证三个线程之间交替打印
  private int state;

  public PrintAbcUsingLock(int times) {
    this.times = times;
  }

  public static void main(String[] args) {
    PrintAbcUsingLock loopThread = new PrintAbcUsingLock(1);
    new Thread(
            () -> {
              loopThread.printLetter("B", 1);
            },
            "B")
        .start();

    new Thread(
            () -> {
              loopThread.printLetter("A", 0);
            },
            "A")
        .start();

    new Thread(
            () -> {
              loopThread.printLetter("C", 2);
            },
            "C")
        .start();
  }

  private void printLetter(String name, int targetNum) {
    for (int i = 0; i < times; ) {
      lock.lock();
      try {
        if (state % 3 == targetNum) {
          state++;
          i++;
          System.out.print(name);
        }
      } finally {
        lock.unlock();
      }
    }
  }
}
