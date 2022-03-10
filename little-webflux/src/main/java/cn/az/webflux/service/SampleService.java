package cn.az.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test executor
 *
 * @author az
 * @since 2021-08-26 21:59
 */
@Service
public class SampleService {

    private static final String DESKTOP = "C:\\Users\\pangy\\Desktop";

    @Autowired
    private CommonService commonService;

    public Mono<String> test(long milli) {
        return this.commonService.doSomethingBad(milli)
            .subscribeOn(Schedulers.newBoundedElastic(1, 10, "Custom Scheduler"))
            .thenReturn("ok");
    }

    public Mono<String> upload(FilePart filePart) {
        Path p = createFile(filePart.filename());
        return filePart.transferTo(p)
            .doOnNext(v -> Mono.fromRunnable(() -> this.commonService.someWork(p))
                .subscribeOn(Schedulers.newSingle("uploadExecutor"))
                .subscribe())
            .map(v -> "ok");
    }

    private Path createFile(String filename) {
        Path path = Paths.get(DESKTOP, filename);
        try {
            Files.createFile(Paths.get(DESKTOP, filename));
        } catch (IOException e) {
            throw new RuntimeException("failed to create file", e);
        }
        return path;
    }
}
