package cn.az.code.concurrent;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: azusachino
 * @version: 2019/12/16
 */
@Data
@NoArgsConstructor
public class ThreadTask implements Runnable,Comparable<ThreadTask>{

    private int priority;

    @Override
    public int compareTo(ThreadTask o) {
        return this.priority > o.priority ? -1: 1;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);

            System.out.println("priority: " + this.priority + ", ThreadName: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
