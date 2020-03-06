package cn.az.code.lock;

/**
 * @author az
 * @date 2020-03-06
 */
public class SimpleConsume extends Thread{

    private SimpleService service;

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
