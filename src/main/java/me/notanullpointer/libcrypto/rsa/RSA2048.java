package me.notanullpointer.libcrypto.rsa;

import me.notanullpointer.libcrypto.KeyType;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class RSA2048 {

    private static RSA2048 instance;
    private RSACrypto core;
    private boolean initialized;

    private RSA2048() {
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

    public static void initialize(KeyPair rsa2048keyPair) {
        if(instance == null) {
            new RSA2048();
        }
        instance.initialized = true;
        instance.core.setKeyPair(rsa2048keyPair);
    }

    public static byte[] encrypt(byte[] in, KeyType keyType) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return instance.core.encrypt(in, keyType);
    }

    public static byte[] decrypt(byte[] in, KeyType keyType) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return instance.core.decrypt(in, keyType);
    }

}