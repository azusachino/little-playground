package cn.az.code.dubbo.client;

import io.netty.bootstrap.Bootstrap;

public class CustomClient {
    public static void main(String[] args) {
        Bootstrap bootStrap = new Bootstrap();
        try {
            var cf = bootStrap.connect("127.0.0.1", 8081).sync();
            cf.channel().writeAndFlush("hahaha");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
