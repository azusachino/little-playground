package cn.az.code.encrypt;


import java.security.Key;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author az
 */
public class AesDecryptUtil {

    /**
     * The constant MODE_ECB.
     */
    public static final int MODE_ECB = 1;

    /**
     * The constant MODE_CBC.
     */
    public static final int MODE_CBC = 2;

    private static final byte[] IN = new byte[]{
            0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00,
            0x00
    };

    /**
     * AES復号化処理（Paddingなし）*
     *
     * @param ciMode ブロック暗号化モード指定（MODE_ECB、MODE_CBC）*
     * @param iv     MODE_CBCの為の初期化ベクトル指定（16 bytesのデータ）（NULL指定の場合、すべて 0 を設定）*
     * @param keylen 鍵長指定（ビット長：128、192、256 の何れか）*
     * @param key    暗号化／復号化の鍵の構成要素指定*
     * @param in     入力データのアドレス指定（暗号化する元データ）*
     * @param ilen   入力データの長さ（octets:8 ビットを1組→バイト）*
     * @return the byte [ ]
     * @throws Exception 内部処理異常
     */
    public static byte[] vnopadDecrypt(int ciMode, byte[] iv, int keylen,
                                       byte[] key, byte[] in, int ilen) throws Exception {
        // ブロック暗号化モード指定（MODE_ECB、MODE_CBC）が不正
        if (ciMode != MODE_ECB && ciMode != MODE_CBC) {
            return null;
        }
        // 鍵長の指定が不正
        if (keylen != 128 && keylen != 192 && keylen != 256) {
            return null;
        }
        // 鍵構成要素アドレス指定が不正（NULL）
        if (key == null) {
            return null;
        }
        // if(outDEC == null){return -10; }
        // 正常終了（inがNULL、または、ilen<=0 の場合）
        if (in == null || ilen == 0) {
            return null;
        }
        // 入力データ長異常（ブロック長で割り切れない
        if ((ilen & 0x0f) != 0) {
            return null;
        }

        // MODE_CBCの為の初期化ベクトル指定（16bytesのデータ）（NULL指定の場合、すべて 0 を設定）
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(IN);
        } else {
            ivSpec = new IvParameterSpec(iv);
        }

        // ブロック暗号化モード指定
        Cipher cipher;
        if (ciMode == MODE_ECB) {
            ivSpec = null;
            cipher = Cipher.getInstance("AES/ECB/NoPadding");
        } else {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
        }

        // 復号化の鍵
        Key keySpec = new SecretKeySpec(key, "AES");

