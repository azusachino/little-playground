package cn.az.code.future;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @author az
 * @date 2020/3/16
 */
public class CloseableFuture {

    public static void main(String[] args) {
        ExecutorService service = ThreadUtil.newExecutor(10);

        Future<?> future = service.submit(() -> {
            // finish under 3 seconds is ok, otherwise timeout
            sleep(3);
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

    @SneakyThrows
    private static void sleep(long seconds) {
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
