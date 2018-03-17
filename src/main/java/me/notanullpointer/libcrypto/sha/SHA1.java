package me.notanullpointer.libcrypto.sha;

import me.notanullpointer.libcrypto.Hash;
import me.notanullpointer.libcrypto.md.MD5;

import java.security.NoSuchAlgorithmException;

public class SHA1 {

    private static SHA1 instance;
    private Hash core;
    private static boolean initialized;

    private SHA1() {
        instance = this;
        initialized = true;
        try {
            this.core = new Hash("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void initialize() {
        new SHA1();
    }

    public static byte[] hash(byte[] in) {
        return instance.core.hash(in);
    }

    public static byte[] hashWithSalt(byte[] in, byte[] salt) {
        return instance.core.hashWithSalt(in, salt);
    }

}
