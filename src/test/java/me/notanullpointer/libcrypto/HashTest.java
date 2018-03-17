package me.notanullpointer.libcrypto;

import me.notanullpointer.libcrypto.md.MD2;
import me.notanullpointer.libcrypto.md.MD5;
import me.notanullpointer.libcrypto.sha.SHA1;
import me.notanullpointer.libcrypto.sha.SHA224;
import me.notanullpointer.libcrypto.utils.Converter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestingFramework.class)
public class HashTest {

    private byte[] testBytes = Converter.stringToByteArray("1020DEADC0DE");

    private byte[] expectedMD2Hash = Converter.stringToByteArray("24CA45EB6A46970C43DE3A4591E74B3A");
    private byte[] expectedMD5Hash = Converter.stringToByteArray("D515F0C105E2EE31216A8B217B6A649C");
    private byte[] expectedSHA1Hash = Converter.stringToByteArray("8A393BF85C4D2D41F0BC23D710881A3FF408A96C");
    private byte[] expectedSHA224Hash = Converter.stringToByteArray("3F418A2DBA4E999136BA34FCDA07BF6F2C78623909E833DA7A566A0E");

    @RepeatedTest(2)
    @DisplayName("MD 2 Test")
    public void testMD2() {
        MD2.initialize();
        assertEquals(Converter.byteArrayToString(expectedMD2Hash), Converter.byteArrayToString(MD2.hash(testBytes)));
    }

    @RepeatedTest(2)
    @DisplayName("MD 5 Test")
    public void testMD5() {
        MD5.initialize();
        assertEquals(Converter.byteArrayToString(expectedMD5Hash), Converter.byteArrayToString(MD5.hash(testBytes)));
    }

    @RepeatedTest(2)
    @DisplayName("SHA 1 Test")
    public void testSHA1() {
        SHA1.initialize();
        assertEquals(Converter.byteArrayToString(expectedSHA1Hash), Converter.byteArrayToString(SHA1.hash(testBytes)));
    }

    @RepeatedTest(2)
    @DisplayName("SHA 224 Test")
    public void testSHA224() {
        SHA224.initialize();
        assertEquals(Converter.byteArrayToString(expectedSHA224Hash), Converter.byteArrayToString(SHA224.hash(testBytes)));
    }

}
