package com.ariel.javabase.encoder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 小明生成一个随机的AES口令，然后用小红的公钥通过RSA加密这个口令，并发给小红；
 * 小红用自己的RSA私钥解密得到AES口令；
 * 双方使用这个共享的AES口令用AES加密通信。
 */
public class RsaTest {
    
    @Test
    public void test() throws GeneralSecurityException, UnsupportedEncodingException {
        // 明文:
        byte[] plain = "Hello, encrypt use RSA".getBytes("UTF-8");
        // 创建公钥／私钥对:
        RsaPerson alice = new RsaPerson("Alice");
        // 用Alice的公钥加密:
        byte[] pk = alice.getPublicKey();
        System.out.println(String.format("public key: %x", new BigInteger(1, pk)));
        byte[] encrypted = alice.encrypt(plain);
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
        // 用Alice的私钥解密:
        byte[] sk = alice.getPrivateKey();
        System.out.println(String.format("private key: %x", new BigInteger(1, sk)));
        byte[] decrypted = alice.decrypt(encrypted);
        System.out.println(new String(decrypted, "UTF-8"));
    }

    @Test
    public void recover() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] pkData = null;
        byte[] skData = null;
        KeyFactory kf = KeyFactory.getInstance("RSA");
        // 恢复公钥:
        X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pkData);
        PublicKey pk = kf.generatePublic(pkSpec);
        // 恢复私钥:
        PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(skData);
        PrivateKey sk = kf.generatePrivate(skSpec);
    }
    
}
