package com.ariel.java.base.liaoxuefeng;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringCoderTest {

    @Test
    public void test() {
        print("a");
        print("中");
    }

    private static void print(String s) {
        System.out.println("----------------------" + s + "----------------------");
        System.out.println("US_ASCII = " + getHexStr(s, StandardCharsets.US_ASCII));
        System.out.println("GBK = " + getHexStr(s, Charset.forName("GBK")));
        System.out.println("ISO_8859_1 = " + getHexStr(s, StandardCharsets.ISO_8859_1));
        System.out.println("UTF_8 = " + getHexStr(s, StandardCharsets.UTF_8));
    }

    private static String getHexStr(String s, Charset charset) {
        byte[] bytes = s.getBytes(charset);
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append("0x")
                    .append(Integer.toHexString(b & 0xff))
                    .append(" ");
        }
        return builder.toString();
    }

}
