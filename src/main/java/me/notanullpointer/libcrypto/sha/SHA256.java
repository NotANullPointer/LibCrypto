package me.notanullpointer.libcrypto.sha;

import me.notanullpointer.libcrypto.Hash;

import java.security.NoSuchAlgorithmException;

public class SHA256 {

    private static SHA256 instance;
    private Hash core;
    private static boolean initialized;

    private SHA256() {
        instance = this;
        initialized = true;
        try {
            this.core = new Hash("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void initialize() {
        new SHA256();
    }

    public static byte[] hash(byte[] in) {
        return instance.core.hash(in);
    }

    public static byte[] hashWithSalt(byte[] in, byte[] salt) {
        return instance.core.hashWithSalt(in, salt);
    }

}
