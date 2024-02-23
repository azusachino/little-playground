package cn.az.code.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.tomcat.util.threads.VirtualThreadExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liz
 */
@Configuration
@ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
public class FutureDemo {

    public static void main(String[] args) throws Exception {
        // AnnotationConfigApplicationContext applicationContext = new
        // AnnotationConfigApplicationContext();

        // applicationContext.register(FutureDemo.class);

        // applicationContext.refresh();

        // FutureService futureService =
        // applicationContext.getBean(FutureService.class);
        // futureService.transfer("az", "chino",
        // BigDecimal.valueOf(1000)).thenRun(FutureDemo::demo).get(10,
        // TimeUnit.SECONDS);

        // applicationContext.close();

        cf();
    }

    static void demo() {
        ExecutorService service = Executors.newCachedThreadPool();

        Future<Double> future = service.submit(() -> 8.0);
        System.out.println(9.0);

        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e);
        }
    }

    public static void cf() {
        ExecutorService es = new VirtualThreadExecutor("null");
        var cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            return 1;
        }, es);
        var cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            return 2;
        }, es);
        var cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            return 3;
        }, es);

        var list = List.of(cf1, cf2, cf3);

        CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()])).join();
        for (var cf : list) {
            if (cf.isDone()) {
                try {
                    System.out.println(cf.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Bean
    FutureService futureService() {
        return new FutureService();
    }
}
