package cn.az.code.learn;


import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

/**
 * @author Liz
 * @date 1/18/2020
 */
public class UuidDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(compressedUuid(UUID.randomUUID()));
        }
    }

    private static String compressedUuid(UUID uuid) {
        byte[] byUuid = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
        // LE5ZZzh7SWyzkc0bx-q3xQ (no '==')
        return Base64.encodeBase64URLSafeString(byUuid);
    }

    private static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }
}
