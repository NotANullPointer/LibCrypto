package me.notanullpointer.libcrypto;

import me.notanullpointer.libcrypto.aes.AES128;
import me.notanullpointer.libcrypto.aes.AES192;
import me.notanullpointer.libcrypto.aes.AES256;
import me.notanullpointer.libcrypto.des.DES56;
import me.notanullpointer.libcrypto.des.DESede168;
import me.notanullpointer.libcrypto.utils.Converter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestingFramework.class)
public class SymmetricEncryptionTest {

    private byte[] testBytes = Converter.stringToByteArray("1020DEADC0DE");

    private SecretKey aes128SecretKey;
    private SecretKey aes192SecretKey;
    private SecretKey aes256SecretKey;
    private SecretKey des56SecretKey;
    private SecretKey desede168SecretKey;

    {
        try {
            aes128SecretKey = Converter.byteArrayToSecretKey(
                    Converter.stringToByteArray("132286c3d19181126529e03f51ac0e4c"),
                    "AES");
            aes192SecretKey = Converter.byteArrayToSecretKey(
                    Converter.stringToByteArray("132286c3d19181126529e03f51ac0e4c4f35b0e32daad955"),
                    "AES");
            aes256SecretKey = Converter.byteArrayToSecretKey(
                    Converter.stringToByteArray("132286c3d19181126529e03f51ac0e4c4f35b0e32daad955b167d34fc0d4bac9"),
                    "AES");
            des56SecretKey = Converter.byteArrayToSecretKey(
                    Converter.stringToByteArray("132286c3d1918112"),
                    "DES");
            desede168SecretKey = Converter.byteArrayToSecretKey(
                    Converter.stringToByteArray("132286c3d19181126529e03f51ac0e4c4f35b0e32daad955"),
                    "DESede");
        } catch (NoSuchAlgorithmException |InvalidKeyException |InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    @RepeatedTest(2)
    @DisplayName("AES 128 Test")
    public void testAES128() throws Exception {
        AES128.initialize(aes128SecretKey);
        byte[] enc = AES128.encrypt(testBytes);
        byte[] dec = AES128.decrypt(enc);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }

    @RepeatedTest(2)
    @DisplayName("AES 192 Test")
    public void testAES192() throws Exception {
        AES192.initialize(aes192SecretKey);
        byte[] enc = AES192.encrypt(testBytes);
        byte[] dec = AES192.decrypt(enc);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }

    @RepeatedTest(2)
    @DisplayName("AES 256 Test")
    public void testAES256() throws Exception {
        AES256.initialize(aes256SecretKey);
        byte[] enc = AES256.encrypt(testBytes);
        byte[] dec = AES256.decrypt(enc);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }

    @RepeatedTest(2)
    @DisplayName("DES 56 Test")
    public void testDES56() throws Exception {
        des56SecretKey = KeyGenerator.getInstance("DES").generateKey();
        DES56.initialize(des56SecretKey);
        byte[] enc = DES56.encrypt(testBytes);
        byte[] dec = DES56.decrypt(enc);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }

    @RepeatedTest(2)
    @DisplayName("DESede 168 Test")
    public void testDESede168() throws Exception {
        DESede168.initialize(desede168SecretKey);
        byte[] enc = DESede168.encrypt(testBytes);
        byte[] dec = DESede168.decrypt(enc);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }
}
