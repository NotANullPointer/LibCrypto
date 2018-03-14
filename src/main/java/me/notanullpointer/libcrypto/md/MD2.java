package me.notanullpointer.libcrypto.md;

import me.notanullpointer.libcrypto.Hash;

import java.security.NoSuchAlgorithmException;

public class MD2 {

    private static MD2 instance;
    private Hash core;

    private MD2() {
        instance = this;
        try {
            this.core = new Hash("MD2");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public static byte[] hash(byte[] in) {
        return instance.core.hash(in);
    }

    public static byte[] hashWithSalt(byte[] in, byte[] salt) {
        return instance.core.hashWithSalt(in, salt);
    }

}
