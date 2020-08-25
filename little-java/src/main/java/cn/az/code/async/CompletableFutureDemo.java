package cn.az.code.async;

import java.util.concurrent.CompletableFuture;

/**
 * @author az
 * @since 08/25/20
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {

        // constructor
        CompletableFuture<String> cfString = new CompletableFuture<>();
        cfString.complete("complete quest");

        // factory method
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "");
        cf1.thenApplyAsync(x -> {
            System.out.println(Thread.currentThread().getName() + x);
            return x;
        }).complete("over");

        CompletableFuture.runAsync(() -> {
        })
                .thenRun(() -> {
                })
                .thenAccept(r -> {
                })
                .thenApply(r -> "")
                .obtrudeException(new RuntimeException("ex"));

        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("");
        })
                .exceptionally(ex -> "")
                .thenApply(r -> "")
                .handle((x, ex) -> x);

        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> "resultA");
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> "resultB");

        cfA.thenAcceptBoth(cfB, (resultA, resultB) -> {
        });
        cfA.thenCombine(cfB, (resultA, resultB) -> "result A + B");
        cfA.runAfterBoth(cfB, () -> {
        });


        CompletableFuture<Void> future =
                CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> "resultA"),
                        CompletableFuture.supplyAsync(() -> 123),
                        CompletableFuture.supplyAsync(Object::new));
        // 所以这里的 join() 将阻塞，直到所有的任务执行结束
        future.join();

        CompletableFuture<Object> future2 =
                CompletableFuture.anyOf(CompletableFuture.supplyAsync(() -> "resultA"),
                        CompletableFuture.supplyAsync(() -> 123),
                        CompletableFuture.supplyAsync(Object::new));
        future2.join();
    }
}
