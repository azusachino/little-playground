package cn.az.code.async;

/**
 * A bean will init async
 */
public class AsyncBean {

    // Mark init method with async
    public void init() {
        AsyncTaskExecutor.submitTask(() -> {

        });
    }
}
