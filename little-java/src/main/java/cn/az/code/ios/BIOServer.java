package cn.az.code.ios;

import java.io.IOException;
import java.net.ServerSocket;

public class BIOServer implements Runnable {

    @Override
    public void run() {
        try (var serverSocket = new ServerSocket()) {
            for (;;) {
                var s = serverSocket.accept();
                s.getOutputStream().write("hello".getBytes());
                s.getOutputStream().flush();
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
