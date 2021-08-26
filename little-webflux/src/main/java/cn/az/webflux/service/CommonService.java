package cn.az.webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Common Service
 *
 * @author az
 * @since 2021/8/2 22:26
 */
@Service
public class CommonService {

    public final ThreadLocal<String> LOCAL_STRING = ThreadLocal.withInitial(() -> "100000");

    public Mono<Void> doSomethingBad(long milli) {
        return Mono.create(sink -> {
            System.out.println(LocalDateTime.now() + Thread.currentThread().getName());
            try {
                Thread.sleep(milli);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
