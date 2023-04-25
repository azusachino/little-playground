package cn.az.code.ios;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOServer implements Runnable {

    @Override
    public void run() {
        try (var selector = Selector.open();
                var serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            serverSocket.configureBlocking(false);
            // 注册到 Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            for (;;) {
                // blocking select
                selector.select();
                var selectedKeys = selector.selectedKeys();
                var iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    var key = iter.next();
                    try (var c = ((ServerSocketChannel) key.channel()).accept()) {
                        c.write(ByteBuffer.wrap("hello".getBytes()));
                        c.close();
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
