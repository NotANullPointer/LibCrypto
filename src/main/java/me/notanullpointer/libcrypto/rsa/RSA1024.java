package me.notanullpointer.libcrypto.rsa;

import me.notanullpointer.libcrypto.KeyType;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class RSA1024 {

    private static RSA1024 instance;
    private RSACrypto core;
    private boolean initialized;

    private RSA1024() {
        instance = this;
        try {
            this.core = new RSACrypto();
        } catch (NoSuchAlgorithmException |NoSuchPaddingException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static void initialize(KeyPair rsa1024keyPair) {
        if(instance == null) {
            new RSA1024();
        }
        instance.initialized = true;
        instance.core.setKeyPair(rsa1024keyPair);
    }

    public static byte[] encrypt(byte[] in, KeyType keyType) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return instance.core.encrypt(in, keyType);
    }

    public static byte[] decrypt(byte[] in, KeyType keyType) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return instance.core.decrypt(in, keyType);
    }

}