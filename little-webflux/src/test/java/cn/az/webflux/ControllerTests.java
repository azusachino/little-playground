package cn.az.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Tests
 *
 * @author az
 * @since 2021/8/2 22:38
 */
@SpringBootTest(args = "--HOST_IP=127.0.0.1", properties = "spring.profiles.active=dev")
class ControllerTests {

    @Test
    void test() {

    }
}
