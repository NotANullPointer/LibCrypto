package me.notanullpointer.libcrypto.aes;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES192 {

    private static AES192 instance;
    private AESCrypto core;
    private boolean initialized;

    private AES192() {
        instance = this;
        try {
            this.core = new AESCrypto();
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static void initialize(SecretKey aes192Key) throws InvalidKeyException {
        if(instance == null) {
            new AES192();
        }
        instance.initialized = true;
        if(aes192Key.getEncoded().length == 24) {
            instance.core.setKey(aes192Key);
        } else {
            throw new InvalidKeyException("Invalid keysize");
        }
    }

    public static byte[] encrypt(byte[] in) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return instance.core.encrypt(in);
    }

    public static byte[] decrypt(byte[] in) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return instance.core.decrypt(in);
    }


}
