package cn.az.code.lock;

/**
 * @author az
 */
public class SimpleProduce extends Thread {

    private final SimpleService service;

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
