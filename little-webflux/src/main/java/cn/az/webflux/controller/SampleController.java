package cn.az.webflux.controller;

import cn.az.webflux.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * TODO
 *
 * @author az
 * @since 2021-08-26 21:57
 */
@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @PostMapping("/test")
    public Mono<String> testMilli(@RequestBody Map<String, Object> map) {
        long milli = (long) map.get("milli");
        Mono<String> res = this.sampleService.test(milli);
        return res.map(x -> x);
    }

    @PostMapping("/upload")
    public Mono<ResponseEntity<?>> upload(@RequestPart("file") FilePart filePart) {
        return this.sampleService.upload(filePart).map(ResponseEntity::ok);
    }
}
