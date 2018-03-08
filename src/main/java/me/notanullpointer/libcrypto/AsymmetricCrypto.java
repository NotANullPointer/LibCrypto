package me.notanullpointer.libcrypto;

import javax.crypto.*;
import java.security.*;

public class AsymmetricCrypto {

    private KeyPair keyPair;
    Cipher cipher;

    public AsymmetricCrypto(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
        cipher = Cipher.getInstance(algorithm);
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public void setKeyPair(PrivateKey privateKey, PublicKey publicKey) {
        this.keyPair = new KeyPair(publicKey, privateKey);
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public byte[] encrypt(byte[] in, KeyType keyType) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, keyType.equals(KeyType.KEY_PRIVATE) ? keyPair.getPrivate() : keyPair.getPublic());
        return cipher.doFinal(in);
    }

    public byte[] decrypt(byte[] in, KeyType keyType) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, keyType.equals(KeyType.KEY_PUBLIC) ? keyPair.getPublic() : keyPair.getPrivate());
        return cipher.doFinal(in);
    }


}


