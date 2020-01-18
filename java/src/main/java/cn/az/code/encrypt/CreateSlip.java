package cn.az.code.encrypt;

import java.util.Objects;

/**
 * @author Liz
 * @date 1/16/2020
 */
public class CreateSlip {

    public static void main(String[] args) {
        String dateTime = "20191123092324", tid = "6406503212345";
        String key = "30293534053d37353c3d6b68696e6f6c";
        byte[] iv = (tid.substring(5) + dateTime.substring(6)).getBytes();
        byte[] keys = AesDecryptUtil.hexStringToBytes(key);
        String decryptedString = "", encryptedString = "";

        for (int i = 0; i < keys.length; i++) {
            keys[i] = (byte) (keys[i] ^ 0x5a);
        }
        String template = "{\"DenpyoInfo\":" + "{\"KessaiSyubeu\":\"01\",\"TerminalSeqNo\":\"12345\",\"YukoKigen\":\"20201212\",\"TerminalID\":\"6406503212345\",\"ProcessSeqNo\":\"00107\",\"ToriatukaiKubun\":\"123456\",\"Amount\":\"0009999\",\"Signature\":\"hahaha\"}," +
                "\"IcInfo\":{\"BrandName\":\"123456789\",\"CardSeqNo\":\"666\"}," +
                "\"SonotaInfo\":{\"MerchantInfo\":\"chino\",\"MerchantGyosyu\":\"eve\"}," +
                "\"DccInfo\":{\"CurrencyAbbreviation\":\"dollar\",\"DisclaimerLength\":\"444\"}}";

        byte[] temp = template.getBytes();

        try {
            encryptedString = AesDecryptUtil.bytesToHexString(Objects.requireNonNull(AesDecryptUtil.vpadEncrypt(
                    AesDecryptUtil.MODE_CBC, iv, keys.length * 8, keys, temp, temp.length)));
        } catch (Exception e) {
           e.printStackTrace();
        }
        System.out.println("encryptedString = " + encryptedString);

        temp = AesDecryptUtil.hexStringToBytes(Objects.requireNonNull(encryptedString));
        try {
            decryptedString = new String(Objects.requireNonNull(AesDecryptUtil.vpadDecrypt(
                    AesDecryptUtil.MODE_CBC, iv, keys.length * 8, keys, temp, temp.length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("decryptedString = " + decryptedString);
    }

}
