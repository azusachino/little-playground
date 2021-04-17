package cn.az.code.future;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author az
 */
public class CloseableFuture {

    public static void main(String[] args) {
        ExecutorService service = ThreadUtil.newExecutor(10);

        Future<?> future = service.submit(() -> {
            // finish under 3 seconds is ok, otherwise timeout
            try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            // timeout ,close
            Thread.currentThread().interrupt();
            // attempt to cancel
            future.cancel(true);
        }

        service.shutdown();
    }

    private static void sleep(long seconds) throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        if (Thread.interrupted()) {
            return;
        }
        action();
    }

    public static void action() {
        System.out.printf("Thread[%s] is running...\n", Thread.currentThread().getName());
    }

}
