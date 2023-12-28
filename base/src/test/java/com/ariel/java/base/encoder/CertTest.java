package com.ariel.java.base.encoder;

import com.ariel.java.base.jvm.Main;
import org.junit.Test;

import javax.crypto.Cipher;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Scanner;

/**
 * 数字证书
 */
public class CertTest {

    @Test
    public void createCertFile() throws IOException, InterruptedException {
        // 生成数字证书命令，需要刷新IDEA
        String command = "keytool -storepass 123456 -genkeypair -keyalg RSA -keysize 1024 -sigalg SHA1withRSA -validity 3650 " +
                "-alias mycert -keystore my.keystore -dname \"CN=www.sample.com, OU=sample, O=sample, L=BJ, ST=BJ, C=CN\"";
        Process process = Runtime.getRuntime().exec("cmd");
        try (PrintWriter writer = new PrintWriter(process.getOutputStream())) {
            writer.println("cd src/test/resources/com/ariel/javabase/encoder");
            writer.flush();
            writer.println(command);
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Thread.sleep(5000);
            process.destroy();
        }
    }

    @Test
    public void doit() throws Exception {
        byte[] message = "Hello, use X.509 cert!".getBytes("UTF-8");
        // 读取KeyStore:
        KeyStore ks = loadKeyStore("my.keystore", "123456");
        // 读取私钥:
        PrivateKey privateKey = (PrivateKey) ks.getKey("mycert", "123456".toCharArray());
        // 读取证书:
        X509Certificate certificate = (X509Certificate) ks.getCertificate("mycert");
        // 加密:
        byte[] encrypted = encrypt(certificate, message);
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
        // 解密:
        byte[] decrypted = decrypt(privateKey, encrypted);
        System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
        // 签名:
        byte[] sign = sign(privateKey, certificate, message);
        System.out.println(String.format("signature: %x", new BigInteger(1, sign)));
        // 验证签名:
        boolean verified = verify(certificate, message, sign);
        System.out.println("verify: " + verified);
    }

    @Test
    public void privateKey() throws Exception {
        // 读取KeyStore:
        KeyStore ks = loadKeyStore("my.keystore", "123456");
        // 读取私钥:
        PrivateKey privateKey = (PrivateKey) ks.getKey("mycert", "123456".toCharArray());
        String keyStr = new BigInteger(privateKey.getEncoded()).toString(16);
        System.out.println(privateKey.getAlgorithm());
        System.out.println(privateKey.getFormat());
        System.out.println(keyStr);
        System.out.println(keyStr.length());
    }

    KeyStore loadKeyStore(String keyStoreFile, String password) {
        try (InputStream input = this.getClass().getResourceAsStream(keyStoreFile)) {
            if (input == null) {
                throw new RuntimeException("file not found in classpath: " + keyStoreFile);
            }
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(input, password.toCharArray());
            return ks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static byte[] encrypt(X509Certificate certificate, byte[] message) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(certificate.getPublicKey().getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());
        return cipher.doFinal(message);
    }

    static byte[] decrypt(PrivateKey privateKey, byte[] data) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    static byte[] sign(PrivateKey privateKey, X509Certificate certificate, byte[] message)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    static boolean verify(X509Certificate certificate, byte[] message, byte[] sig) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initVerify(certificate);
        signature.update(message);
        return signature.verify(sig);
    }

}
