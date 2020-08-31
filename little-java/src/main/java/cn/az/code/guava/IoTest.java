package cn.az.code.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * @author az
 * @since 08/31/20
 */
public class IoTest {

    public static void main(String[] args) {

    }

    public void copyFile() throws IOException {
        CharSource cs = Files.asCharSource(File.createTempFile("a", "temp"), Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(File.createTempFile("a", "temp"), Charsets.UTF_8);
        // 实现文件拷贝
        cs.copyTo(charSink);

    }
}
