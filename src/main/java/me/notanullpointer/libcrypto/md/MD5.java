package me.notanullpointer.libcrypto.md;

import me.notanullpointer.libcrypto.Hash;

import java.security.NoSuchAlgorithmException;

public class MD5 {

    private static MD5 instance;
    private Hash core;

    private MD5() {
        instance = this;
        try {
            this.core = new Hash("MD5");
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