        // 復号化
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(in);
    }

    /**
     * AES暗号化処理（Paddingなし）
     *
     * @param ciMode the ci mode
     * @param iv     the iv
     * @param keylen the keylen
     * @param key    the key
     * @param in     the in
     * @param ilen   the ilen
     * @return the byte [ ]
     * @throws Exception the exception
     */
    public static byte[] vnopadEncrypt(int ciMode, byte[] iv, int keylen,
                                       byte[] key, byte[] in,
                                       int ilen) throws Exception {
        // ブロック暗号化モード指定（MODE_ECB、MODE_CBC）が不正
        if (ciMode != MODE_ECB && ciMode != MODE_CBC) {
            return null;
        }
        // 鍵長の指定が不正
        if (keylen != 128 && keylen != 192 && keylen != 256) {
            return null;
        }
        // 鍵構成要素アドレス指定が不正（NULL）
        if (key == null) {
            return null;
        }
        // if(outEN == null) {return -10; }
        // 正常終了（inがNULL、または、ilen<=0 の場合）
        if (in == null || ilen == 0) {
            return null;
        }
        // 入力データ長異常（ブロック長で割り切れない）
        if ((ilen & 0x0f) != 0) {
            return null;
        }

        // MODE_CBCの為の初期化ベクトル指定（16bytesのデータ）（NULL指定の場合、すべて 0 を設定）
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(IN);
        } else {
            ivSpec = new IvParameterSpec(iv);
        }

        // ブロック暗号化モード指定（MODE_ECB、MODE_CBC）
        Cipher cipher;
        if (ciMode == MODE_ECB) {
            cipher = Cipher.getInstance("AES/ECB/NoPadding");
        } else {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
        }

        // 暗号化の鍵
        Key keySpec = new SecretKeySpec(key, "AES");

        // 暗号化
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(in);
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[] byte [ ]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return new byte[0];
        }
        hexString = hexString.toUpperCase(Locale.JAPANESE);
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | (charToByte(hexChars[pos + 1])) & 0xff);
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * Convert byte[] string to hex string
     *
     * @param src src
     * @return String string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * AES暗号化処理（Paddingあり）
     *
     * @param ciMode ブロック暗号化モード指定（MODE_ECB、MODE_CBC）
     * @param iv     MODE_CBCの為の初期化ベクトル指定（16bytesのデータ） （NULL指定の場合、すべて 0 を設定）
     * @param keylen 鍵長指定（ビット長：128、192、256の何れか）
     * @param key    暗号化／復号化の鍵の構成要素指定
     * @param in     入力データのアドレス指定（暗号化する元データ）
     * @param ilen   入力データの長さ（octets:8ビットを1組→バイト）
     * @return int 出力データのバイト長
     * @throws Exception 内部処理異常
     */
    public static byte[] vpadEncrypt(int ciMode, byte[] iv, int keylen,
                                     byte[] key, byte[] in,
                                     int ilen) throws Exception {
        // ブロック暗号化モード指定（MODE_ECB、MODE_CBC）が不正
        if (ciMode != MODE_ECB && ciMode != MODE_CBC) {
            return null;
        }
        // 鍵長の指定が不正
        if (keylen != 128 && keylen != 192 && keylen != 256) {
            return null;
        }
        // 鍵構成要素アドレス指定が不正（NULL）
        if (key == null) {
            return null;
        }
        // if(outEN == null){return -10; }
        // 正常終了（inがNULL、または、ilen<=0 の場合）
        if (in == null || ilen == 0) {
            return null;
        }

        // MODE_CBCの為の初期化ベクトル指定（16bytesのデータ）（NULL指定の場合、すべて 0 を設定）
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(IN);
        } else {
            ivSpec = new IvParameterSpec(iv);
        }

        // ブロック暗号化モード（MODE_ECB、MODE_CBC）
        Cipher cipher;
        if (ciMode == MODE_ECB) {
            ivSpec = null;
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } else {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }

        // 暗号化の鍵
        Key keySpec = new SecretKeySpec(key, "AES");

        // 暗号化
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(in);
    }

    /**
     * AES復号化処理（Paddingあり）
     *
     * @param ciMode ブロック暗号化モード指定（MODE_ECB、MODE_CBC）
     * @param iv     MODE_CBCの為の初期化ベクトル指定（16bytesのデータ） （NULL指定の場合、すべて 0 を設定）
     * @param keylen 鍵長指定（ビット長：128、192、256の何れか）
     * @param key    暗号化／復号化の鍵の構成要素指定
     * @param in     入力データのアドレス指定（暗号化する元データ）
     * @param ilen   入力データの長さ（octets:8ビットを1組→バイト）
     * @return int 出力データのバイト長
     * @throws Exception 内部処理異常
     */
    public static byte[] vpadDecrypt(int ciMode, byte[] iv, int keylen, byte[] key,
                                     byte[] in, int ilen) throws Exception {

        // ブロック暗号化モード指定（MODE_ECB、MODE_CBC）が不正
        if (ciMode != MODE_ECB && ciMode != MODE_CBC) {
            return null;
        }
        // 鍵長の指定が不正
        if (keylen != 128 && keylen != 192 && keylen != 256) {
            return null;
        }
        // 鍵構成要素アドレス指定が不正（NULL）
        if (key == null) {
            return null;
        }
        // if(outDEC == null){return -10; }
        // 正常終了（inがNULL、または、ilen<=0 の場合）
        if (in == null || ilen == 0) {
            return null;
        }

        // MODE_CBCの為の初期化ベクトル指定（16bytesのデータ）（NULL指定の場合、すべて 0 を設定）
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(IN);
        } else {
            ivSpec = new IvParameterSpec(iv);
        }

        // ブロック暗号化モード（MODE_ECB、MODE_CBC）
        Cipher cipher;
        if (ciMode == MODE_ECB) {
            ivSpec = null;
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } else {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }

        // 復号化の鍵
        Key keySpec = new SecretKeySpec(key, "AES");

        // 復号化
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(in);
    }

}
