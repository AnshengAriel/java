package com.ariel.javabase.encoder;

import org.junit.Test;

/**
 * 密钥交换算法
 */
public class DHTest {

    @Test
    public void test() {
        // Bob和Alice:
        DhPerson bob = new DhPerson("Bob");
        DhPerson alice = new DhPerson("Alice");

        // 各自生成KeyPair:
        bob.generateKeyPair();
        alice.generateKeyPair();

        // 双方交换各自的PublicKey:
        // Bob根据Alice的PublicKey生成自己的本地密钥:
        bob.generateSecretKey(alice.publicKey.getEncoded());
        // Alice根据Bob的PublicKey生成自己的本地密钥:
        alice.generateSecretKey(bob.publicKey.getEncoded());

        // 检查双方的本地密钥是否相同:
        bob.printKeys();
        alice.printKeys();
        // 双方的SecretKey相同，后续通信将使用SecretKey作为密钥进行AES加解密...
    }

}
