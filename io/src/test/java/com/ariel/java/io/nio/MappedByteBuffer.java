package com.ariel.java.io.nio;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer API
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230628163111.java' style='color:green;font-weight:bold;'>MappedByteBuffer API</a>
 */
public class MappedByteBuffer {

    @Test
    public void test() throws IOException {
        File file = new File("src/test/resources/nio_buffer/a.txt");
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            Assert.assertTrue(newFile);
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        java.nio.MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        // 将堆外内存映射到一个数组，修改数组就相当于修改文件，避免了操作系统的反复读写操作。
        mappedByteBuffer.put(0, ((byte) 'b'));

        fileChannel.close();
        randomAccessFile.close();
    }
}
