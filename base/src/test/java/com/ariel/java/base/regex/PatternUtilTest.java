package com.ariel.java.base.regex;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtilTest {

    @Test
    public void sign() {
        printMatchStr("\\d+", "pattern-test.txt"); // 数字
        printMatchStr("\\D+", "pattern-test.txt");
        printMatchStr("\\w+", "pattern-test.txt"); // 数字、字母、下划线
        printMatchStr("\\W+", "pattern-test.txt");
        printMatchStr("\\s+", "pattern-test.txt"); // 空格
        printMatchStr("\\S+", "pattern-test.txt");
        printMatchStr("\\t+", "pattern-test.txt"); // 制表符
        printMatchStr("\\u" + Integer.toHexString('世') + "+", "pattern-test.txt"); // 匹配unicode字符
    }

    @Test
    public void chinese() {
        printMatchStr("[\\u4e00-\\u9fa5]+", "pattern-test.txt");
    }

    @Test
    public void email() {
        printMatchStr("\\w+@\\w+\\.com", "pattern-test.txt");
    }

    @Test
    public void htmlContent() {
        printMatchStr("content=\"[a-z ]+\"", "pattern-test.txt");
    }

    @Test
    public void mobileNumber() {
        printMatchStr("1[0-9]{10}", "pattern-test.txt");
    }

    @Test
    public void number() {
        printMatchStr("[+-]?([1-9]\\d*|0)(\\.\\d+)?", "pattern-test.txt");
    }

    @Test
    public void url() {
        printMatchStr("(\\w+)://(\\w+(.\\w+)*)((/\\w+)+)\\?(.+)", "pattern-test.txt");
    }

    @Test
    public void stammer() {
        // 捋顺结巴语句
        Matcher match = match("(.)\\1+", "pattern-test-stammer.txt");
        while (match.find()) {
            System.out.println(match.group());
        }
        System.out.println("matcher.replaceAll(\"$1\") = " + match.replaceAll("$1"));
    }

    @Test
    public void palindromic() {
        // 查找回文子串abba
        Matcher match = match("(.)(.)\\2\\1", "pattern-test-palindromic.txt");
        while (match.find()) {
            String palindromicStr = match.group();
            if (palindromicStr.charAt(0) != palindromicStr.charAt(1)) {
                System.out.println(palindromicStr);
            }
        }
    }

    public void printMatchStr(String regex, String path) {
        Matcher match = match(regex, path);
        while (match.find()) {
            System.out.println(match.group());
        }
    }

    public Matcher match(String regex, String path) {
        System.out.println("------------------------" + regex + "------------------------");
        Pattern pattern = Pattern.compile(regex);
        try (InputStream in = this.getClass().getResource(path).openStream()) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            return pattern.matcher(new String(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
