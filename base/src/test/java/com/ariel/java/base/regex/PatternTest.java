package com.ariel.java.base.regex;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    @Test
    public void ignoredCase() {
        printMatchStr("(?i)abc", "pattern-test.txt");
    }

    @Test
    public void or() {
        printMatchStr("abc|ABC", "pattern-test.txt");
    }

    @Test
    public void sub() {
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p = Pattern.compile("\\wo\\w");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String sub = s.substring(m.start(), m.end());
            System.out.println(sub);
        }
    }

    @Test
    public void greedyMode() {
        // 默认贪婪模式，尽可能的匹配更长的字符串，减少匹配次数
        printMatchStr("[\\d]+", "pattern-test.txt");

        // 限定词后面增加?，开启非贪婪模式，尽可能匹配限定符极小值的字符串
        printMatchStr("[0-9]+?", "pattern-test.txt");

        // [0-9]{3,4}?等价于[0-9]{3}  [0-9]+?等价于[0-9]1
        printMatchStr("[0-9]{3,4}?", "pattern-test.txt");
    }

    @Test
    public void matherGroup() {
        // 匿名捕获
        Matcher match1 = match("([0-9]{4})\\d([0-9]{4})", "pattern-test.txt");
        while (match1.find()) {
            System.out.println("match1.group(1) = " + match1.group(1));
            System.out.println("match1.group(2) = " + match1.group(2));
        }
        // 命名捕获
        Matcher match2 = match("(?<a>[0-9]{4})\\d(?<b>[0-9]{4})", "pattern-test.txt");
        while (match2.find()) {
            System.out.println("match2.group(a) = " + match2.group("a"));
            System.out.println("match2.group(b) = " + match2.group("b"));
        }
    }

    @Test
    public void nonMatherGroup() {
        // 非捕获分组之?:?=?!
        printMatchStr("199(?:12|2|3|1)", "pattern-test.txt");

        // 限定词后面增加?，开启非贪婪模式，尽可能匹配限定符极小值的字符串
        printMatchStr("199(?=2|3)", "pattern-test.txt");

        // [0-9]{3,4}?等价于[0-9]{3}  [0-9]+?等价于[0-9]1
        printMatchStr("199(?!2|3)", "pattern-test.txt");
    }

    @Test
    public void multiline1() {
        // ^和$有两种情况：1.默认情况下对于整个输入序列进行匹配，且只匹配一次；2.使用Pattern.MULTILINE多行模式，每一行文本都匹配一次
        Pattern pattern = Pattern.compile("^a[a-z]+", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(asString("pattern-test.txt"));
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void multiline2() {
        // ^和$有两种情况：1.默认情况下对于整个输入序列进行匹配，且只匹配一次；2.使用Pattern.MULTILINE多行模式，每一行文本都匹配一次
        Pattern pattern = Pattern.compile("[a-z]+c$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(asString("pattern-test.txt"));
        while (matcher.find()) {
            System.out.println(matcher.group());
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
        return pattern.matcher(asString(path));
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
