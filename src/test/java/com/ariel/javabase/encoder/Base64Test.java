package com.ariel.javabase.encoder;

import org.junit.Test;

import java.util.Arrays;
import java.util.Base64;

public class Base64Test {

    @Test
    public void encodeToString() {
        String text = "中文！";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes());
        System.out.println(encoded);
    }

    @Test
    public void decode() {
        String text = "5Lit5paH77yB";
        String decoded = new String(Base64.getDecoder().decode(text));
        System.out.println(decoded);
    }

    @Test
    public void withoutPadding() {
        // base64编码加不加"="填充都不影响解码，因为填充字符是唯一的
        byte[] input = { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        String b64encoded = Base64.getEncoder().encodeToString(input);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(b64encoded);
        System.out.println(b64encoded2);
        byte[] output = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output));
    }

    @Test
    public void getUrlEncoder() {
        String text = "中文！";
        String encoded = Base64.getUrlEncoder().encodeToString(text.getBytes());
        System.out.println(encoded);
    }
}
