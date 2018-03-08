package me.notanullpointer.libcrypto;

import me.notanullpointer.libcrypto.aes.AES128;
import me.notanullpointer.libcrypto.aes.AES192;
import me.notanullpointer.libcrypto.aes.AES256;
import me.notanullpointer.libcrypto.des.DES56;
import me.notanullpointer.libcrypto.des.DESede168;
import me.notanullpointer.libcrypto.rsa.RSA1024;
import me.notanullpointer.libcrypto.rsa.RSA2048;
import me.notanullpointer.libcrypto.utils.Converter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestingFramework.class)
public class UnitTest {

    private byte[] testBytes = Converter.stringToByteArray("1020DEADC0DE");

    private SecretKey aes128SecretKey;
    private SecretKey aes192SecretKey;
    private SecretKey aes256SecretKey;
    private SecretKey des56SecretKey;
    private SecretKey desede168SecretKey;
    private KeyPair rsa1024keyPair;
    private KeyPair rsa2048keyPair;

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
            byte[][] rsa1024Keys = {Converter.stringToByteArray("30819f300d06092a864886f70d010101050003818d0030818902818100b832631ba0595ad9fffdc4b8c8e2b3b0ea32bc260689722e840fedf07f16121969acc40d801da8b6cdb2c8d96b00690aed9717441da0734955cb3d3f2094b71f93d74fb5fd774c6c2aa39b3fa1377b477659d324fea851f1edd095b14ad2348156a80cdaf4f05a7eb904b468b4cbd29579d516394e4aabb5205ed0c9832dc91b0203010001"), Converter.stringToByteArray("30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100b832631ba0595ad9fffdc4b8c8e2b3b0ea32bc260689722e840fedf07f16121969acc40d801da8b6cdb2c8d96b00690aed9717441da0734955cb3d3f2094b71f93d74fb5fd774c6c2aa39b3fa1377b477659d324fea851f1edd095b14ad2348156a80cdaf4f05a7eb904b468b4cbd29579d516394e4aabb5205ed0c9832dc91b02030100010281800eed4e68cbbad208fe9cd317627834b5ae9503f854e9488e8a773c02145a768706b05087038de3c144b42b60ab32325107660a25a3fcffbabeb024bb7be971598340eb5ad8bd32c155fad0fce59c8533bd050f65860122070573fd7d399d8ccb6f54352304ce3d097c0238befc778a894c7d133881b4bc9ce71b9251692ce5a1024100f8016a3fdd1d7e0ce71493d2eee5bdd54e0308d7665585aa062e37a921b4aba97d49aeadf641a47ba78c5b86ed08a5a6c480f232025d28909c5bf493e08bf571024100be22695a5b8a5ea9a6f18e1d477be09eed2828b3cfd20048821ee01e47bc00a01f2ea02736a1e599beb8c1b600b6292e305eb600fc55b59442d1c52b7d36714b024100eb2c5c568714ea6ccdcbc389a8248f68effb0cad454b1fdee4d17ad29535bffa8ee60a10c88de2ada769ff78e8d8e44c49d684d44b4d8fa3e1c189219932dee102406afc9805950ee637564e03e6abcba3d829a0784ebdb8f019dcc61cdc4d0717df01cccda76311744dafc8ec8f4a153654dc38def6ed06197784a5dee99aba60c302403848a776df694067548b53ffbce21e4a30d0c73dbbf283f9686a3e2384041d94551f8d31aeac20a03a5d5ebe95ba87995705e635ca68c5bfa1528ee977d16fb8")};
            rsa1024keyPair = Converter.byteArrayToKeyPair(rsa1024Keys);
            byte[][] rsa2048Keys = {Converter.stringToByteArray("30820122300d06092a864886f70d01010105000382010f003082010a02820101008bdcb862387b0bce07d0f1456fff5dae91e120e04f4d553cd56c45b3c73f42cab9ae97a91e57feb3323d2b0ab7cf53077bc1ad9dad43ecc391f273c4514db915edae03b5e18414b13fe80a6d34e6a2a835fa9365e96040d9a2e533f26c8b1c7d844e6923df4d627090f03a7d9d6a8131cf273864d46674aa7fd4d53ad91d3e015c91e8d9192ede4893a1d0a832c68349ad452e6889ef48ec597b02f4f144bb12f9a28e99cd64fa4357af9ecef0fc51bb2bb84ec0d07adcf6af562b856403669e161af91935e0d907cba29877dbe08dfcec9b85cae171025bf96cd34eeea04f09380ddfe1c31e4c7b085a635c87eb0cd8d79664688b99baf5566105a8d6c3a8890203010001"), Converter.stringToByteArray("308204bc020100300d06092a864886f70d0101010500048204a6308204a202010002820101008bdcb862387b0bce07d0f1456fff5dae91e120e04f4d553cd56c45b3c73f42cab9ae97a91e57feb3323d2b0ab7cf53077bc1ad9dad43ecc391f273c4514db915edae03b5e18414b13fe80a6d34e6a2a835fa9365e96040d9a2e533f26c8b1c7d844e6923df4d627090f03a7d9d6a8131cf273864d46674aa7fd4d53ad91d3e015c91e8d9192ede4893a1d0a832c68349ad452e6889ef48ec597b02f4f144bb12f9a28e99cd64fa4357af9ecef0fc51bb2bb84ec0d07adcf6af562b856403669e161af91935e0d907cba29877dbe08dfcec9b85cae171025bf96cd34eeea04f09380ddfe1c31e4c7b085a635c87eb0cd8d79664688b99baf5566105a8d6c3a889020301000102820100774bc10a265aedeac2c7f9c3dc8fd8fc79f9a8ed2b372f294071a5d27e74fb27a7d1c012c48b11f90f35156d82f318431dd049a26093bb73e51d0499c32fa45b8bec0dc629a15b7504a98b9b32b036b57bb54bdc757f6051429c75fea58ed2b24d1404a25069d53966a7417cbbb488964fb7886698b4fb7622ab0b65b2a33aaaf0475903629efc933b11c93ce643cbec503dbb162e7f92dbe1ef8bda23c10fb0a9b1ecb49a3fe776cd4515854f7a3e7a0a0d79d3f4465c07262116fd02e93107dbe8fc1411ca7f630004fb3dd8c34eb5ed751d9013596983bc513aab2ceaf922286b209d8b5f3b5e022e9a3ddb8e9392c3eb37233467eec5cd53895205d781c102818100db8c41ea35fb417bf89a3cae850cdf6369ab69b1de593fb3f1032e6bf7d132d21b8cfc7234cb5d06c9f998b792c0aeb3e2795ac35279d84a23ec9934413116f1cbe35e776ec21d92f6f3c2a28dddc401c16eeb3d7bd0223e20a29b250fab4579a6b679835dd152c1b8c1d5f465cf6b3354f23d300c17c9eb5cffddd1c3aa224d02818100a3157929451efd58733810c4359ba948da6379c5f5797d5c9555207e6cc049fb2d89a97fc47869d298af2da13a5e15b80515d42a1fe441c7013c43d4c8274f19aee8e3ca7909004ef825c30b3bf19273cb724f502780faa79c7fe34add43153015c269509ecc758cfdd6858bca7d29a799d772f980cb3811e322834750c0a52d028180183a220bf6690390ea9e1a94279a840c345cf60ceabc6e8b2ccd6d11fd06d8d4d6b57826222d4ed6b0f31a2727ca9b7cbe412602e7d70a47242fb7b84c795e4ed07106bba73d4486333a33003d277bb9b5dec796c152f67464a58cf94a015c69503c52ac6c6decf3f3af702cc80f5b850e9aaca5e0afbf5879aac28fa347e30102818037745ccfd0c639e5b2c034cb53634c0c0eded6909ee921dcb296d0c6cc9a124e0bed0caafa96eb7d0618434ed8b717718ca12094f6653a8cbdf9cd22f4b091302d8ed9ae86bbadd42c8342b07640adc91c3c118632f8a3c30cef5bcda031686a026c2a3d94d1f782eae9e05d698c67f6aad45d2f4441561e96faac633aad573d02818069062898de6c54c0f24d82a1a2dcf31eddab80bbca8a21c98f6dc2c2b25c8f99ba370969578a9f0b0c213fee07376a0a7f3ed71c2d39b0c9f4da45e61870497ba740e99d9a717de7a867c3f6e8b9d318f1d416bd9bfe8d0bfc8376efba71bbeb522b79a6d0f14ef6fd3fe85bd85764070dd62bb38256e8d2195748d27e206cc1")};
            rsa2048keyPair = Converter.byteArrayToKeyPair(rsa2048Keys);
        } catch (NoSuchAlgorithmException|InvalidKeyException|InvalidKeySpecException e) {
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

    @RepeatedTest(2)
    @DisplayName("RSA 1024 Test")
    public void testRSA1024() throws Exception {
        RSA1024.initialize(rsa1024keyPair);
        byte[] enc = RSA1024.encrypt(testBytes, KeyType.KEY_PRIVATE);
        byte[] dec = RSA1024.decrypt(enc, KeyType.KEY_PUBLIC);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }

    @RepeatedTest(2)
    @DisplayName("RSA 2048 Test")
    public void testRSA2048() throws Exception {
        RSA2048.initialize(rsa2048keyPair);
        byte[] enc = RSA2048.encrypt(testBytes, KeyType.KEY_PRIVATE);
        byte[] dec = RSA2048.decrypt(enc, KeyType.KEY_PUBLIC);
        assertEquals(Converter.byteArrayToString(dec), Converter.byteArrayToString(testBytes));
    }

}
