package com.ariel.java.base.encoder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHATest {

    @Test
    public void digest() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // 反复调用update输入数据:
        md.update("Hello".getBytes());
        md.update("World".getBytes());
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }

    @Test
    public void digestBySalt() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // 反复调用update输入数据:
        md.update("This is a salt".getBytes());
        md.update("Hello".getBytes());
        md.update("World".getBytes());
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }


    @Test
    public void digest256() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // 反复调用update输入数据:
        md.update("This is a salt".getBytes());
        md.update("Hello".getBytes());
        md.update("World".getBytes());
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }

    @Test
    public void digest512() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        // 反复调用update输入数据:
        md.update("This is a salt".getBytes());
        md.update("Hello".getBytes());
        md.update("World".getBytes());
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }

}
