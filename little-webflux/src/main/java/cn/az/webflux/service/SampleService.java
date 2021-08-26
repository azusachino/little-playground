package cn.az.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * TODO
 *
 * @author az
 * @since 2021-08-26 21:59
 */
@Service
public class SampleService {

    @Autowired
    private CommonService commonService;

    public Mono<String> test(long milli) {
        return this.commonService.doSomethingBad(milli)
            .subscribeOn(Schedulers.newElastic("Custom Scheduler"))
            .thenReturn("ok");
    }
}
