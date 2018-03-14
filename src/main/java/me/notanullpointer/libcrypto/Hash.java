package me.notanullpointer.libcrypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    MessageDigest messageDigest;

    public Hash(String algorithm) throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance(algorithm);
    }

    public byte[] hash(byte[] in) {
        return messageDigest.digest(in);
    }

    public byte[] hashWithSalt(byte[] in, byte[] salt) {
        messageDigest.update(in);
        messageDigest.update(salt, in.length, salt.length);
        return messageDigest.digest();
    }

}
