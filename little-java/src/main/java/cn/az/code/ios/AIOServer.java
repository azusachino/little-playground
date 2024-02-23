package cn.az.code.ios;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOServer implements Runnable {

    @Override
    public void run() {
        try {
            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
            var ch = new CompletionHandler<AsynchronousSocketChannel, Object>() {

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {

                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                }

            };
            assc.accept(null, ch);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
