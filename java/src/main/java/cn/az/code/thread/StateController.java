package cn.az.code.thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * @author az
 * @date 2020/3/1
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

    @RequestMapping("/stat")
    public Integer stat() {
        return set.stream().map(Val::get).reduce(Integer::sum).get();
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        _add();
        return 1;
    }
}
