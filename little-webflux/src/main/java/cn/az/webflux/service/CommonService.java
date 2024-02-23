package cn.az.webflux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

    private final StringRedisTemplate stringRedisTemplate;

    public CommonService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public static final ThreadLocal<String> LOCAL_STRING = ThreadLocal.withInitial(() -> "100000");

    public Mono<Void> doSomethingBad(long milli) {
        return Mono.create(sink -> {
            LOGGER.info("{}", LocalDateTime.now() + Thread.currentThread().getName());
            try {
                Thread.sleep(milli);
            } catch (InterruptedException e) {
                LOGGER.error("error", e);
                Thread.currentThread().interrupt();
            }
        });
    }

    public void someWork(Path path) {
        try (Stream<String> s = Files.lines(path)) {
            s.forEach(LOGGER::info);
        } catch (IOException e) {
            LOGGER.error("error", e);
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOGGER.error("error", e);
        }
    }

    public String get(String k) {
        return this.stringRedisTemplate.opsForValue().get(k);
    }

}
