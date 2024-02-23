package cn.az.code.learn;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Liz
 */
public class Test {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "222");
        ExecutorService service = Executors.newFixedThreadPool(10);
        Object[] objects = Arrays.asList(1, 2, 3, 4, 5, 6).toArray();

        service.execute(() -> {
            objects.getClass();
        });
    }

    public static int hash(Object key) {
        int h;
        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
            // 2210095
            int s = h >>> 16;
            return h ^ s;
        }
    }
}
