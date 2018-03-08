package me.notanullpointer.libcrypto.rsa;

import me.notanullpointer.libcrypto.AsymmetricCrypto;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class RSACrypto extends AsymmetricCrypto {

    RSACrypto() throws NoSuchPaddingException, NoSuchAlgorithmException {
        super("RSA");
    }

}
