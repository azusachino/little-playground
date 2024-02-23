package cn.az.code.ios;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Demo for ios
 * 
 * @author az
 */
public class DifferentLevels {

    public static void main(String[] args) {

    }

    void bio() throws IOException {
        try (ServerSocket ss = new ServerSocket(0, 0, InetAddress.getLocalHost())) {
        }
    }

    void nio() throws IOException {
        var addr = new InetSocketAddress(InetAddress.getLocalHost(), 0);
        var sc = ServerSocketChannel.open();

        sc.configureBlocking(false);
        sc.bind(addr);

        Selector selector = Selector.open();
        sc.register(selector, 0, SelectionKey.OP_ACCEPT);

        for (;;) {
            selector.select();
            var keys = selector.selectedKeys();
            for (Iterator<SelectionKey> itr = keys.iterator(); itr.hasNext();) {
                var key = itr.next();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        var clientSocket = sc.accept();
                        System.out.println("receiving" + clientSocket.getRemoteAddress());
                    }
                }
            }
        }

    }

    void aio() throws IOException {
        var server = AsynchronousServerSocketChannel.open();
        var addr = new InetSocketAddress(InetAddress.getLocalHost(), 0);

        server.bind(addr);

        var handler = new CompletionHandler<AsynchronousSocketChannel, Object>() {

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println("Accepted connection from " + result);
                result.read(ByteBuffer.allocate(1024), null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        System.out.println("Received " + result + " bytes");
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("Error: " + exc);
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                throw new UnsupportedOperationException("Unimplemented method 'failed'");
            }

        };
        server.accept(null, handler);

    }

}
