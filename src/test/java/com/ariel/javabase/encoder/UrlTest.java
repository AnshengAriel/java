package com.ariel.javabase.encoder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlTest {

    @Test
    public void encode() throws UnsupportedEncodingException {
        String text = "中文！";
        String encoded = URLEncoder.encode(text, "utf-8");
        System.out.printf("text[%s] utf-8[%s] encoded[%s]%n", text, getUTF8(text), encoded);
    }

    @Test
    public void decode() throws UnsupportedEncodingException {
        String encoded = "%E4%B8%AD%E6%96%87%EF%BC%81";
        String decoded = URLDecoder.decode(encoded, "utf-8");
        System.out.printf("text[%s] encoded[%s]%n", encoded, decoded);
    }

    private String getUTF8(String s) {
        StringBuilder builder = new StringBuilder();
        for (byte b : s.getBytes()) {
            builder.append(Integer.toHexString(b & 0xff));
        }
        return builder.toString();
    }
}
