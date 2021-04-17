package cn.az.code.encrypt;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * The type SHA256 util.
 *
 * @author Liz
 */
public class ShaUtil {

    private static final String SHA256 = "SHA-256";

    /**
     * Instantiates a new Sha util.
     */
    protected ShaUtil() {

    }

    /**
     * Sha 256 optional.
     *
     * @param str the str
     * @return the optional
     */
    public static Optional<?> sha256(String str) {
        if (StringUtils.isEmpty(str)) {
            return Optional.empty();
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA256);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return Optional.of(byte2Hex(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1) {
                //得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
