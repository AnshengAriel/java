package com.ariel.javabase.io.file;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

    @Test
    public void showPath() {
        Path path = Paths.get(".", "md");
        System.out.println("path = " + path);
        System.out.println("path.toAbsolutePath() = " + path.toAbsolutePath());
        System.out.println("path.normalize() = " + path.normalize());
    }

    @Test
    public void getParent() {
        Path path = Paths.get("md").toAbsolutePath();
        for (Path p : path) {
            System.out.println(p);
        }
    }

    @Test
    public void getResourcesFile() throws IOException {
        InputStream in = PathTest.class.getResource("getResources.txt").openStream();
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        System.out.println(new String(bytes));
    }

}
