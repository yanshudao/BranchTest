package io.renren.common.utils;

import sun.security.rsa.RSAUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {

    public static String privateKeyDecrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
//        String outStr = new String(cipher.doFinal(inputByte));
        //当长度过长的时候，需要分割后解密 128个字节
        String outStr = new String(getMaxResultDecrypt(str, cipher));
        return outStr;
    }

    private static byte[] getMaxResultDecrypt(String str, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] inputArray = Base64.getDecoder().decode(str.getBytes("UTF-8"));
        int inputLength = inputArray.length;
        // 最大解密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 128;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(privateKeyDecrypt("fLTFtLivbSw1CdlVeGid/ggnWI1HlvCDSbcoOTsylAN/Ia4SWhGS5n0fUUvMUdZikOtg2wKgBfJ2Sg/N1SvZNHKD4iFZRMXR3gnxUA3W6c88QbuB6j6AVkA6k6ckzL/KRe1ywGNtqu2zcyvr/6xmc26wfIPss0MsrYmvXgQSJ0E=","MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMNpq6tHHxCh7PfR" +
                "PQWhcXrLeqCaqdqWQV8G2GX+mMQ8Ecxm+qB50uq49uUFzjm2Fgg0CjTYu7n6L5iD" +
                "nXe3oUQvn+WA7LK3s/mZskCAB2ZHBKe/IHVrvVSPI93X1TJ+Qfu0yt03hbWwoPYl" +
                "st3pCr/O8TkXQmAgEnGeuYbHwl85AgMBAAECgYEAgcUxDeODS8Zc2g4IGi8mb0el" +
                "vfY4CkNyUir3lnRG+zpxD27rzZpZaStAOsNqOoUjiHsWtBWiRVVSOlRdRF2cJ8Tm" +
                "VeTYVUdiSP+sEcIlt8VnaJoQ5BJ8SRqs/bR4hG9c5gZDZKEAktfmCO0gzk8uCAUP" +
                "usENxcLogEekwVHAfckCQQDjKBwvApLeput/e5zeN6Ltiab0AfM7RUTxxEzrNb2d" +
                "sktv29eOgS/WvAgDT5+Ge1lMAFaFTkJ7QyP5iCLj2csLAkEA3DmzxdlcGAPvhAyh" +
                "+NiQKzeDFavyw6j9lh0/0EUaOSY8+T8YdQBP6+NlYX4CrpQERjc/YoFvFUYOOUw0" +
                "8sGJSwJAelwSwmfagUDcvfDyEOlbRCTP38RlJtorRyf8Xv61wwpVhE2hkUuZX0wt" +
                "7MqpHaG3+i58bJY5TXhfGnzwflfE/QJBAMecHZA1Jb42wwBDmwQ89t/VHyGjixVB" +
                "tSg9NrwGBnDKcfXQ9NAICmja4eduGew3CXDDXtZgT1lO+FGC+3MVbOUCQC9BzAaY" +
                "VvMK+JMA8hbW2ipXf/7DQpGFqSVGgpMAky3oDkTNveYfY5fxTHMNOtryl+TSnYWe" +
                "lC0PCH+p7mCD5Z4="));
    }
}
