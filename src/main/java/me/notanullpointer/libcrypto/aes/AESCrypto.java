package me.notanullpointer.libcrypto.aes;

import me.notanullpointer.libcrypto.SymmetricCrypto;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

class AESCrypto extends SymmetricCrypto {

    AESCrypto() throws NoSuchPaddingException, NoSuchAlgorithmException {
        super("AES");
    }

}
