package cn.az.webflux.service;

import org.springframework.stereotype.Service;

/**
 * Common Service
 *
 * @author az
 * @since 2021/8/2 22:26
 */
@Service
public class CommonService {

    public final ThreadLocal<String> LOCAL_STRING = ThreadLocal.withInitial(() -> "100000");
}
