
/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/3 16:04
 **/

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/3 16:04
 **/
@Slf4j
@SpringBootTest
public class Test {

    @org.junit.Test
    public void test() {
        String text = "{\n" + "    \"code\": 200,\n" + "    \"msg\":\"\\成\\功\",\n" + "    \"data\": [\n" + "        {\n"
            + "            \"schoolId\": 1001,\n" + "            \"tid\": 10000,\n" + "            \"tcomCount\": 0,\n"
            + "            \"tbrowseCount\": 0,\n" + "            \"tclassifyId\": 1,\n"
            + "            \"ttitle\": \"世界上真的有修仙者\",\n" + "            \"tlastTime\": 1698027764,\n"
            + "            \"tauthorId\": 1715310059591618560,\n" + "            \"tlikeCount\": 0,\n"
            + "            \"ttypeId\": 1,\n" + "            \"tforwardCount\": 0,\n"
            + "            \"tpubTime\": 1698027764,\n"
            + "            \"tcontext\": \"{\\ncontent:[\\\"我是内容一\\\",\\\"_img\\\",\\\"_img\\\",\\\"我是内容二\\\"]\\n}\"\n"
            + "        }\n" + "    ]\n" + "}";

        SM2 sm2 = SmUtil.sm2();
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println(encryptStr);
        System.out.println(decryptStr);
    }

    @org.junit.Test
    public void test1() {
        String text = "{\n" + "    \"code\": 200,\n" + "    \"msg\":\"\\成\\功\",\n" + "    \"data\": [\n" + "        {\n"
            + "            \"schoolId\": 1001,\n" + "            \"tid\": 10000,\n" + "            \"tcomCount\": 0,\n"
            + "            \"tbrowseCount\": 0,\n" + "            \"tclassifyId\": 1,\n"
            + "            \"ttitle\": \"世界上真的有修仙者\",\n" + "            \"tlastTime\": 1698027764,\n"
            + "            \"tauthorId\": 1715310059591618560,\n" + "            \"tlikeCount\": 0,\n"
            + "            \"ttypeId\": 1,\n" + "            \"tforwardCount\": 0,\n"
            + "            \"tpubTime\": 1698027764,\n"
            + "            \"tcontext\": \"{\\ncontent:[\\\"我是内容一\\\",\\\"_img\\\",\\\"_img\\\",\\\"我是内容二\\\"]\\n}\"\n"
            + "        }\n" + "    ]\n" + "}";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        log.info("pub-->{}", publicKey);
        log.info("pri-->{}", privateKey);

        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println(encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println(decryptStr);
    }

    /**
     * [48, -127, -109, 2, 1, 0, 48, 19, 6, 7, 42, -122, 72, -50, 61, 2, 1, 6, 8, 42, -127, 28, -49, 85, 1, -126, 45, 4,
     * 121, 48, 119, 2, 1, 1, 4, 32, 87, 54, -10, -7, 79, -43, 85, -5, -89, 69, -71, -78, -7, -97, -37, -49, 10, 41, 77,
     * 16, 17, 6, 52, 0, 89, 37, 100, -35, 32, 89, -91, -54, -96, 10, 6, 8, 42, -127, 28, -49, 85, 1, -126, 45, -95, 68,
     * 3, 66, 0, 4, -16, -88, 10, 106, 90, -60, -23, -63, -107, -50, -14, -42, -102, 123, -121, 89, 54, 33, -62, -112,
     * 102, -127, 89, 127, 27, 101, -28, -33, -12, -11, 89, -52, 87, -37, -42, 70, -42, 103, 12, -25, 38, -106, -71,
     * -26, 5, 67, 101, 67, 89, 9, -47, -113, -55, 30, 108, -78, -116, -126, 19, -89, -82, -92, 72, 25]
     */

    /**
     * 生成秘钥
     */
    @org.junit.Test
    public void test2() {
        String text = "{\n" + "    \"code\": 200,\n" + "    \"msg\":\"\\成\\功\",\n" + "    \"data\": [\n" + "        {\n"
            + "            \"schoolId\": 1001,\n" + "            \"tid\": 10000,\n" + "            \"tcomCount\": 0,\n"
            + "            \"tbrowseCount\": 0,\n" + "            \"tclassifyId\": 1,\n"
            + "            \"ttitle\": \"世界上真的有修仙者\",\n" + "            \"tlastTime\": 1698027764,\n"
            + "            \"tauthorId\": 1715310059591618560,\n" + "            \"tlikeCount\": 0,\n"
            + "            \"ttypeId\": 1,\n" + "            \"tforwardCount\": 0,\n"
            + "            \"tpubTime\": 1698027764,\n"
            + "            \"tcontext\": \"{\\ncontent:[\\\"我是内容一\\\",\\\"_img\\\",\\\"_img\\\",\\\"我是内容二\\\"]\\n}\"\n"
            + "        }\n" + "    ]\n" + "}";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        // byte[] pub = [48, 89, 48, 19, 6, 7, 42, -122, 72, -50, 61, 2, 1, 6, 8, 42, -127, 28, -49, 85, 1, -126, 45, 3,
        // 66, 0, 4, 113, -73, 55, -124, 54, 2, 6, -14, 49, -43, 94, -46, 25, 67, -41, 105, -95, 99, -73, -7, 113, -52,
        // -53, -16, -82, -25, 107, 110, -79, -12, -42, 4, -10, -76, -109, 50, 24, 50, 84, -5, -40, -55, -65, 33, 61,
        // 64, -7, 41, -94, -1, 111, -2, -29, -1, 80, -97, -85, -41, -83, -102, 50, -20, -116, 23];

        // 将字节数组转换为十六进制字符串
        String publicKeyHex = Hex.toHexString(publicKey);
        String privateKeyHex = Hex.toHexString(privateKey);

        //3059301306072a8648ce3d020106082a811ccf5501822d03420004f16494746de1bfcda0ed267c214f7440fe32766d302217c303399fbfce220687e6c62f4a38fe9c792018cd9797a3cf4d933a0f0c92cc378169c58e6d072cad40
        log.info("pub-->{}", publicKeyHex);
        String pubKey = "3059301306072a8648ce3d020106082a811ccf5501822d03420004f16494746de1bfcda0ed267c214f7440fe32766d302217c303399fbfce220687e6c62f4a38fe9c792018cd9797a3cf4d933a0f0c92cc378169c58e6d072cad40";
        //308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104209135a0dd817cc935ebcd9d6c0551cffea903ab3fbabc8009b35d2037edd80123a00a06082a811ccf5501822da14403420004f16494746de1bfcda0ed267c214f7440fe32766d302217c303399fbfce220687e6c62f4a38fe9c792018cd9797a3cf4d933a0f0c92cc378169c58e6d072cad40
        log.info("pri-->{}", privateKeyHex);
        String priKey = "308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104209135a0dd817cc935ebcd9d6c0551cffea903ab3fbabc8009b35d2037edd80123a00a06082a811ccf5501822da14403420004f16494746de1bfcda0ed267c214f7440fe32766d302217c303399fbfce220687e6c62f4a38fe9c792018cd9797a3cf4d933a0f0c92cc378169c58e6d072cad40";

        SM2 sm2 = SmUtil.sm2(priKey, pubKey);

        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println(encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd("", KeyType.PrivateKey));
        System.out.println(decryptStr);

    }

}
