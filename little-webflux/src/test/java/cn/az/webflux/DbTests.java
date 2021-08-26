package cn.az.webflux;

import cn.az.webflux.entity.DateTest;
import cn.az.webflux.service.DateTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author az
 * @since 2021-08-05 22:37
 */
@SpringBootTest(classes = LittleWebfluxApplication.class)
public class DbTests {

    @Autowired
    private DateTestService dateTestService;

    @Test
    public void test1() {
        DateTest dt = new DateTest();
        dt.setId(123);
        dt.setAppId("123");
        dt.setDate("2021-08-05");
        dt.setDatetime(LocalDateTime.now());
        dt.setTs((int) (System.currentTimeMillis() / 1000));
        System.out.println(this.dateTestService.save(dt));
    }
}
