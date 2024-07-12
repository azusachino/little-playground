package cn.az.webflux.utils;

import com.fasterxml.uuid.Generators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author haru
 * @date 2024/07/12 15:59
 */
class JsonUtilsTests {

    @Test
    void uuidTest() {
        var uuid = JsonUtils.uuid();
        System.out.println(uuid);
        Assertions.assertNotNull(uuid);

        var new_uuid = Generators.timeBasedEpochGenerator().generate();
        Assertions.assertNotNull(new_uuid);
        System.out.println(new_uuid);
    }
}
