package cn.az.webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.stream.Stream;

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

    public void someWork(Path path) {
        try (Stream<String> s = Files.lines(path)) {
            s.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
