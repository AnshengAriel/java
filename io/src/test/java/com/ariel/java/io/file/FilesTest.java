package com.ariel.java.io.file;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilesTest {

    @Test
    public void readAllLines() throws IOException, URISyntaxException {
        URL url = this.getClass().getResource("files-test-read-all-lines.txt");
        List<String> strings = Files.readAllLines(Paths.get(url.toURI()));
        for (String s : strings) {
            System.out.println(s);
        }
    }

}
