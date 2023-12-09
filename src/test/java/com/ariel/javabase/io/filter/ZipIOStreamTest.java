package com.ariel.javabase.io.filter;

import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipIOStreamTest {

    @Test
    public void write() {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(getPath()))) {
            out.putNextEntry(new ZipEntry("zip-wirte1.txt"));
            out.write("Hello 1".getBytes());
            out.putNextEntry(new ZipEntry("zip-wirte2.txt"));
            out.write("Hello 2".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void read() {
        try (ZipInputStream in = new ZipInputStream(new FileInputStream(getPath()))) {
            byte[] bytes = new byte[1024];
            int len;
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                long size = entry.getSize(); // zip解压流不知道解压后的文件大小，因此这个值是-1
                while ((len = in.read(bytes)) != -1) {
                    String s = new String(bytes, 0, len);
                    System.out.printf("file[%s] size[%d] content[%s]%n", entry.getName(), size, s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void decompress() throws IOException {
        decompress(getPath(), "D:\\Users");
    }

    private static String getPath() {
        return ZipIOStreamTest.class.getResource("").getFile() + "/zip-stream-test-write.zip";
    }

    public static void decompress(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry entry = zis.getNextEntry();
        while(entry != null) {
            String filePath = destDir + File.separator + entry.getName();
            if(!entry.isDirectory()) {
                // if the entry is a file, extract it
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                byte[] bytesIn = new byte[4096];
                int read = 0;
                while ((read = zis.read(bytesIn)) != -1) {
                    bos.write(bytesIn, 0, read);
                }
                bos.close();
            } else {
                // if the entry is a directory, create the directory
                File dirEntry = new File(filePath);
                dirEntry.mkdirs();
            }
            zis.closeEntry();
            entry = zis.getNextEntry();
        }
        zis.close();
        fis.close();
    }

}
