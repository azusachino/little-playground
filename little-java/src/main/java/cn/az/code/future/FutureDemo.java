package cn.az.code.future;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Liz
 */
public class FutureDemo {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(FutureDemo.class);

        applicationContext.refresh();

        FutureService futureService = applicationContext.getBean(FutureService.class);
        futureService.transfer("az", "chino", BigDecimal.valueOf(1000))
                .thenRun(FutureDemo::demo)
                .get(10, TimeUnit.SECONDS);

        applicationContext.close();

    }

    static void demo() {
        ExecutorService service = Executors.newCachedThreadPool();

        Future<Double> future = service.submit(() -> 8.0);
        System.out.println(9.0);

        try {
            double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e);
        }
    }

    @Bean
    public FutureService futureService() {
        return new FutureService();
    }
}
