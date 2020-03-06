package cn.az.code.lock;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author az
 * @date 2020-03-06
 */
public class SimpleTest {
    public static void main(String[] args) {
        SimpleService simpleService = new SimpleService();
        ExecutorService service = ThreadUtil.newExecutor(2);
        SimpleProduce produce = new SimpleProduce(simpleService);
        SimpleConsume consume = new SimpleConsume(simpleService);
        service.execute(produce);
        service.execute(consume);
    }
}
