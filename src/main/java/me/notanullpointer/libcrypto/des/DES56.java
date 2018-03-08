package me.notanullpointer.libcrypto.des;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DES56 {

    private static DES56 instance;
    private DESCrypto core;
    private boolean initialized;

    private DES56() {
        instance = this;
        try {
            this.core = new DESCrypto(false);
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static void initialize(SecretKey des56Key) throws InvalidKeyException {
        if(instance == null) {
            new DES56();
        }
        instance.initialized = true;
        //uses 8 bytes wtf
        if(des56Key.getEncoded().length == 8) {
            instance.core.setKey(des56Key);
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