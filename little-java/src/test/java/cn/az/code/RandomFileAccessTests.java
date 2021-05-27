package cn.az.code;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author Liz
 * @date 1/13/2020
 */
@SpringBootTest
public class RandomFileAccessTests {

    @Test
    public void test() {
        File file = new File("E://temp//Report_20191016_000000001.csv");
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
