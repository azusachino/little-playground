package cn.az.code.file;

import cn.hutool.core.date.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author az
 * @since 5/27/2021 22:34
 */
public class FileRead {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileRead.class);

    public static void main(String[] args) {
//        String file = "E:\\Projects\\project-github\\little-playground\\logs\\info.log";

        String file = "C:\\ProgramData\\chocolatey\\logs\\chocolatey.log";
        StopWatch stopWatch = StopWatch.create("1");

        run(() -> bufferedRead(file), stopWatch);
        run(() -> streamRead(file), stopWatch);
        run(() -> scanRead(file), stopWatch);

        LOGGER.info(stopWatch.prettyPrint());
    }

    public static void bufferedRead(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
//                LOGGER.info(line);
            }
        } catch (IOException e) {
            LOGGER.error("错误", e);
        }
    }

    public static void streamRead(String file) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            System.out.println(stream.count());
        } catch (IOException e) {
            LOGGER.error("错误", e);
        }
    }

    public static void scanRead(String file) {
        try (Scanner scan = new Scanner(new File(file))) {
            while (scan.hasNextLine()) {
                scan.nextLine();
//                LOGGER.error(line);
            }
        } catch (IOException e) {
            LOGGER.error("错误", e);
        }
    }

    public static void run(Runnable r, StopWatch sw) {

        sw.start();
        r.run();
        sw.stop();
    }
}
