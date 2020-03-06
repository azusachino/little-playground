package cn.az.code.concurrent;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadFactory;

/**
 * @author az
 * @version 2019/12/16
 */
public class CommonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(@Nonnull Runnable r) {
        return new Thread(r);
    }
}
