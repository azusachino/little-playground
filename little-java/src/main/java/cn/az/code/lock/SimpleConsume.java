package cn.az.code.lock;

/**
 * @author az
 */
public class SimpleConsume extends Thread {

    private final SimpleService service;

    public SimpleConsume(SimpleService simpleService) {
        this.service = simpleService;
    }

    @Override
    public void run() {
        while (true) {
            service.consume();
        }
    }
}
