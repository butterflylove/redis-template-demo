package com.example.boottest.util;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zhangtianlong on 20/2/16.
 * 文件压缩,解压工具类
 */
public class CompressUtil {
    private static final Logger logger = LoggerFactory.getLogger(CompressUtil.class);

    /**
     * 使用gzip进行文件压缩
     * 如果compressedFilePath已存在,则会覆盖
     * 文件的目录必须存在,否则抛异常
     */
    public static void compressByGzip(String sourceFilePath, String compressedFilePath) {
        try (InputStream in = Files.newInputStream(Paths.get(sourceFilePath));
             OutputStream fout = Files.newOutputStream(Paths.get(compressedFilePath));
             BufferedOutputStream out = new BufferedOutputStream(fout);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(out)) {
            final byte[] buffer = new byte[1000];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                gzOut.write(buffer, 0, n);
            }
        } catch (IOException e) {
            logger.error("file compress failed, source:{}, dest:{}", sourceFilePath, compressedFilePath, e);
            throw new RuntimeException("文件压缩失败", e);
        }
    }

    /**
     * 使用gzip解压文件
     * 如果decompressedFilePath已存在,则覆盖
     * 文件的目录必须存在,否则抛异常
     */
    public static void decompressByGzip(String compressedFilePath, String decompressedFilePath) {
        try (InputStream fin = Files.newInputStream(Paths.get(compressedFilePath));
             BufferedInputStream in = new BufferedInputStream(fin);
             OutputStream out = Files.newOutputStream(Paths.get(decompressedFilePath));
             GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in)) {
            final byte[] buffer = new byte[1000];
            int n = 0;
            while (-1 != (n = gzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            logger.error("file decompress failed, compressedFile:{}, decompressedFile:{}",
                    compressedFilePath, decompressedFilePath, e);
            throw new RuntimeException("文件解压失败", e);
        }
    }

    public static void main(String[] args) throws Exception {
        compressByGzip("/Users/zhangtianlong/antx.properties", "/Users/zhangtianlong/test/antx.properties.gz");
//        decompressByGzip("/Users/zhangtianlong/antx.properties2.gz", "/Users/zhangtianlong/test/antx.properties2");
    }
}
