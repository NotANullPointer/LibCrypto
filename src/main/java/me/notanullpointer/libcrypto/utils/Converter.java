package me.notanullpointer.libcrypto.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Converter {

    private static KeyFactory keyFactory;

    static {
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm not supported", e);
        }
    }

    public static String byteArrayToString(byte[] in) {
        return DatatypeConverter.printHexBinary(in);
    }

    public static byte[] stringToByteArray(String in) {
        return DatatypeConverter.parseHexBinary(in);
    }

    public static SecretKey byteArrayToSecretKey(byte[] in, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        SecretKeyFactory keyFactory;
        switch(algorithm) {
            case "DES":
                keyFactory = SecretKeyFactory.getInstance(algorithm);
                return keyFactory.generateSecret(new DESKeySpec(in));
            case "DESede":
                keyFactory = SecretKeyFactory.getInstance(algorithm);
                return keyFactory.generateSecret(new DESedeKeySpec(in));
            default:
                return new SecretKeySpec(in, 0, in.length, algorithm);
        }
    }

    public static byte[] secretKeyToByteArray(SecretKey in) {
        return in.getEncoded();
    }

    public static PublicKey byteArrayToPublicKey(byte[] publicKey) throws InvalidKeySpecException {
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));
    }

    public static PrivateKey byteArrayToPrivateKey(byte[] privateKey) throws InvalidKeySpecException {
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
    }

    public static KeyPair byteArrayToKeyPair(byte[] publicKey, byte[] privateKey) throws InvalidKeySpecException {
        return byteArrayToKeyPair(new byte[][]{publicKey, privateKey});
    }

    public static KeyPair byteArrayToKeyPair(byte[][] in) throws InvalidKeySpecException {
        if(in.length < 2)
            throw new InvalidParameterException("Invalid input");
        return new KeyPair(byteArrayToPublicKey(in[0]), byteArrayToPrivateKey(in[1]));
    }

    public static byte[][] keyPairToByteArray(KeyPair in) {
        byte[][] res = new byte[2][];
        res[0] = in.getPublic().getEncoded();
        res[1] = in.getPrivate().getEncoded();
        return res;
    }

}
