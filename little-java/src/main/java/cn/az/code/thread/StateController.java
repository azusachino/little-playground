package cn.az.code.thread;

import java.util.HashSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 */
@RestController
public class StateController {

    static HashSet<Val<Integer>> set = new HashSet<>();

    static ThreadLocal<Val<Integer>> c = ThreadLocal.withInitial(() -> {
        Val<Integer> v = new Val<>();
        v.set(0);
        set.add(v);
        return v;
    });

    void _add() throws InterruptedException {
        Thread.sleep(100L);
        Val<Integer> v = c.get();
        v.set(v.get() + 1);
    }

    @GetMapping("/stat")
    public Integer stat() {
        return set.stream().map(Val::get).reduce((Integer a, Integer b) -> a + b).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/add")
    public Integer add() throws InterruptedException {
        _add();
        return 1;
    }
}
