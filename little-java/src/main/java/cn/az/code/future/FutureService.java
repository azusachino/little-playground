package cn.az.code.future;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

/**
 * @author az
 * @since 08/05/20
 */
@Service
public class FutureService {

    public CompletableFuture<Void> transfer(String from, String to, BigDecimal amount) {
        return CompletableFuture.runAsync(() -> System.out.println("从" + from + "转账到" + to + "" + amount + "元"))
                .thenRun(() -> System.out.println("completed"));
    }
}
