package cn.az.code.future;

import java.util.concurrent.*;

/**
 * @author Liz
 * @date 1/9/2020
 */
public class FutureDemo {

    public static void main(String[] args) {
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
            System.out.println(e.toString());
        }
    }
}
