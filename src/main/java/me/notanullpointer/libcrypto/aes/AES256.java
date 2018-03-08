package me.notanullpointer.libcrypto.aes;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class AES256 {

    private static AES256 instance;
    private AESCrypto core;
    private boolean initialized;

    private AES256() {
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

    public static void initialize(SecretKey aes256Key) throws InvalidKeyException {
        if(instance == null) {
            new AES256();
        }
        instance.initialized = true;
        if(aes256Key.getEncoded().length == 32) {
            instance.core.setKey(aes256Key);
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
