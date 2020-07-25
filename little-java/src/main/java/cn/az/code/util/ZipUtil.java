package cn.az.code.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.log.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author az
 * @since 07/25/20
 */
public class ZipUtil {

    private static final int BUFFER_SIZE = 10 * 1024;

    private static final Log LOG = Log.get(ZipUtil.class);

    private ZipUtil() {
        throw new RuntimeException("util class no instantiation");
    }

    public static void toZip(String srcDir, OutputStream os, boolean keepDirStructure) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(os)) {
            File srcFile = new File(srcDir);
            compress(srcFile, zos, srcFile.getName(), keepDirStructure);
            long end = System.currentTimeMillis();
            LOG.info("压缩完成, 耗时{}秒", end - start);
        } catch (Exception e) {
            throw new RuntimeException("压缩文件时出现了错误");
        }
    }

    public static void toZip(List<File> srcFiles, OutputStream os) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(os)) {
            for (File f : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(f.getName()));
                int len;
                FileInputStream fis = new FileInputStream(f);
                while ((len = fis.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            long end = System.currentTimeMillis();
            LOG.info("压缩完成, 耗时{}秒", end - start);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtil", e);
        }

    }

    private static void compress(File sourceFile, ZipOutputStream zos, String zipFilename, boolean keepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        // only one file
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(zipFilename));
            int len;
            FileInputStream fis = new FileInputStream(sourceFile);
            while ((len = fis.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            fis.close();
        } else {
            File[] files = sourceFile.listFiles();
            if (Objects.isNull(files) || ArrayUtil.isEmpty(files)) {
                if (keepDirStructure) {
                    zos.putNextEntry(new ZipEntry(zipFilename + "/"));
                    // non file exists
                    zos.closeEntry();
                }
            } else {
                for (File f : files) {
                    if (keepDirStructure) {
                        compress(f, zos, zipFilename + "/" + f.getName(), keepDirStructure);
                    } else {
                        compress(f, zos, f.getName(), keepDirStructure);
                    }
                }
            }
        }
    }
}
