package cn.az.code.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author az
 */
public class SimpleTest {
    public static void main(String[] args) {
        SimpleService simpleService = new SimpleService();
        ExecutorService service = Executors.newFixedThreadPool(2);
        SimpleProduce produce = new SimpleProduce(simpleService);
        SimpleConsume consume = new SimpleConsume(simpleService);
        service.execute(produce);
        service.execute(consume);
    }
}
