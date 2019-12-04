package cn.az.code.encrypt;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * @author Liz
 * @version 2019/12/3
 */
public class Md5Util {

    public static void main(String[] args) {
        String sn = "000000001";
        String date = "20180118141634";
        System.out.println(generateHash(sn+date));
        System.out.println(Optional.ofNullable("ss").orElse("haha"));
    }
    /**
     * @param input string
     * @return hash
     */
    public static String generateHash(String input){
        //チェック
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                int temp = b & 255;
                if (temp < 16) {
                    hexString.append("0").append(Integer.toHexString(temp));
                } else {
                    hexString.append(Integer.toHexString(temp));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
