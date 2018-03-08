package me.notanullpointer.libcrypto.aes;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES128 {

    private static AES128 instance;
    private AESCrypto core;
    private boolean initialized;

    private AES128() {
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

    public static void initialize(SecretKey aes128Key) throws InvalidKeyException {
        if(instance == null) {
            new AES128();
        }
        instance.initialized = true;
        if(aes128Key.getEncoded().length == 16) {
            instance.core.setKey(aes128Key);
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
