package me.notanullpointer.libcrypto.des;

import me.notanullpointer.libcrypto.SymmetricCrypto;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

class DESCrypto extends SymmetricCrypto {

    DESCrypto(boolean isDESede) throws NoSuchPaddingException, NoSuchAlgorithmException {
        super(isDESede ? "DESede" : "DES");
    }

}