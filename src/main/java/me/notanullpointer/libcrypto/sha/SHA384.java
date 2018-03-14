package me.notanullpointer.libcrypto.sha;

import me.notanullpointer.libcrypto.Hash;

import java.security.NoSuchAlgorithmException;

public class SHA384 {

    private static SHA384 instance;
    private Hash core;

    private SHA384() {
        instance = this;
        try {
            this.core = new Hash("SHA-384");
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
