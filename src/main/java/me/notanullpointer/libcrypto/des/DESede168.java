package me.notanullpointer.libcrypto.des;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DESede168 {

    private static DESede168 instance;
    private DESCrypto core;
    private boolean initialized;

    private DESede168() {
        instance = this;
        try {
            this.core = new DESCrypto(true);
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            throw new RuntimeException("Algorithm not supported", e.getCause());
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static void initialize(SecretKey desede168Key) throws InvalidKeyException {
        if(instance == null) {
            new DESede168();
        }
        instance.initialized = true;
        //uses 24 bytes instead of 21 wtf
        if(desede168Key.getEncoded().length == 24) {
            instance.core.setKey(desede168Key);
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