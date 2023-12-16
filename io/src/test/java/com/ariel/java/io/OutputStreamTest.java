package com.ariel.java.io;

import org.junit.Test;

import java.io.*;

public class OutputStreamTest {

    @Test
    public void testFileOutputStream() {
        byte[] bytes = "Hello world!".getBytes();
        try (FileOutputStream out = new FileOutputStream("a.txt")) {
            out.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBufferedOutputStream() {
        byte[] bytes = "Hello world!".getBytes();
        try (FileOutputStream out = new FileOutputStream("a.txt")) {
            try (BufferedOutputStream bufOut = new BufferedOutputStream(out)) {
                bufOut.write(bytes, 0, bytes.length);
                bufOut.flush();  // 此方法会在close()方法中自动调用，因此不需要手动调用
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
