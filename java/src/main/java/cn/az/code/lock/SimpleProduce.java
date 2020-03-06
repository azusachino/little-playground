package cn.az.code.lock;

/**
 * @author az
 * @date 2020-03-06
 */
public class SimpleProduce extends Thread {

    private SimpleService service;

    public SimpleProduce(SimpleService simpleService) {
        this.service = simpleService;
    }

    @Override
    public void run() {
        while (true) {
            service.produce();
        }
    }
}
