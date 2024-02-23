package cn.az.code.netty;

import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

public class NettyServerDemo {

    public static void main(String[] args) {
        EventExecutor executor = new DefaultEventExecutor();
        Promise<Void> promise = new DefaultPromise<>(executor);

        // add listeners
        promise.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("success");
                } else {
                    System.out.println("fail");
                }
            }
        }).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                System.out.println("done");
            }
        });

        executor.submit(() -> {
            try {
                Thread.sleep(1000);
                promise.setFailure(new RuntimeException());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            promise.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
