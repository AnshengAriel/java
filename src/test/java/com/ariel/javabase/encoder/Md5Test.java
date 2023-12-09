package com.ariel.javabase.encoder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 盐值如何传输？
 * 盐值不需隐藏，因为盐值不是用来防止单个密码被破解的，而是为了防止多个密码被破解。
 * 如果没有盐值或者是所有密码盐值都是一样的，破解者只需要跑一遍暴力破解就可以知道数据库里的所有密码对应明文。
 * 而如果每个密码的盐值不一样，那么破解者就需要对每一个密码单独跑一遍暴力破解。
 *
 * 但其实防止固定盐重新生成彩虹表的方法很简单,只要在盐的基础上再加入登录名进行摘要就好了,一般来讲攻击者不会针对每个登录名生成彩虹表,这么干的话其实就是暴力破解了.
 * 但是拖库后至少提供了暴力破解的机会, 因为服务层的暴力破解至少还可以靠访问层的验证码和频率控制来防护. (后端对密码再次处理可以防止暴力破解,因为前端的加密方式攻击者可以看到,但后端的代码是相对隐蔽的.) .
 * 防止暴力破解，一般采用的办法是增加加密或摘要的时间/空间复杂度 比如bcrypt，scrypt 或者摘要几万次之类的。这样大大增加了暴力破解的成本。
 *
 */
public class Md5Test {

    @Test
    public void digest() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 反复调用update输入数据:
        md.update("Hello".getBytes());
        md.update("World".getBytes());
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }

    @Test
    public void digestBySalt() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 反复调用update输入数据:
        md.update("This is a salt".getBytes());
        md.update("Hello".getBytes());
        md.update("World".getBytes());
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }

}
