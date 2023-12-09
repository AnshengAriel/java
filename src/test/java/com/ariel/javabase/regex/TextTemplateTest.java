package com.ariel.javabase.regex;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class TextTemplateTest {

    @Test
    public void format() {
        String template = asString("text-template-test.txt");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("good", "好");
        String format = TextTemplate.format(template, map);
        System.out.println(format);
    }

    public String asString(String path) {
        try (InputStream in = this.getClass().getResource(path).openStream()) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
