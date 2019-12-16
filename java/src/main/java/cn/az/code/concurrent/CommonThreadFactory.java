package cn.az.code.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * @author: azusachino
 * @version: 2019/12/16
 */
public class CommonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
